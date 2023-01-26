import { createInstance } from "@/api/Ingerceptors";
import axios from "axios";

const instance = createInstance();

class BoardApi{
    URL = '/bbd'

    totalBoard(){
        return axios.get(this.URL + '/total')
                .then((response)=>response.data)
    }

    questionList(){
        return axios.get(this.URL + '/questions')
                .then((response)=>response.data);
    }

    answerList(id){
        return axios.get(this.URL + `/answers/${id}`)
                .then((response)=>response.data);
    }

    searchList(searchWord, value){
        return axios.get(this.URL + `/boards/${searchWord}`, 
        {params:{
            value: value
        }})
    }

    postQuestion(board){
        return axios.post(this.URL + '/question/contents', {...board})
                .then((response)=>response.data)
    }

    postFile(formData){
        return instance.post(this.URL + '/board/file', formData)
                .then((response)=>response.data)
    }

    downloadFile(date, boardId, fileName){
        return axios.get(this.URL + `/attach/${date}/${boardId}/${fileName}`)
                .then((response)=>response.data)
    }

    postAnswer(board){
        return axios.post(this.URL + '/answer/contents', {...board})
                .then((response)=>response.data)
    }

    boardDelete(bbdId, ansId){
        return axios.delete(this.URL + `/board/${bbdId}`, 
                { params:{
                    bbdId: bbdId, 
                    ansId: ansId 
                }})
                .then((response)=>response.data)
    }

    boardEdit(bbdId, board){
        return axios.put(this.URL + `/board/${bbdId}`, {...board})
                .then((response)=>response.data)
    }

    boardDetail(bbdId, ansId){
        return axios.get(this.URL + `/board/${bbdId}`,
        { params:{
            bbdId: bbdId, 
            ansId: ansId 
        }})
                .then((response)=>response.data)
    }

    boardView(bbdId, ansId){
        return axios.put(this.URL + '/board/view', null,
        { params:{
            bbdId: bbdId, 
            ansId: ansId 
        }})
                .then((response)=>response.data)
    }

    boardStatus(date){
        return axios.get(this.URL + `/status/${date}`)
                .then((response)=>response.data)
    }

    statusGenerate(date){
        return axios.get(this.URL + `/generate/${date}`)
                .then((response)=>response.data)
    }
}

export default new BoardApi();