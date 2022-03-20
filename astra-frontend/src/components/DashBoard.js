import React,{useState,useEffect} from 'react'
import Background from "./Background";
import Logo from './Logo';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import UserService from '../Services/UserService';

import Message from '../Services/Message';
import SockJS from 'sockjs-client'; 
import {Client, Message as IMessage,StompJs} from '@stomp/stompjs';

export default function DashBoard() {
  const currentBoard = window.sessionStorage.getItem('currentBoard');
  const userId = window.sessionStorage.getItem('userId');

  var socket = null;
  useEffect(()=>{
    //Sockjs
    const sockJS = new SockJS('http://localhost:8080/websocket-sockjs',
    '*', {debug: true, transports: [],reconnectDelay:1500});

    sockJS.onopen = function(){
      console.log("client connected");
      console.log('Subprotocol: ' + sockJS.protocol);
      console.log('Extensions: ' + sockJS.extensions);
    }
    
    sockJS.onmessage = function(m){
      console.log(m);
    }
    
    socket = sockJS;
    
  },[]);

 const handleChange = (event)=>{
   console.log(event);
   var tosend = new Message(userId,currentBoard,event);

   socket.send(JSON.stringify({userId:userId,
    boardid:currentBoard,
    data:event,
    create_dt:Date.now}));
  //socket.send(tosend);
 };
return (
    
    <div>
        <div>
            <div className="container">
              <ReactQuill onChange={handleChange}            
              />
            </div>
        </div>
        <Logo/>
    </div>
  );
}

