<template>
<div>
    <div class="z-50 w-11/12 mx-auto overflow-y-auto bg-white rounded shadow-lg modal-container md:max-w-3xl">
        <div class="px-6 py-4 text-left modal-content">
            <!--Title-->
            <div class="flex items-center justify-between pb-3">
                
                <p class="text-2xl font-bold mt-2">({{ this.question.id }}) {{ this.question.title }}</p>
                <div class="z-50 cursor-pointer modal-close" @click="goToBoardList()">
                    <svg
                        class="text-black fill-current"
                        xmlns="http://www.w3.org/2000/svg"
                        width="18"
                        height="18"
                        viewBox="0 0 18 18">
                        <path d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z" />
                    </svg>
                </div>
            </div> 
            
            <!--Body-->
            <div>
                <p class="text-lg font-bold">{{ this.question.writer }}</p>
                <p class="text-sm">
                    {{ this.question.date }} &nbsp; 조회 {{ this.question.views }}
                </p>
                <hr class="mt-4"/>
            </div>
            <div>
                <p class="mt-4">{{ this.question.contents }}</p>
                <p>{{ this.question.files }}</p>
                <hr class="mt-4"/>
            </div>

            <div class="flex justify-end pt-7">
            
                <editQuestion/> <deleteBoard/>
                <div class="flex justify-end pt-7 ml-3">
                    <button
                    @click="formShow"
                    class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                    답변 등록
                    </button>
                </div>
            </div>

            <div v-if="show">
                <div class="">
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
                        등록
                    </button>
                    <button
                        @click="formShow"
                        class="px-6 py-3 mx-2 text-blue-700 bg-transparent rounded-lg hover:bg-gray-100 hover:text-blue-600 focus:outline-none">
                        취소
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</template>
<script>
import deleteBoard from '../modal/DeleteBoard.vue';
import editQuestion from './EditQuestion.vue';

export default {
    name: "questionDetail",
    components: {
    editQuestion,
    deleteBoard
},
    
    data() {
        return {
            show: false,

            answer:{
                title: '',
                contents: '',
                files: '',
                secret: '',
                checked: '',
            },

            question: {
                id: '2023', 
                writer: '김한비', 
                date: '2022-11-31 10:10:10', 
                views: 5,
                title: '어려운 질문', 
                contents: '어려운 질문인데요...... 컴퓨터 개발 환경은 어떻게 하면 쉽게 세팅을 할까요???',
                files: 'file1.jpg'
            }
        };
    },

    methods: {
        goToBoardList(){
            this.$router.push({
            name: 'boardList',
            })
        },

        itemsCheck(){
            alert("답변 등록이 완료되었습니다.");
            this.$router.go;
            this.formShow();
        },

        formShow(){
            this.show = !this.show;
        }
    },
    
}
</script>
<style>
</style>