import {io} from 'socket.io-client'
import Message from "./Message";


export default function BoardServices(){

const socket=io("ws://localhost:8083")
// send a message to the server
const tosend = new Message("1","1","");

socket.emit("update-board-data", tosend);

// receive a message from the server
socket.on("send-board-data", (...args) => {
    window.sessionStorage.setItem('res',args);
    });

    socket.on("connection", (socket) => {
        // send a message to the client
        socket.emit("update-board-data", tosend);
      
        // receive a message from the client
        socket.on("send-board-data", (...args) => {
          // ...
        });
      });

}
