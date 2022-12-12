<template>
    <!-- Paginations -->
      <div class="mt-5">
        <div class="flex justify-center px-4 py-4 overflow-x-auto rounded-md">
          <div class="flex mr-4 rounded">
            <button
              @click="firstPage()"
              class="px-3 py-2 ml-0 leading-tight text-blue-700 bg-transparent border border-r-0 border-gray-200 rounded-l hover:bg-gray-100 hover:text-blue-600">
              처음으로
            </button>
            <button
              @click="prevPage()"
              class="px-3 py-2 leading-tight text-blue-700 bg-transparent  border border-r-0 border-gray-200 hover:bg-gray-100 hover:text-blue-600">
              이전
            </button>
            <button
              v-for="page in setPages" :key="page"
              :class="[page === this.paging.nowPage ? 'selected' : '']"
              class="px-3 py-2 leading-tight text-blue-700 bg-transparent  border border-r-0 border-gray-200 hover:bg-gray-100 hover:text-blue-600"
              @click="changeNowPage(page)">
              {{ page }}
            </button>
            <button
              @click="nextPage()"
              class="px-3 py-2 leading-tight text-blue-700 bg-transparent  border border-r-0 border-gray-200 hover:bg-gray-100 hover:text-blue-600">
              다음
            </button>
            <button
              @click="lastPage()"
              class="px-3 py-2 leading-tight text-blue-700 bg-transparent border border-gray-200 rounded-r hover:bg-gray-100 hover:text-blue-600 ">
              끝으로
            </button>
          </div>
        </div>
      </div>
</template>

<script>
export default {
    name: 'BoardPagination',
    props:['paging'],
    data(){
      return{
      }
    },
    computed: {
      // 화면에 표시할 페이지 배열
      setPages() {
        let pages = [];
        for(let num = this.paging.startPage; num <= this.paging.endPage; num++){
          pages.push(num);
        }
        return pages;
      },
    },
    methods:{
      prevPage(){ 
        this.$emit("prevPage"); 
      },
      nextPage(){ 
        this.$emit("nextPage"); 
      },
      firstPage(){ 
        this.$emit("firstPage"); 
      },
      lastPage(){ 
        this.$emit("lastPage"); 
      },
      changeNowPage(page){
        this.$emit("changeNowPage", page)
      }
    },
};
</script>

<style>
button.selected{
  background-color: #4338CA;
  color: white;
}
</style>