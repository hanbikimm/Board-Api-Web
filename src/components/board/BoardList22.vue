<template>
<div>
    <div class="mt-5 ml-10">
        <p 
        class="text-base cursor-pointer"
        @click="goToCurrentStatus()">
            > 운영 현황판 가기
        </p>
        <p class="text-2xl font-bold my-5">다목적 게시판</p>
    </div>
    
    <div class="m-3 w-full grid place-items-center">
        <grid
            :data="gridProps.data"
            :columns="gridProps.columns">
        </grid>

        <div class="flex justify-end mt-9">
            <registerQuestion />
        </div>
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
import "tui-grid/dist/tui-grid.css";
import { Grid } from "@toast-ui/vue-grid";
import boardPagination from "@/components/board/BoardPagination.vue";
import questionDetail from "@/components/question/QuestionDetail.vue";

export default {
    name: "boardList",

    components: {
        grid: Grid,
        registerQuestion,
        questionDetail,
        boardPagination,
    },

    methods: {
        enterSecretNum() {},

        goToCurrentStatus(){
            this.$router.push({
            name: 'currentStatus',
            })
        },

        goToQuestionDetail(){
            this.$router.push({
            name: 'questionDetail',
            })
        },

        goToAnswerDetail(id){
            this.$router.push({
            name: 'answerDetail',
            params: { id: id }
            })
        },

        getBoardList(){

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

    created() {
        this.gridProps = {
            data: [
                {
                    id: "2023",
                    secret: "o",
                    title: "어려운 질문",
                    writer: "김한비",
                    date: "2022-11-31 10:10:10",
                    views: 5,
                },
                {
                    id: "2022",
                    secret: "x",
                    title: "질문",
                    writer: "민윤기",
                    date: "2022-11-31 12:25:10",
                    views: 55,
                },
                {
                    id: "2021",
                    secret: "o",
                    title: "쉬운 질문",
                    writer: "김석진",
                    date: "2022-11-31 08:13:10",
                    views: 25,
                },
                {
                    id: "2020",
                    secret: "o",
                    title: "어려운 질문",
                    writer: "김남준",
                    date: "2022-11-31 04:55:10",
                    views: 85,
                },
                {
                    id: "2019",
                    secret: "x",
                    title: "대박 질문",
                    writer: "전정국",
                    date: "2022-11-31 09:30:10",
                    views: 33,
                },
                {
                    id: "2018",
                    secret: "x",
                    title: "파이썬 질문",
                    writer: "박지민",
                    date: "2022-11-31 05:48:10",
                    views: 188,
                },
                {
                    id: "2017",
                    secret: "o",
                    title: "자바 질문",
                    writer: "정호석",
                    date: "2022-11-31 01:10:10",
                    views: 1,
                },
                {
                    id: "2016",
                    secret: "o",
                    title: "자바 스크립트 질문",
                    writer: "김태형",
                    date: "2022-11-31 12:37:10",
                    views: 60,
                },
                {
                    id: "2015",
                    secret: "o",
                    title: "스프링 질문",
                    writer: "김한비",
                    date: "2022-11-31 01:52:10",
                    views: 16,
                },
                {
                    id: "2014",
                    secret: "o",
                    title: "리액트 질문",
                    writer: "전정국",
                    date: "2022-11-31 07:22:10",
                    views: 128,
                },
            ],
            columns: [
                {
                header: "순번",
                name: "id",
                },
                {
                header: "보안",
                name: "secret",
                },
                {
                header: "제목",
                name: "title",
                },
                {
                header: "작성자",
                name: "writer",
                },
                {
                header: "작성일자",
                name: "date",
                },
                {
                header: "조회수",
                name: "views",
                },
            ],
            
        };
    },

    data() {
        return {
            paging: {},

        };
    },


};
</script>
