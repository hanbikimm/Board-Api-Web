<template>
    <div>
        <div>
            <hr class="my-3"/>
            <p class="text-lg font-bold">({{this.answer.ans_seq}}) {{ this.answer.bbd_title }}</p>
            <p class="text-sm">
                {{ this.answer.reg_writer }} &nbsp; {{ this.answer.reg_datetime }} &nbsp; 조회 {{ this.answer.total_views }} &nbsp;
            </p>
            
        </div>

    </div>
</template>
<script>
export default {
    name: 'answerList',

    props:{
        answer:{
            type: Object,
            required: true
        }
    }
}
</script>
<style>
    
</style>