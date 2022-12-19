import { createSchedulerInstance } from "@/api/Ingerceptors";

const instance = createSchedulerInstance();

class SchedulerApi{
    URL = '/bbd'

    questionList(){
        return instance.get(this.URL + '/questions')
                .then((response)=>response.data);
    }

    answerList(id){
        return instance.get(this.URL + `/answers/${id}`)
                .then((response)=>response.data);
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

    


   
}

export default new SchedulerApi();