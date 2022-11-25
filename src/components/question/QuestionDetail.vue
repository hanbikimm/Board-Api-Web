<template>
<div>
    <div>
        <div class="mx-2">
            <button
            @click="open = true"
            class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            조회 등록
            </button>
        </div>

        <div
        :class="`modal ${
            !open && 'opacity-0 pointer-events-none'
        } z-50 fixed w-full h-full top-0 left-0 flex items-center justify-center`">
            <div
                @click="open = false"
                class="absolute w-full h-full bg-gray-900 opacity-50 modal-overlay">
            </div>

            <div class="z-50 w-11/12 mx-auto overflow-y-auto bg-white rounded shadow-lg modal-container md:max-w-3xl">
                <div class="px-6 py-4 text-left modal-content">
                    <!--Title-->
                    <div class="flex items-center justify-between pb-3">
                        
                        <p class="text-2xl font-bold mt-2">{{ this.question.title }}</p>
                        <div class="z-50 cursor-pointer modal-close" @click="open = false">
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
                    <div>
                        <registerAnswer/>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</template>
<script>
import registerAnswer from '../answer/RegisterAnswer.vue';

export default {
    name: "questionDetail",
    components: { 
        registerAnswer
    },
    
    data() {
        return {
            open: false,
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
    methods: {},
    
}
</script>
<style>
</style>