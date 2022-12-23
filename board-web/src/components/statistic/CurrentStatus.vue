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
            <option
                v-for="(item, index) in yearList"
                :key="index"
                :value="item.value"
            >{{ item.name }}</option>
            
        </select>
        &nbsp;
        <select v-model="this.date.month">
            <option disabled>월</option>
            <option
                v-for="(item, index) in monthList"
                :key="index"
                :value="item.value"
            >{{ item.name }}</option>
        </select>
        &nbsp;
        <select v-model="this.date.week">
            <option disabled value="">주간</option>
            <option value="23">23</option>
            <option value="24">둘째주</option>
            <option value="3">셋째주</option>
            <option value="4">넷째주</option>
        </select>
    </div>
    <div>
        <GChart type="ColumnChart" :data="chartData" :options="chartOptions"/>
    </div>
    <button @click="setDate()">moment</button>  
    <StatusBoard/>

    
</template>
<script>
import StatusBoard from './StatusBoard.vue';
import { GChart } from "vue-google-charts";
import moment from 'moment';

export default {
    name: "CurrentStatus",
    components: { 
        StatusBoard,
        GChart,
    },
    data() {
        return {
            date: {
                year: moment().format('YYYY'),
                month: moment().format('MM'),
                week: ''
            },

            monthList:[
                { name: "1월", value: "1" },
                { name: "2월", value: "2" },
                { name: "3월", value: "3" },
                { name: "4월", value: "4" },
                { name: "5월", value: "5" },
                { name: "6월", value: "6" },
                { name: "7월", value: "7" },
                { name: "8월", value: "8" },
                { name: "9월", value: "9" },
                { name: "10월", value: "10" },
                { name: "11월", value: "11" },
                { name: "12월", value: "12" },
            ],

            yearList:[
                { name: "2022년", value: "2022" },
                { name: "2021년", value: "2021" },
                { name: "2020년", value: "2020" },
                { name: "2019년", value: "2019" },
                { name: "2018년", value: "2018" },
                { name: "2017년", value: "2017" },
                { name: "2016년", value: "2016" },
                { name: "2015년", value: "2015" },
                { name: "2014년", value: "2014" },
                { name: "2013년", value: "2013" },
                { name: "2012년", value: "2012" },
                { name: "2011년", value: "2011" },
                { name: "2010년", value: "2010" },
            ],

            chartData: [
                ['날짜', '조회수', '작성수'],
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
        },

        setDate(){
            const this_monday = moment().day(1).format("YYYY-MM-DD");
            const this_sunday = moment().day(7).format("YYYY-MM-DD");
            console.log(this_monday)
            console.log(this_sunday);
        }
    }
    
}
</script>
<style>
</style>