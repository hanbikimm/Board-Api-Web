<template>
    <div>
        <div class="mx-2">
            <button
                @click="confirmDelete()"
                class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                삭제
            </button>
        </div>
    </div>
</template>
<script>
export default {
    name: 'deleteBoard',
    methods:{
        confirmDelete(){
            confirm('정말로 삭제하시겠습니까?');
            alert('성공적으로 삭제되었습니다.');
            this.$router.go();
        }
    }
}
</script>
<style>
</style>