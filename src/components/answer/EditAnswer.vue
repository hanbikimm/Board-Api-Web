<template>

    <div class="flex justify-end m-5">
        <button
        @click="open = true"
        class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
        답변 수정
        </button>
    </div>

<!--Body-->
<div>
    <div>
        <div class="mt-3">
            <label class="text-gray-700 ml-2">
                제목
            </label>
            <input
            class="block w-full p-2 my-1 border border-gray-300 rounded hover:border-gray-400 focus:outline-none focus:border-gray-400"
            type="text"
            v-model="answer.title"/>
        </div>

        <div class="mt-3">
            <label class="text-gray-700 ml-2">
                내용
            </label>
            <textarea id="message" rows="5"
            v-model="answer.contents"
            class="block p-2.5 w-full text-sm text-gray-900 rounded-md border border-gray-300 hover:border-gray-400 focus:outline-none focus:border-gray-400"></textarea>
        </div>

        <div class="mt-3">
            <label class="text-gray-700 ml-2">
                첨부
            </label>
            <input
            class="block w-full p-2 my-1 border border-gray-300 rounded hover:border-gray-400 focus:outline-none focus:border-gray-400"
            type="text"
            v-model="answer.files"/>
        </div>

        <div class="mt-3">
            <label class="text-gray-700 ml-2">
                비밀번호 (4자리 숫자)
            </label>
            <input
            class="block w-full p-2 my-1 border border-gray-300 rounded hover:border-gray-400 focus:outline-none focus:border-gray-400"
            type="password"
            v-model="answer.secret"/>
        </div>

        <div class="mt-3">
            <input class="ml-2 border border-gray-300"
            type="checkbox"
            v-model="answer.checked"/>
            <label class="text-gray-700 ml-2">
            조회 보안
            </label>
        </div>
        
    </div>

    <!--Footer-->
    <div class="flex justify-end pt-7">
        <button
            @click="itemsCheck()"
            class="px-6 py-3 text-white bg-blue-700 hover:bg-blue-600 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            수정
        </button>
        <button
            class="px-6 py-3 mx-2 text-blue-700 bg-transparent rounded-lg hover:bg-gray-100 hover:text-blue-600 focus:outline-none">
            취소
        </button>
    </div>
</div>
</template>
<script>
export default {
    name: 'editrAnswer',

    data() {
        return {
            show: false,

            answer:{
                title: '',
                contents: '',
                files: '',
                secret: '',
                checked: '',
            }
        }
    },

    methods: {
        itemsCheck(){
            alert("답변 수정이 완료되었습니다.");
            this.$router.go;
        },

    }
}
</script>
<style>
</style>