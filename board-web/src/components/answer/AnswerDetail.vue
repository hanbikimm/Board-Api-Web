<template>
<div>
    <div class="z-50 w-11/12 mx-auto overflow-y-auto bg-white rounded shadow-lg modal-container md:max-w-3xl">
        <div class="px-6 py-4 text-left modal-content">
            <!--Title-->
            <div class="flex items-center justify-between pb-3">
                
                <p class="text-2xl font-bold mt-2">({{ this.question.id }}) {{ this.question.title }}의 답변</p>
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

            <div>
                <p class="text-xl font-bold mt-5">{{ this.answer.title }}</p>
                <hr class="mt-2"/>
            </div>

            <!--Body-->
            <div>
                <p class="text-base font-bold mt-2">{{ this.answer.writer }}</p>
                <p class="text-sm">
                    {{ this.answer.date }} &nbsp; 조회 {{ this.answer.views }}
                </p>
                <hr class="mt-2"/>
            </div>
            <div>
                <p class="mt-4">{{ this.answer.contents }}</p>
                <p>{{ this.answer.files }}</p>
            </div>

            <!--Footer-->
            <div class="flex justify-end pt-7">
                <editAnswer/> <deleteBoard/>
            </div>
        </div>
    </div>
</div>


</template>
<script>
import editAnswer from '@/components/answer/EditAnswer.vue';
import deleteBoard from '@/components/modal/DeleteBoard.vue';

export default {
    name: "answerDetail",
    components: {
    editAnswer,
    deleteBoard,
},
    
    data() {
        return {
            open: false,
            question: {
                id: '2023', 
                writer: '김한비', 
                title: '어려운 질문', 
            },
            answer: {
                writer: '민윤기',
                date: '2022-11-31 10:15:10',
                views: '1',
                title: '별로 안어렵네요',
                contents: '전문가에게 문의해서 도움을 받아보세요~~',
                files: '답변 사진.jpg'

            }
        };
    },
    methods: {
        goToBoardList(){
            this.$router.push({
            name: 'boardList',
            })
        },
    },
    
}
</script>
<style>
</style>