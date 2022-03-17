import React,{useState,useEffect} from 'react'
import Background from "./Background";
import Logo from './Logo';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import UserService from '../Services/UserService';
import {io} from 'socket.io-client'
import Message from '../Services/Message';

export default function DashBoard() {
  const [currentBoard,setcurrentBoard] = useState();
  const [userId,setuserId] = useState();
  const [boardData,setboardData] = useState();
  const [socket,setsocket] = useState(null);
  useEffect(()=>{
    setcurrentBoard(window.sessionStorage.getItem('currentBoard'));
    setuserId(window.sessionStorage.getItem('userId'));
    setboardData("Hello");
    const currsocket = io("ws://localhost:8083");
    setsocket(currsocket);
    const tosend = new Message(currentBoard,userId,boardData);
    console.log(currsocket);
    currsocket.emit("get-board-data",tosend);
  },[]);

  
return (
    
    <div>
        <div>
            <div className="container">
              <ReactQuill            
              />
            </div>
        </div>
        <Logo/>
    </div>
  );
}

