<template>
    <div class="m-3 w-full grid place-items-center">
        <div class="flex flex-wrap">
            <div class="flex flex-col mt-7">
                <div class="py-2 -my-2 overflow-x-auto sm:-mx-6 sm:px-6 lg:-mx-8 lg:px-8">
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

                            <!-- <ScheduleListItem 
                                v-for="rowData in this.rowDatas" :key="rowData.id"
                                :rowData = "rowData"
                                @click="goToJobDetail(job.labelId)"/> -->

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

export default {
name: "boardList",

components: {
},

data() {
    return {
        
    };
},

methods: {

},

beforeMount() {
    this.rowDatas = [
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