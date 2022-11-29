<template>
    <div class="m-3 w-full grid place-items-center">
        <AgGridVue
        style="height: 363px; width: 1700px"
        class="ag-theme-alpine"
        :columnDefs="columnDefs"
        :rowData="rowData"
        @grid-ready="sizeToFit"
        :defaultColDef="defaultColDef"
        >
        </AgGridVue>
    </div>
</template>
<script>
import { AgGridVue } from "ag-grid-vue3";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";

export default {
name: "boardList",

components: {
    AgGridVue,
},

data() {
    return {
    columnDefs: [
        { field: "date", width: 50, suppressSizeToFit: true },
        { field: "dayOftheWeeks", width: 50 },
        { field: "views", width: 50 },
        { field: "answers", width: 50 },
        { field: "firstQuestion", width: 150 },
        { field: "firstAnswer", width: 150 },
        
    ],
    rowData: null,
    gridApi: null,
    defaultColDef: {
        resizable: true,
    },
    };
},

methods: {

    sizeToFit(params) {
    params.api.sizeColumnsToFit();
    },
},

beforeMount() {
    this.columnDefs = [
    { headerName: "일자", field: "date", sortable: true},
    { headerName: "요일", field: "dayOftheWeeks" },
    { headerName: "조회건수", field: "views", sortable: true },
    { headerName: "작성건수", field: "answers", sortable: true },
    { headerName: "조회 1위 글", field: "firstQuestion"},
    { headerName: "답변 1위 글", field: "firstAnswer" },
    ];

    this.rowData = [
    {
        date:'12/01',
        dayOftheWeeks: '월',
        views: '105',
        answers: '100',
        firstQuestion: '어려운 질문이에요',
        firstAnswer: '심심해서..'
    },
    {
        date:'12/02',
        dayOftheWeeks: '화',
        views: '130',
        answers: '65',
        firstQuestion: '어려운 질문이에요',
        firstAnswer: '심심해서..'
    },
    {
        date:'12/03',
        dayOftheWeeks: '수',
        views: '288',
        answers: '13',
        firstQuestion: '어려운 질문이에요',
        firstAnswer: '심심해서..'
    },
    {
        date:'12/04',
        dayOftheWeeks: '목',
        views: '78',
        answers: '30',
        firstQuestion: '어려운 질문이에요',
        firstAnswer: '심심해서..'
    },
    {
        date:'12/05',
        dayOftheWeeks: '금',
        views: '77',
        answers: '45',
        firstQuestion: '어려운 질문이에요',
        firstAnswer: '심심해서..'
    },
    {
        date:'12/06',
        dayOftheWeeks: '토',
        views: '84',
        answers: '22',
        firstQuestion: '어려운 질문이에요',
        firstAnswer: '심심해서..'
    },
    {
        date:'12/07',
        dayOftheWeeks: '일',
        views: '196',
        answers: '80',
        firstQuestion: '어려운 질문이에요',
        firstAnswer: '심심해서..'
    },
    ];
},
};
</script>
<style>
</style>