<template>
<div>
    <div class="z-50 w-11/12 mx-auto overflow-y-auto bg-white rounded shadow-lg modal-container md:max-w-3xl">
        <div class="px-6 py-4 text-left modal-content">
            <!--Title-->
            <div class="flex items-center justify-between pb-3">
                <p class="text-2xl font-bold mt-2">({{ this.answer.bbd_seq }}-{{ this.answer.ans_seq }}) {{ this.answer.bbd_title }}</p>
                <div class="z-50 cursor-pointer modal-close" @click="goBack()">
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
                <p class="text-lg font-bold">{{ this.answer.reg_writer }}</p>
                <p class="text-sm">
                    {{ this.answer.reg_datetime }} &nbsp; 조회 {{ this.answer.total_views }}
                </p>
                <hr class="mt-4"/>
            </div>
            <div >
                <p class="mt-4 content">{{ this.answer.bbd_content }}</p>

                <button v-if="this.load.file_1 != ''" @click="downloadFile(this.load.file_1)"
                class="mt-4 block focus:outline-none border border-gray-300 font-medium rounded-full text-sm px-5 py-2.5 text-center">
                    {{ this.load.file_1 }}
                </button>
                <button v-if="this.load.file_2 != ''" @click="downloadFile(this.load.file_2)"
                class="mt-1 block focus:outline-none border border-gray-300 font-medium rounded-full text-sm px-5 py-2.5 text-center">
                    {{ this.load.file_2 }}
                </button>
                <button v-if="this.load.file_3 != ''" @click="downloadFile(this.load.file_3)"
                class="mt-1 block focus:outline-none border border-gray-300 font-medium rounded-full text-sm px-5 py-2.5 text-center">
                    {{ this.load.file_3 }}
                </button>
                <button v-if="this.load.file_4 != ''" @click="downloadFile(this.load.file_4)"
                class="mt-1 block focus:outline-none border border-gray-300 font-medium rounded-full text-sm px-5 py-2.5 text-center">
                    {{ this.load.file_4 }}
                </button>
                <button v-if="this.load.file_5 != ''" @click="downloadFile(this.load.file_5)"
                class="mt-1 block focus:outline-none border border-gray-300 font-medium rounded-full text-sm px-5 py-2.5 text-center">
                    {{ this.load.file_5 }}
                </button>
                <hr class="mt-4"/>
            </div>

            <div class="flex justify-end pt-7">
            
                <editBoard
                    v-bind:defaultBoard="answer"/>
                <div class="flex justify-end pt-7 ml-3">
                    <button
                        @click="deleteBoard()"
                        class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                        삭제
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


</template>
<script>
import editBoard from '../board/EditBoard.vue';
import BoardApi from '@/api/BoardApi';

export default {
    name: "answerDetail",
    components: {
    editBoard,
},
    
    data() {
        return {
            open: false,
            answer: {},
            load:{
                file_1: '',
                file_2: '',
                file_3: '',
                file_4: '',
                file_5: '',
            }
        };
    },

    methods: {
        goBack(){
            this.$router.go(-1);
        },

        downloadFile(fileName){
            try {
                const date = this.answer.reg_datetime.slice(0,10);
                const boardId = `${this.answer.bbd_seq}-${this.answer.ans_seq}`;
                window.open(`http://localhost:8000/bbd/attach/${date}/${boardId}/${fileName}`);
            } catch (error) {
                console.log(error);
            }
        },

        async getAnswerDetail(){
            try{
                const data = await BoardApi.boardDetail(this.$route.params.bbdId, this.$route.params.ansId);
                this.answer = data;
                if(this.answer.bbd_attach_1 != null){
                    this.load.file_1 = this.answer.bbd_attach_1.substring(42, this.answer.bbd_attach_1.length);
                    if(this.answer.bbd_attach_2 != null){
                        this.load.file_2 = this.answer.bbd_attach_2.substring(42, this.answer.bbd_attach_2.length);
                        if(this.answer.bbd_attach_3 != null){
                            this.load.file_3 = this.answer.bbd_attach_3.substring(42, this.answer.bbd_attach_3.length);
                            if(this.answer.bbd_attach_4 != null){
                                this.load.file_4 = this.answer.bbd_attach_4.substring(42, this.answer.bbd_attach_4.length);
                                if(this.answer.bbd_attach_5 !=null){
                                    this.load.file_5 = this.answer.bbd_attach_5.substring(42, this.answer.bbd_attach_5.length);
                                }
                            }
                        }
                    }
                }
            }catch(error){
                console.log(error);
            }
        },

        async deleteBoard(){
            try{
                if(confirm('정말로 삭제하시겠습니까?')){
                     const input = prompt('비밀번호를 입력하세요.', '4자리 숫자');
                    if(input === this.answer.bbd_password){
                        const message = await BoardApi.boardDelete(this.answer.bbd_seq, this.answer.ans_seq);
                        alert(`${message}`);
                        this.goBack();
                    } else if(input != this.answer.bbd_password){
                        alert('비밀번호가 틀렸습니다!');
                    }
                }
                
            }catch(error){
                console.log(error);
            }
        },
    },

    mounted(){
        this.getAnswerDetail();
    }
    
}
</script>
<style>
.content {
  white-space: pre-line
}
</style>