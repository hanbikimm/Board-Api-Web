<template>
     <tbody class="bg-white">
        <tr  
        class="hover:bg-gray-50 focus:outline-none">
            <td class="px-6 py-4 text-sm leading-5 text-gray-500 border-b border-gray-200 whitespace-nowrap">
                {{ statusItem.date }}
            </td>

            <td class="px-6 py-4 text-sm leading-5 text-gray-500 border-b border-gray-200 whitespace-nowrap">
                {{ statusItem.day }}
            </td>

            <td class="px-6 py-4 text-sm leading-5 text-gray-500 border-b border-gray-200 whitespace-nowrap">
                {{ statusItem.dailyView }}
            </td>

            <td class="px-6 py-4 text-sm leading-5 text-gray-500 border-b border-gray-200 whitespace-nowrap">
                {{ statusItem.dailyWrite }}
            </td>

            <td 
            @click="goToBoard(statusItem.bbdIdOfFirstView, statusItem.ansIdOfFirstView)"
            :class="[statusItem.ansIdOfFirstView > 0 ? 'seperate-answer' : 'text-gray-500', statusItem.bbdIdOfFirstView == 0 ? '': 'cursor-pointer']"
            class="px-6 py-4 text-sm leading-5  border-b border-gray-200 whitespace-nowrap">
                {{ statusItem.bbdTitleOfFirstView }}
            </td>

            <td 
            @click="goToBoard(statusItem.bbdIdOfFirstAnswer, statusItem.ansIdOfFirstAnswer)"
            :class="[checkDate == '-' ? '': 'cursor-pointer']"
            class="px-6 py-4 text-sm leading-5 text-gray-500 border-b border-gray-200 whitespace-nowrap">
                {{ checkDate }}
            </td>
        </tr>
    </tbody>
</template>
<script>
import moment from 'moment';
import BoardApi from '@/api/BoardApi';

export default {
    name: 'statusListItem',
    props:{
        statusItem:{
            type: Object,
            required:true,
        }
    },
    computed:{
        checkDate(){
            let timeDiff = moment(this.statusItem.date).fromNow().slice(0,2)
            if(timeDiff == 'in'){
                return '-';
            }
            return this.statusItem.bbdTitleOfFirstAnswer;
        }
    },
    methods:{
        goToBoard(bbdId, ansId){
            if (bbdId > 0 && this.checkDate != '-') {
                if (ansId == 0) {
                    this.updateView(bbdId, ansId);
                    this.$router.push({
                    name: 'questionDetail',
                    params: { bbdId: bbdId, 
                            ansId: ansId }
                    });
                } else {
                    this.updateView(bbdId, ansId);
                    this.$router.push({
                    name: 'answerDetail',
                    params: { bbdId: bbdId, 
                            ansId: ansId }
                    });
                }
            }
        },

        async updateView(bbdId, ansId){
            await BoardApi.boardView(bbdId, ansId);
        }

        
    }
}
</script>
<style>
.seperate-answer{
    color: #FF4000;
}
.cursor-pointer{
    cursor: pointer;
}
</style>
