<template>
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
import StatusListItem from './StatusListItem.vue';
export default {
name: "boardList",

components: {
    StatusListItem
},

data() {
    return {
        statusList:[]
    };
},

methods: {

},

beforeMount() {
    this.statusList = [
    {
        date:'12/01',
        day: '월',
        dailyView: '105',
        dailyWrite: '100',
        bbdTitleOfFirstView: '어려운 질문이에요',
        bbdTitleOfFirstAnswer: '심심해서..',
        bbdIdOfFirstView: 13,
        ansIdOfFirstView: 0,
        bbdIdOfFirstAnswer:9,
        ansIdOfFirstAnswer:0

    },
    {
        date:'12/02',
        day: '화',
        dailyView: '130',
        dailyWrite: '65',
        bbdTitleOfFirstView: '어려운 질문이에요',
        bbdTitleOfFirstAnswer: '심심해서..',
        bbdIdOfFirstView: 2,
        ansIdOfFirstView: 1,
        bbdIdOfFirstAnswer:6,
        ansIdOfFirstAnswer:0
    },
    {
        date:'12/03',
        day: '수',
        dailyView: '288',
        dailyWrite: '13',
        bbdTitleOfFirstView: '어려운 질문이에요',
        bbdTitleOfFirstAnswer: '심심해서..',
        bbdIdOfFirstView: 9,
        ansIdOfFirstView: 3,
        bbdIdOfFirstAnswer:14,
        ansIdOfFirstAnswer:0
    },
    {
        date:'12/04',
        day: '목',
        dailyView: '78',
        dailyWrite: '30',
        bbdTitleOfFirstView: '어려운 질문이에요',
        bbdTitleOfFirstAnswer: '심심해서..',
        bbdIdOfFirstView: 13,
        ansIdOfFirstView: 0,
        bbdIdOfFirstAnswer:9,
        ansIdOfFirstAnswer:0
    },
    {
        date:'12/05',
        day: '금',
        dailyView: '77',
        dailyWrite: '45',
        bbdTitleOfFirstView: '어려운 질문이에요',
        bbdTitleOfFirstAnswer: '심심해서..',
        bbdIdOfFirstView: 13,
        ansIdOfFirstView: 0,
        bbdIdOfFirstAnswer:9,
        ansIdOfFirstAnswer:0
    },
    {
        date:'12/06',
        day: '토',
        dailyView: '84',
        dailyWrite: '22',
        bbdTitleOfFirstView: '어려운 질문이에요',
        bbdTitleOfFirstAnswer: '심심해서..',
        bbdIdOfFirstView: 13,
        ansIdOfFirstView: 0,
        bbdIdOfFirstAnswer:9,
        ansIdOfFirstAnswer:0
    },
    {
        date:'12/07',
        day: '일',
        dailyView: '196',
        dailyWrite: '80',
        bbdTitleOfFirstView: '어려운 질문이에요',
        bbdTitleOfFirstAnswer: '심심해서..',
        bbdIdOfFirstView: 13,
        ansIdOfFirstView: 0,
        bbdIdOfFirstAnswer:9,
        ansIdOfFirstAnswer:0
    },
    ];
},
};
</script>
<style>
</style>