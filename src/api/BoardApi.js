import { createSchedulerInstance } from "@/api/Ingerceptors";

const instance = createSchedulerInstance();

class SchedulerApi{
    URL = '/bbd'

    boardList(){
        return instance.get(this.URL + '/boards')
                .then((response)=>response.data);
    }

    boardCreate(board){
        console.log(board)
        return instance.post(this.URL + '/boards', {...board})
                .then((response)=>response.data)
    }

   
}

export default new SchedulerApi();