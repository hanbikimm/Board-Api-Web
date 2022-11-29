<template>
    <div class="mt-5 ml-10">
        <p 
        class="text-base cursor-pointer"
        @click="goToboardList()">
            > 다목적 게시판 가기
        </p>
        <p class="text-2xl font-bold mt-5">운영 현황판</p>
    </div>
    <div class="m-10 flex items-center justify-center">
        > 조회 기간 
    </div>
    <div>
        <GChart type="ColumnChart" :data="chartData" :options="chartOptions"/>
    </div>
      
    <StatusBoard/>
    
</template>
<script>
import StatusBoard from './StatusBoard.vue';
import { GChart } from "vue-google-charts";

export default {
    name: "CurrentStatus",
    components: { 
        StatusBoard,
        GChart,
    },
    data() {
        return {
            date: [],
            chartData: [
                ['Date', '조회수', '작성수'],
                ['월(12/01)', 105, 100],
                ['화(12/02)', 130, 65],
                ['수(12/03)', 288, 13],
                ['목(12/04)', 78, 30],
                ['금(12/05)', 77, 45],
                ['토(12/06)', 84, 22],
                ['일(12/07)', 196, 80],
            ],
            chartOptions: {
                chart: {
                title: "조회 및 작성 통계",          
                }
            }
        }
    },

    methods:{
        goToboardList(){
            this.$router.push({
            name: 'boardList',
            })
        }
    }
    
}
</script>
<style>
</style>