import { createSchedulerInstance } from "@/api/Ingerceptors";

const instance = createSchedulerInstance();

class SchedulerApi{
    URL = '/bbd'

    boardList(){
        return instance.get(this.URL + '/boards')
                .then((response)=>response.data);
    }

    boardCreate(board){
        return instance.post(this.URL + '/boards', {...board})
                .then((response)=>response.data)
    }

    boardDelete(bbdId, ansId){
        console.log(ansId)
        return instance.delete(this.URL + `/board/${bbdId}`, 
                { params:{
                    bbdId: bbdId, 
                    ansId: ansId 
                }})
                .then((response)=>response.data)
    }

    boardDetail(id){
        return instance.get(this.URL + `/board/${id}`)
                .then((response)=>response.data)
    }

   
}

export default new SchedulerApi();