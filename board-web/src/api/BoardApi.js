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

    questionCreate(board){
        const formData = new FormData();

        formData.append('reg_writer', board.reg_writer);
        formData.append('bbd_title', board.bbd_title);
        formData.append('bbd_content', board.bbd_content);
        formData.append('bbd_password', board.bbd_password);
        formData.append('inq_security_yn', board.inq_security_yn);
        formData.append('bbd_attach_length', board.files.length);

        if(board.files.length > 0){
            for(let i=0; i<board.files.length; i++){
                const image = board.files[i];
                formData.append(`bbd_attach_${i+1}`, image);
            }
        }

        console.log(...formData);
        return instance.post(this.URL + '/question', formData)
                .then((response)=>response.data)
    }

    answerCreate(board){
        return instance.post(this.URL + '/answer', {...board})
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