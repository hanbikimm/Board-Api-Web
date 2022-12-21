<template>
<div>
    <div class="mt-5 ml-10">
        <p 
        class="text-base cursor-pointer"
        @click="goToCurrentStatus()">
            > 운영 현황판 가기
        </p>
        <p class="text-2xl font-bold mt-5 mb-1">다목적 게시판</p>
        <p>: 총 {{ total }} 건의 게시물</p>
    </div>
    
    <div class="m-3 w-full grid place-items-center">
        <ag-grid-vue
            style="height: 519px; width: 1403px"
            class="ag-theme-alpine"
            :gridOptions="gridOptions"
            :columnDefs="columnDefs"
            :rowData="rowData"
            :rowSelection="rowSelection"
            @selection-changed="goToQuestionDetail()"
            :defaultColDef="defaultColDef"
            @grid-ready="onGridReady"
            :pagination="true"
            :paginationPageSize="paginationPageSize"
        >
        </ag-grid-vue>
    </div>

    <div class="flex justify-end mt-9">
        <registerQuestion/>
    </div>

</div>
</template>

<script>
import registerQuestion from "@/components/question/RegisterQuestion.vue";
import { AgGridVue } from "ag-grid-vue3";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";
import questionDetail from "@/components/question/QuestionDetail.vue";
import SecretBoard from "@/components/modal/SecretBoard.vue";
import BoardApi from "@/api/BoardApi";
import Validation from "@/assets/Validation";
import LockSvg from "@/assets/LockSvg.vue"

export default {
    name: "boardList",

    components: {
        AgGridVue,
        registerQuestion,
        questionDetail,
        SecretBoard,
        LockSvg
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
            paginationPageSize: 10,
            gridOptions: {},
            total: '',

            paging: {},

        };
    },

    methods: {
        onGridReady(params) {
            this.gridApi = params.api;
            this.gridColumnApi = params.columnApi;
        },

        lockFormatter(params){
            if (params.value == 'y') {
                return '🔒'
            } else {
                return ''
            }
        },

        async getQuestionList(){
            try{
                const results = await BoardApi.questionList();
                const total_board = await BoardApi.totalBoard();
                this.rowData = results;
                this.total = Validation.addComma(total_board);

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
            const bbdId = row[0].bbd_seq;
            const ansId = row[0].ans_seq;

            if (row[0].inq_security_yn == 'y') {
                const input = prompt('비밀번호를 입력하세요.', '4자리 숫자')
                if (input === row[0].bbd_password) {
                    this.$router.push({
                    name: 'questionDetail',
                    params: { bbdId: bbdId, 
                            ansId: ansId }
                    });
                } else if(input != row[0].bbd_password) {
                    alert('비밀번호가 틀렸습니다!');
                    this.getQuestionList();
                }
            } else {
                this.$router.push({
                    name: 'questionDetail',
                    params: { bbdId: bbdId, 
                            ansId: ansId }
                });
            }
            
            
            
        },

    },

    beforeMount() {
        this.columnDefs = [
            { headerName: "순번", field: "bbd_seq" },
            { headerName: "보안", field: "inq_security_yn", valueFormatter: this.lockFormatter },
            { headerName: "제목", field: "bbd_title"},
            { headerName: "답변 수", field: "answer_count" },
            { headerName: "작성자", field: "reg_writer" },
            { headerName: "작성 일시", field: "reg_datetime" },
            { headerName: "조회수", field: "total_views" },
        ];

        this.getQuestionList();
        this.rowSelection = 'single';
    },
};


</script>
