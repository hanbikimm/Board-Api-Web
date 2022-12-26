import { createSchedulerInstance } from "@/api/Ingerceptors";

const instance = createSchedulerInstance();

class SchedulerApi{
    URL = '/bbd'

    totalBoard(){
        return instance.get(this.URL + '/total')
                .then((response)=>response.data)
    }

    questionList(){
        return instance.get(this.URL + '/questions')
                .then((response)=>response.data);
    }

    answerList(id){
        return instance.get(this.URL + `/answers/${id}`)
                .then((response)=>response.data);
    }

    searchList(searchWord, value){
        return instance.get(this.URL + `/boards/${searchWord}`, 
        {params:{
            value: value
        }})
    }

    questionCreate(board){
        return instance.post(this.URL + '/question', {...board})
                .then((response)=>response.data)
    }

    answerCreate(board){
        return instance.post(this.URL + '/answer', {...board})
                .then((response)=>response.data)
    }

    boardDelete(bbdId, ansId){
        return instance.delete(this.URL + `/board/${bbdId}`, 
                { params:{
                    bbdId: bbdId, 
                    ansId: ansId 
                }})
                .then((response)=>response.data)
    }

    boardEdit(bbdId, board){
        return instance.put(this.URL + `/board/${bbdId}`, {...board})
                .then((response)=>response.data)
    }

    boardDetail(bbdId, ansId){
        return instance.get(this.URL + `/board/${bbdId}`,
        { params:{
            bbdId: bbdId, 
            ansId: ansId 
        }})
                .then((response)=>response.data)
    }

    boardView(bbdId, ansId){
        return instance.put(this.URL + '/board/view', null,
        { params:{
            bbdId: bbdId, 
            ansId: ansId 
        }})
                .then((response)=>response.data)
    }

    


   
}

export default new SchedulerApi();