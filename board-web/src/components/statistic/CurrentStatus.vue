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
        <select 
        class="p-2.5 cursor-pointer text-sm font-medium text-gray-900 focus:outline-none bg-transparent border-0 border-b-2 border-gray-300 hover:border-gray-400"
        v-model="this.date.year"
        @change="setWeekList()">
            <option disabled value="">년도</option>
            <option
                v-for="(item, index) in yearList"
                :key="index"
                :value="item.value"
            >{{ item.name }}</option>
            
        </select>
        &nbsp;
        <select 
        class="p-2.5 cursor-pointer text-sm font-medium text-gray-900 focus:outline-none bg-transparent border-0 border-b-2 border-gray-300 hover:border-gray-400"
        v-model="this.date.month"
        @change="setWeekList()">
            <option disabled>월</option>
            <option
                v-for="(item, index) in monthList"
                :key="index"
                :value="item.value"
            >{{ item.name }}</option>
        </select>
        &nbsp;
        <select 
        class="p-2.5 cursor-pointer text-sm font-medium text-gray-900 focus:outline-none bg-transparent border-0 border-b-2 border-gray-300 hover:border-gray-400"
        v-model="this.date.week">
            <option disabled value="">주간</option>
            <option
                v-for="(item, index) in weekList"
                :key="index"
                :value="item.value"
            >{{ item.name }}</option>
        </select>
        <button @click="getStatusData()"
            class="top-0 right-0 ml-2 p-2.5 text-sm font-medium rounded-lg border text-white bg-blue-700 hover:bg-blue-800 focus:outline-none">
            <svg aria-hidden="true" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path></svg>
        </button>
        <button
         @click="generateStatus()"
          class="block font-medium rounded-lg ml-5 text-base px-4 py-2 text-center text-white bg-blue-700 hover:bg-blue-800 focus:outline-none">
          출력
        </button>
    </div>
    <div>
        <GChart type="ColumnChart" :data="chartData" :options="chartOptions"/>
    </div>

    <div class="flex flex-col mt-7 mx-14">
        <div class="py-2 overflow-x-auto sm:-mx-6 sm:px-6 lg:-mx-8 lg:px-8">
            <div class="inline-block min-w-full overflow-hidden align-middle border-b border-gray-200 shadow sm:rounded-lg">
                <table class="min-w-full">
                    <thead>
                        <tr>
                            <th class="px-6 py-3 text-sm font-bold leading-4 tracking-wider text-left text-gray-500 uppercase border-b border-gray-200 bg-gray-50">
                            일자
                            </th>
                            <th class="px-6 py-3 text-sm font-bold leading-4 tracking-wider text-left text-gray-500 uppercase border-b border-gray-200 bg-gray-50">
                            요일
                            </th>
                            <th class="px-6 py-3 text-sm font-bold leading-4 tracking-wider text-left text-gray-500 uppercase border-b border-gray-200 bg-gray-50">
                            조회 건수
                            </th>
                            <th class="px-6 py-3 text-sm font-bold leading-4 tracking-wider text-left text-gray-500 uppercase border-b border-gray-200 bg-gray-50">
                            작성 건수
                            </th>
                            <th class="px-6 py-3 text-sm font-bold leading-4 tracking-wider text-left text-gray-500 uppercase border-b border-gray-200 bg-gray-50">
                            조회 1위 글
                            </th>
                            <th class="px-6 py-3 text-sm font-bold leading-4 tracking-wider text-left text-gray-500 uppercase border-b border-gray-200 bg-gray-50">
                            답변 1위 글
                            </th>
                        </tr>
                    </thead>

                    <StatusListItem 
                        v-for="statusItem in this.statusList" :key="statusItem.date"
                        :statusItem = "statusItem"/>

                </table>
            </div>
        </div>
    </div>

    
</template>
<script>
import { GChart } from "vue-google-charts";
import moment from 'moment';
import BoardApi from '@/api/BoardApi';
import StatusListItem from './StatusListItem.vue';

export default {
    name: "CurrentStatus",
    components: { 
        GChart,
        StatusListItem,
    },

    data() {
        return {
            date: {
                year: moment().format('YYYY'),
                month: moment().format('MM'),
                week: moment().day(1).format("MM-DD")
            },

            weekList:[],

            monthList:[
                { name: "1월", value: "01" },
                { name: "2월", value: "02" },
                { name: "3월", value: "03" },
                { name: "4월", value: "04" },
                { name: "5월", value: "05" },
                { name: "6월", value: "06" },
                { name: "7월", value: "07" },
                { name: "8월", value: "08" },
                { name: "9월", value: "09" },
                { name: "10월", value: "10" },
                { name: "11월", value: "11" },
                { name: "12월", value: "12" },
            ],

            yearList:[
                { name: "2023년", value: "2023" },
                { name: "2022년", value: "2022" },
                { name: "2021년", value: "2021" },
                { name: "2020년", value: "2020" },
                // { name: "2019년", value: "2019" },
                // { name: "2018년", value: "2018" },
                // { name: "2017년", value: "2017" },
                // { name: "2016년", value: "2016" },
                // { name: "2015년", value: "2015" },
                // { name: "2014년", value: "2014" },
                // { name: "2013년", value: "2013" },
                // { name: "2012년", value: "2012" },
                // { name: "2011년", value: "2011" },
                // { name: "2010년", value: "2010" },
            ],

            chartData: [],
            chartOptions: {
                chart: {
                title: "조회 및 작성 통계",          
                }
            },

            statusList:[],
        }
    },

    methods:{
        goToboardList(){
            this.$router.push({
            name: 'boardList',
            })
        },

        setWeekList(){
            try {
                this.weekList = [];
                let year = this.date.year;
                let month = this.date.month;
                let monday = 1;
                let sunday = 7;

                for(let i=0; i<5; i++){
                    let week_monday = moment(`${year}${month}01`).day(monday).format("MM-DD");
                    let week_sunday = moment(`${year}${month}01`).day(sunday).format("MM-DD");
                    let obj = { name: `${week_monday} ~ ${week_sunday}`, value: `${week_monday}` };
                    this.weekList.push(obj);
                    monday += 7;
                    sunday += 7;
                }   
            } catch (error) {
                console.log(error);
            }
            
        },

        getStatusData(){
            const date = `${this.date.year}-${this.date.week}`;
            this.setStatusData(date);
        },

        setChartData(){
            try {
                // 초기화
                this.chartData = [];
                let dataRow = ['날짜', '조회수', '작성수'];
                this.chartData.push(dataRow);
                for (let i=0; i<this.statusList.length; i++) {
                    let data = this.statusList[i];
                    dataRow = [`${data.day}(${data.date.slice(5,7)}/${data.date.slice(8)})`, data.dailyView, data.dailyWrite];
                    this.chartData.push(dataRow);
                }
            } catch (error) {
                console.log(error);
            }
        },

        async setStatusData(date){
            try{
                const data = await BoardApi.boardStatus(date);
                this.statusList = data;
                this.setChartData();
            }catch(error){
                console.log(error);
            }
        },

        async generateStatus(){
            try {
                const date = `${this.date.year}-${this.date.week}`;
                window.open(`http://localhost:8000/bbd/generate/${date}`);
            } catch (error) {
                console.log(error);
            }
        }
    },

    mounted(){
        this.setWeekList();
        this.getStatusData();
    }
    
}
</script>
<style>
</style>