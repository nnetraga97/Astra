import React,{useState,useEffect} from 'react'
import Background from "./Background";
import Logo from './Logo';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import UserService from '../Services/UserService';

import Message from '../Services/Message';
import SockJS from 'sockjs-client'; 
import {Client, Message as IMessage,Stomp} from '@stomp/stompjs';
export default function DashBoard() {
  const currentBoard = window.sessionStorage.getItem('currentBoard');
  const userId = window.sessionStorage.getItem('userId');
  
  const [qdata,setQdata] = useState("");
  const [dataupdated,setdataUpdated ]= useState(false);




  const [socket,setSocket] = useState();
  useEffect(()=>{
    //Sockjs
    const sockJS = new SockJS('http://localhost:8080/websocket-sockjs',
    '*', {debug: true, transports: [],reconnectDelay:1500});

    sockJS.onopen = function(){
      console.log("SockJS client opened");
    }
    
    sockJS.onmessage = function(m){
      console.log(m);
      const resdata = m.data;
      if(!resdata.includes("one-time")){
        console.log(resdata);
        
        
      }
    }

   
    
    setSocket(sockJS);
    var stompClient = Stomp.over(sockJS);
    stompClient.connect({},function(frame){
      console.log("Stomp connected");
    })
  },[]);

  useEffect(()=>{},[dataupdated]);

 const handleChange = (data)=>{
   console.log(data);


     var tosend = new Message(userId,currentBoard,data);
     
   socket.send(JSON.stringify({userId:userId,
    boardid:currentBoard,
    data:data,
    create_dt:Date.now}));
  socket.send(tosend);
  };
return (
    
    <div>
        <div>
            <div className="container">
              <ReactQuill onChange={handleChange} 
              value={qdata} 
              />
            </div>
        </div>
        <Logo/>
    </div>
  );
}

