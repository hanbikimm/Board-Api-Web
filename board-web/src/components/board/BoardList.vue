<template>
<div>
    <div class="mt-5 ml-10">
        <button 
        class="text-base cursor-pointer"
        @click="goToCurrentStatus()">
        > 운영 현황판 가기
        </button>
    </div>    
    <div class="ml-10 mb-3 grid grid-cols-1 gap-6 sm:grid-cols-2">
        <div>
            <p class="text-2xl font-bold mt-5 mb-1">다목적 게시판</p>
            <p>: 총 {{ total }} 건의 게시물</p>
        </div>
        <div class="flex mt-5">
                <div>
                    <select v-model="search.condition"
                        class="p-2.5 text-sm font-medium rounded-l-lg text-gray-900 focus:outline-none bg-gray-100 border border-gray-300 hover:bg-gray-200 hover:border-gray-400">
                        <option value="1">제목(질문/답변)</option>
                        <option value="2">작성자</option>
                    </select>
                </div>
            
            <div class="relative w-1/2">
                <input type="text" placeholder="검색어를 입력하세요..." required
                v-model="search.word"
                class="block p-2.5 w-full text-sm rounded-r-lg text-gray-900 border border-gray-300 hover:border-gray-400 focus:outline-none focus:border-gray-400" >
                <button @click="getSearchList()"
                    class="absolute top-0 right-0 p-2.5 text-sm font-medium rounded-r-lg border text-white bg-blue-700 hover:bg-blue-800 focus:outline-none">
                    <svg aria-hidden="true" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path></svg>
                </button>
            </div>
        </div>
    </div>
    
    <div class="m-3 w-full grid place-items-center">
        <ag-grid-vue
            style="height: 520px; width: 80%"
            class="ag-theme-alpine"
            :gridOptions="gridOptions"
            :columnDefs="columnDefs"
            :rowData="rowData"
            :rowSelection="rowSelection"
            @selection-changed="goToQuestionDetail()"
            @grid-ready="onGridReady"
            :pagination="true"
            :paginationPageSize="paginationPageSize"
            :rowClassRules="rowClassRules">
        </ag-grid-vue>
    </div>

    <div class="flex">
        <div class="w-5/6"></div>
        <div class="w-1/6 mt-9">
            <registerQuestion/>
        </div>
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
                { field: "bbd_seq"},
                { field: "ans_seq" },
                { field: "inq_security_yn"},
                { field: "bbd_title"},
                { field: "answer_count"},
                { field: "reg_writer"},
                { field: "reg_datetime"},
                { field: "total_views"},
            ],
            rowData: null,
            rowClassRules: null,
            defaultColDef: {
                resizable: true,
            },
            gridApi: null,
            columnApi: null,
            rowSelection: null,
            paginationPageSize: 10,
            gridOptions: {},
            

            total: '',

            search:{
                condition:'1',
                word:'',
            },

        };
    },

    methods: {
        onGridReady(params) {
            this.gridApi = params.api;
            this.gridColumnApi = params.columnApi;
            params.api.sizeColumnsToFit();
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

        async getSearchList(){
            try {
                const results = await BoardApi.searchList(this.search.word, this.search.condition);
                this.rowData = results.data;
                this.total = Validation.addComma(results.data.length);
            } catch (error) {
                console.log(error);
            }
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
                    this.updateView(bbdId, ansId);
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
                this.updateView(bbdId, ansId);
                this.$router.push({
                    name: 'questionDetail',
                    params: { bbdId: bbdId, 
                            ansId: ansId }
                });
                
            }
        },

        async updateView(bbdId, ansId){
            await BoardApi.boardView(bbdId, ansId);
        }

    },

    beforeMount() {
        this.columnDefs = [
            { headerName: "순번", field: "bbd_seq", resizable: true },
            { field: "ans_seq", hide: true },
            { headerName: "보안", field: "inq_security_yn", valueFormatter: this.lockFormatter, resizable: true },
            { headerName: "제목", field: "bbd_title", resizable: true },
            { headerName: "답변 수", field: "answer_count", resizable: true },
            { headerName: "작성자", field: "reg_writer", resizable: true },
            { headerName: "작성 일시", field: "reg_datetime", resizable: true },
            { headerName: "조회수", field: "total_views", resizable: true },
        ];

        this.getQuestionList();
        this.rowSelection = 'single';
        this.rowClassRules = {
            "seperateAnswer": "data.ans_seq > 0",
        };
    },
};


</script>
<style>
.ag-theme-alpine .seperateAnswer{
    color: #FF4000 !important;
}
</style>
