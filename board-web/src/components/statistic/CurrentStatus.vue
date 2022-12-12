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
        > 조회 기간 &nbsp;
        <select v-model="this.date.year">
            <option disabled value="">년도</option>
            <option value="2022" selected="selected">2022년</option>
            <option value="2021">2021년</option>
            <option value="2020">2020년</option>
        </select>
        &nbsp;
        <select v-model="this.date.month">
            <option disabled value="">월</option>
            <option value="1">1월</option>
            <option value="2">2월</option>
            <option value="3">3월</option>
        </select>
        &nbsp;
        <select v-model="this.date.week">
            <option disabled value="">주간</option>
            <option value="1">첫째주</option>
            <option value="2">둘째주</option>
            <option value="3">셋째주</option>
            <option value="4">넷째주</option>
        </select>
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
            date: {
                year: '',
                month: '',
                week: ''
            },
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