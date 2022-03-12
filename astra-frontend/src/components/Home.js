import React, { PureComponent } from 'react'
import {io} from 'socket.io-client'
import UserService from '../Services/UserService';


export default class Home extends PureComponent {
      constructor(props) {
        super(props);
        this.state={
          text:"",
          userid:0,
          boardid:0,
          username:"" 
        }
        this.createAndRoute = this.createAndRoute.bind(this)
      }

      createAndRoute(){
        console.log("Enteres create and route")
        document.getElementsByClassName('responseText').visible='true';
       UserService.createUser(document.getElementsByClassName('uname').text,document.getElementsByClassName('pwd').text).then(
         (res) =>{
           if(res.data.includes('error') || res.data.includes('exists')){
              document.getElementsByClassName('responseText').text = "Unable to create, check logs";
              console.log("data from createUser"+res.data)
           }
           else{
              document.getElementsByClassName('responseText').text = "User created with ID:"+res.data;
              this.state.userid = res.data;
              this.state.username = (document.getElementsByClassName('uname').text);
              console.log("data from createUser"+res.data)
           }
         }
       )
        UserService.createBoard(this.state.username,(document.getElementsByClassName('bname').text)).then(
          (res)=>{
            if(res.data.includes('no') || res.data.includes('to')){
              document.getElementsByClassName('responseText').text = "Unable to create, check logs";
              console.log("data from createBoard"+res.data)
           }
           else{
              document.getElementsByClassName('responseText').text = "Board created with ID:"+res.data;
              this.state.boardid = res.data;
              console.log("data from createBoard"+res.data)
           }
          }
        )

      }
    
  render() {
    return (
      <div>
        <div>Home</div>
        <input type="text" name='uname' className='uname'/>
        <input type="text" name='pwd' className='pwd'/>
        <input type="text" name='bname' className='bname'/>
        <button onClick={this.createAndRoute()}>Create User and new Doc</button>
        <label visible='false' className='resposnseText'>Can we see</label>
      </div>
    )
  }
}

