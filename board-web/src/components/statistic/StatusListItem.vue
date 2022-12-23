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
            class="cursor-pointer px-6 py-4 text-sm leading-5 text-gray-500 border-b border-gray-200 whitespace-nowrap">
                {{ statusItem.bbdTitleOfFirstView }}
            </td>

            <td 
            @click="goToBoard(statusItem.bbdIdOfFirstAnswer, statusItem.ansIdOfFirstAnswer)"
            class="cursor-pointer px-6 py-4 text-sm leading-5 text-gray-500 border-b border-gray-200 whitespace-nowrap">
                {{ statusItem.bbdTitleOfFirstAnswer }}
            </td>
        </tr>
    </tbody>
</template>
<script>
export default {
    name: 'statusListItem',
    props:{
        statusItem:{
            type: Object,
            required:true,
        }
    },
    methods:{
        goToBoard(bbdId, ansId){
            this.$router.push({
                    name: 'questionDetail',
                    params: { bbdId: bbdId, 
                            ansId: ansId }
                    });
        }
    }
}
</script>
