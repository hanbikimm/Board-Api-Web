<template>
<div>
    <div class="mt-5 ml-10">
        <p 
        class="text-base cursor-pointer"
        @click="goToCurrentStatus()">
            > 운영 현황판 가기
        </p>
        <p class="text-2xl font-bold my-5">다목적 게시판</p>
        <p>> 총 2022 건의 게시물</p>
    </div>
    
    <div class="m-3 w-full grid place-items-center">
        <ag-grid-vue
            style="height: 471px; width: 1403px"
            class="ag-theme-alpine"
            :gridOptions="gridOptions"
            :columnDefs="columnDefs"
            :rowData="rowData"
            :rowSelection="rowSelection"
            @selection-changed="goToQuestionDetail"
            :defaultColDef="defaultColDef"
            @grid-ready="onGridReady"

        >
        </ag-grid-vue>
    </div>

    <div class="flex justify-end mt-9">
        <registerQuestion/> <SecretBoard/>
    </div>

    <div>
        <boardPagination
        :paging="paging"
        v-on:prevPage="prevPage"
        v-on:nextPage="nextPage"
        v-on:firstPage="firstPage"
        v-on:lastPage="lastPage"
        v-on:changeNowPage="changeNowPage"/>
    </div>
</div>
</template>

<script>
import registerQuestion from "@/components/question/RegisterQuestion.vue";
import { AgGridVue } from "ag-grid-vue3";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";
import boardPagination from "@/components/board/BoardPagination.vue";
import questionDetail from "@/components/question/QuestionDetail.vue";
import SecretBoard from "@/components/modal/SecretBoard.vue";
import BoardApi from "@/api/BoardApi";

export default {
    name: "boardList",

    components: {
        AgGridVue,
        registerQuestion,
        questionDetail,
        boardPagination,
        SecretBoard
    },

    data() {
        return {
            columnDefs: [
                { field: "bbd_seq", width: 50, suppressSizeToFit: true },
                { field: "inq_security_yn", width: 50 },
                { field: "bbd_title", width: 200 },
                { field: "answer_count", width: 50 },
                { field: "reg_writer", width: 150 },
                { field: "reg_datetime", width: 100 },
                { field: "total_views", width: 50 },
            ],
            rowData: null,
            defaultColDef: {
                resizable: true,
            },
            gridApi: null,
            columnApi: null,
            rowSelection: null,
            gridOptions: {},
            total_boards: '',

            paging: {},

        };
    },

    methods: {
        onGridReady(params) {
            this.gridApi = params.api;
            this.gridColumnApi = params.columnApi;
        },

        async getBoardList(){
            try{
                const results = await BoardApi.boardList();
                this.rowData = results;
                // this.total_boards = ressults.

            }catch(error){
                console.log(error);
            }
        },

        enterSecretNum() {
            
        },

        goToCurrentStatus(){
            this.$router.push({
            name: 'currentStatus',
            })
        },

        goToQuestionDetail(){
            const row = this.gridApi.getSelectedRows();
            const id = row[0].bbd_seq;
            this.$router.push({
            name: 'questionDetail',
            params: { id: id }
            })
        },

        

        // 페이징
        prevPage(){
            if(this.paging.nowPage > 1){
                this.paging.nowPage -= 1;
                this.getBoardList(this.paging.nowPage);
            }else if(this.paging.nowPage == 1){
                alert('첫번째 페이지입니다.');
            }
        },
        nextPage(){
            if(this.paging.lastPage > this.paging.nowPage){
                this.paging.nowPage += 1;
                this.getBoardList(this.paging.nowPage);
            }else if(this.paging.lastPage == this.paging.nowPage){
                alert('마지막 페이지입니다.');
            }
        },
        firstPage(){
            if(this.paging.nowPage != 1){
                this.paging.nowPage = 1;
                this.getBoardList(this.paging.nowPage);
            }else if(this.paging.nowPage == 1){
                alert('첫번째 페이지입니다.');
            }
        },
        lastPage(){
            if(this.paging.nowPage != this.paging.lastPage){
                this.paging.nowPage = this.paging.lastPage;
                this.getBoardList(this.paging.nowPage);
            }else if(this.paging.lastPage == this.paging.nowPage){
                alert('마지막 페이지입니다.');
            }
        },
        changeNowPage(page){
            if(page !== this.paging.nowPage){
                this.paging.nowPage = page;
                this.getBoardList(this.paging.nowPage);
            }
        }


    },

    beforeMount() {
        this.columnDefs = [
            { headerName: "순번", field: "bbd_seq", sortable: true, filter: true },
            { headerName: "보안", field: "inq_security_yn", sortable: true, filter: true },
            { headerName: "제목", field: "bbd_title", sortable: true, filter: true },
            { headerName: "답변 수", field: "answer_count", sortable: true, filter: true},
            { headerName: "작성자", field: "reg_writer", sortable: true, filter: true },
            { headerName: "작성 일시", field: "bbd_datetime", sortable: true, filter: true },
            { headerName: "조회수", field: "total_views", sortable: true, filter: true },
        ];

        this.getBoardList();
        this.rowSelection = 'single';
    },
};
</script>
