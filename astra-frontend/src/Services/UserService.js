import axios from 'axios';

const ORDER_API_BASE_URL = "http://localhost:8080/api/v1/";

class UserService {

    getUserbyUsername(username){
        return axios.get(ORDER_API_BASE_URL + 'user/' + username);
    }
    
    createUser(username,password){
        return axios.post(ORDER_API_BASE_URL + 'user/' + username+"/"+password);
    }

    deleteBoard(userId,boardid){
        return axios.post(ORDER_API_BASE_URL + 'board/' + userId+"/"+boardid);
    }

    createBoard(userId,boardid){
        return axios.post(ORDER_API_BASE_URL + 'user/createBoard/' + userId+"/"+boardid);
    }

    addBoard(userId,boardid){
        return axios.post(ORDER_API_BASE_URL + 'user/createBoard/' + userId+"/"+boardid);
    }

    removeBoard(userId,boardid){
        return axios.post(ORDER_API_BASE_URL + 'user/removeBoard/' + userId+"/"+boardid);
    }

}

export default new UserService()
