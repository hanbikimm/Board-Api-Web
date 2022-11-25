<template>
    <div>
        <p class="text-2xl font-bold mt-5">다목적 게시판</p>
            <div class="m-3 w-full grid place-items-center">
                <ag-grid-vue 
                    @rowClicked="onRowClicked"
                    style="height: 471px; width: 1603px"
                    class="ag-theme-alpine"
                    :columnDefs="columnDefs"
                    :rowData="rowData">
                </ag-grid-vue>

                <div class="flex justify-end mt-9">
                    <registerQuestion/>
                </div>
            </div>
        

        <div class="col-span-2 flex justify-start mt-9">
            <editQuestion/> <deleteBoard/>
        </div>

        <div class="col-span-2 flex justify-start mt-9">
            <questionDetail/> <answerDetail/>
        </div>


    </div>
</template>

<script>

import registerQuestion from "@/components/question/RegisterQuestion.vue"
import questionDetail from '@/components/question/QuestionDetail.vue'
import {AgGridVue} from "ag-grid-vue3"
import 'ag-grid-community/dist/styles/ag-grid.css'
import 'ag-grid-community/dist/styles/ag-theme-alpine.css'
import deleteBoard from "../modal/DeleteBoard.vue"
import editQuestion from '@/components/question/EditQuestion.vue'
import answerDetail from "@/components/answer/AnswerDetail.vue"



export default {
    name: 'boardList',

    components: {
        AgGridVue,
        registerQuestion,
        questionDetail,
        deleteBoard,
        editQuestion,
        answerDetail,
},

    data () {
            return {
                columnDefs: [
                    {
                        field: 'edit',
                        cellRenderer: function(){
                            return '<editQuestion/>'
                        },

                    }
                ],
                rowData: null,
            }
        },

    methods: {

        enterSecretNum(){
            
        },

        onRowClicked(){
            alert('row clicked!')
        }

        
    },
        
    beforeMount() {
        this.columnDefs = [
            { headerName: '순번', field: 'id', sortable: true, filter: true },
            { headerName: '보안', field: 'secret', sortable: true, filter: true },
            { headerName:'제목', field: 'title', sortable: true, filter: true },
            { headerName: '작성자', field: 'writer', sortable: true, filter: true },
            { headerName: '작성 일시', field: 'date', sortable: true, filter: true },
            { headerName:'조회수', field: 'views', sortable: true, filter: true },
            { headerName:'수정', field: 'edit' },
            { headerName:'삭제', field: 'delete' },
            
        ];

        this.rowData = [
            { id: '2023', secret: 'o', title: '어려운 질문', writer: '김한비', date: '2022-11-31 10:10:10', views: 5, edit: 'edit', delete: 'delete' },
            { id: '2022', secret: 'x', title: '질문', writer: '민윤기', date: '2022-11-31 12:25:10', views: 55, edit: 'edit', delete: 'delete' },
            { id: '2021', secret: 'o', title: '쉬운 질문', writer: '김석진', date: '2022-11-31 08:13:10', views: 25, edit: 'edit', delete: 'delete' },
            { id: '2020', secret: 'o', title: '어려운 질문', writer: '김남준', date: '2022-11-31 04:55:10', views: 85, edit: 'edit', delete: 'delete' },
            { id: '2019', secret: 'x', title: '대박 질문', writer: '전정국', date: '2022-11-31 09:30:10', views: 33, edit: 'edit', delete: 'delete' },
            { id: '2018', secret: 'x', title: '파이썬 질문', writer: '박지민', date: '2022-11-31 05:48:10', views: 188, edit: 'edit', delete: 'delete' },
            { id: '2017', secret: 'o', title: '자바 질문', writer: '정호석', date: '2022-11-31 01:10:10', views: 1, edit: 'edit', delete: 'delete' },
            { id: '2016', secret: 'o', title: '자바 스크립트 질문', writer: '김태형', date: '2022-11-31 12:37:10', views: 60, edit: 'edit', delete: 'delete' },
            { id: '2015', secret: 'o', title: '스프링 질문', writer: '김한비', date: '2022-11-31 01:52:10', views: 16, edit: 'edit', delete: 'delete' },
            { id: '2014', secret: 'o', title: '리액트 질문', writer: '전정국', date: '2022-11-31 07:22:10', views: 128, edit: 'edit', delete: 'delete' },
            
        ];
    }
}
</script>