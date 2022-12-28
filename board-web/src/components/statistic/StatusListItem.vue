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
            :class="[statusItem.ansIdOfFirstView > 0 ? 'seperate-answer' : ''] && [statusItem.bbdIdOfFirstView == 0 ? '': 'cursor-pointer']"
            class="px-6 py-4 text-sm leading-5 text-gray-500 border-b border-gray-200 whitespace-nowrap">
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
            if (bbdId > 0) {
                this.$router.push({
                    name: 'questionDetail',
                    params: { bbdId: bbdId, 
                            ansId: ansId }
                    });
            }
            
        },

        
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
