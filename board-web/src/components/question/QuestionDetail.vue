<template>
<div>
    <div class="z-50 w-11/12 mx-auto overflow-y-auto bg-white rounded shadow-lg modal-container md:max-w-3xl">
        <div class="px-6 py-4 text-left modal-content">
            <!--Title-->
            <div class="flex items-center justify-between pb-3">
                
                <p class="text-2xl font-bold mt-2">({{ this.question.bbd_seq }}) {{ this.question.bbd_title }}</p>
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
                <p class="text-lg font-bold">{{ this.question.reg_writer }}</p>
                <p class="text-sm">
                    {{ this.question.reg_datetime }} &nbsp; 조회 {{ this.question.total_views }} &nbsp; 답변 {{ this.question.answer_count }}
                </p>
                <hr class="mt-4"/>
            </div>
            <div>
                <p class="mt-4 content">{{ this.question.bbd_content }}</p>
                <p>{{ this.question.bbd_attach_1 }}</p>
                <hr class="mt-4"/>
            </div>

            <div class="flex justify-end pt-7">
            
                <editBoard
                    v-bind:defaultBoard="question"/>
                <div class="flex justify-end pt-7 ml-3">
                    <button
                        @click="deleteBoard()"
                        class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                        삭제
                    </button>
                </div>
                <div class="flex justify-end pt-7 ml-3">
                    <button
                    @click="formShow"
                    class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                    답변 등록
                    </button>
                </div>
            </div>

            <div v-if="show">
                <div>
                    <div class="mt-3">
                        <label class="text-gray-700 ml-2">
                            작성자
                        </label>
                        <input
                            class="block w-full p-2 my-1 border border-gray-300 rounded hover:border-gray-400 focus:outline-none focus:border-gray-400"
                            type="text"
                            v-model="board.reg_writer"/>

                        <label class="text-gray-700 ml-2">
                            제목
                        </label>
                        <input
                            class="block w-full p-2 my-1 border border-gray-300 rounded hover:border-gray-400 focus:outline-none focus:border-gray-400"
                            type="text"
                            v-model="board.bbd_title"/>

                        <label class="text-gray-700 ml-2">
                            내용
                        </label>
                        <textarea id="message" rows="5"
                            v-model="board.bbd_content"
                            class="block p-2.5 w-full text-sm text-gray-900 rounded-md border border-gray-300 hover:border-gray-400 focus:outline-none focus:border-gray-400"></textarea>

                        <label class="text-gray-700 ml-2">
                            첨부
                        </label>
                        <input
                            class="block w-full p-2 my-1 border border-gray-300 rounded hover:border-gray-400 focus:outline-none focus:border-gray-400"
                            type="text"
                            v-model="board.bbd_attach_1"/>

                        <label class="text-gray-700 ml-2">
                            비밀번호 (4자리 숫자)
                        </label>
                        <input
                            class="block w-full p-2 my-1 border border-gray-300 rounded hover:border-gray-400 focus:outline-none focus:border-gray-400"
                            type="password"
                            v-model="board.bbd_password"/>

                        <input class="ml-2 border border-gray-300"
                            type="checkbox"
                            v-model="board.inq_security_yn"/>
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

            <div>
                <answerList
                    class="cursor-pointer"
                    v-for="answer in this.answers" :key="answer.ans_seq"
                    :answer="answer"
                    @click="goToAnswerDetail(answer.bbd_seq, answer.ans_seq, answer.inq_security_yn, answer.bbd_password)"/>
            </div>
            
        </div>
    </div>
</div>
</template>
<script>
import BoardApi from '@/api/BoardApi';
import answerList from '@/components/answer/AnswerList.vue';
import editBoard from '../board/EditBoard.vue';

export default {
    name: "questionDetail",
    components: {
    editBoard,
    answerList
},
    
    data() {
        return {
            show: false,
            question: {},
            answers:[],
            board:{}
            
        };
    },

    computed:{
        content(){
            return this.question.bbd_content.split('\n').join('<br>');
        }
    },

    methods: {
        async getQuestionDetail(){
            try{
                const data = await BoardApi.boardDetail(this.$route.params.bbdId, this.$route.params.ansId);
                this.question = data;
            }catch(error){
                console.log(error);
            }
        },

        async getAnswerList(){
            try{
                const data = await BoardApi.answerList(this.$route.params.bbdId);
                this.answers = data;
            }catch(error){
                console.log(error);
            }
        },

        async deleteBoard(){
            
            try{
                if(confirm('정말로 삭제하시겠습니까?')){
                     const input = prompt('비밀번호를 입력하세요.', '4자리 숫자');
                    if(input === this.question.bbd_password){
                        await BoardApi.boardDelete(this.question.bbd_seq, this.question.ans_seq);
                        alert('성공적으로 삭제되었습니다.');
                        this.$router.push({name: 'boardList'});
                    } else if(input != this.question.bbd_password){
                        alert('비밀번호가 틀렸습니다!');
                    }
                }
                
            }catch(error){
                console.log(error);
            }
            
        },

        goToAnswerDetail(bbdId, ansId, security, password){
            if (security == 'y') {
                const input = prompt('비밀번호를 입력하세요.', '4자리 숫자')
                if (input === password) {
                    this.$router.push({
                    name: 'answerDetail',
                    params: { bbdId: bbdId, 
                            ansId: ansId }
                    });
                } else if(input != password) {
                    alert('비밀번호가 틀렸습니다!');
                    this.getQuestionList();
                }
            } else {
                this.$router.push({
                    name: 'answerDetail',
                    params: { bbdId: bbdId, 
                            ansId: ansId }
                });
            }
        },

        goToBoardList(){
            this.$router.push({
            name: 'boardList',
            });
        },

        itemsCheck(){
        if(this.board.reg_writer == null || this.board.bbd_title == null || 
          this.board.bbd_content == null || this.board.bbd_password == null){
            alert("항목을 다 입력했는지 확인해주세요!")
        } else{
            if(this.board.inq_security_yn == true){
            this.board.inq_security_yn = 'y';
            } else{
            this.board.inq_security_yn = 'n';
            }
            this.registerAnswer();
            }   
            
        },

        async registerAnswer(){
          try {
            this.board.bbd_seq = this.$route.params.bbdId;
            await BoardApi.answerCreate(this.board);
            alert("답변 등록이 완료되었습니다!");
            this.$router.go();
          } catch (error) {
            console.log(error);
          }
          
        },

        formShow(){
            this.show = !this.show;
        }
    },

    mounted(){
        this.getQuestionDetail();
        this.getAnswerList();
    }
    
}
</script>
<style>
.content {
  white-space: pre-line
}
</style>