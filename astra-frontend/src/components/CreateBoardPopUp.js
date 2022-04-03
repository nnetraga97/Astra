import React, { useRef, useState,useEffect } from 'react'
import PropTypes from 'prop-types';
import Button from '@mui/material/Button';

import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';


import Typography from '@mui/material/Typography';
import { blue } from '@mui/material/colors';
import TextField from '@mui/material/TextField';
import UserService from '../Services/UserService';


export default function CreateBoardPopUp(props) {
    const { onClose, selectedValue, open,listUpdated,setlistUpdated} = props;
    const[userId,setuserId] = React.useState(window.sessionStorage.getItem('userId'));
    const[name,setName] = useState("Untitled");


    const handleClose = () => {
        onClose(selectedValue)
      };
  const handleClickOpen = (value) => {
    //call user service
        console.log(value);
        if(value===null){
        console.log("undefined text");
        return;}
        UserService.createBoard(userId, name).then((res)=>{
          let data = String(res.data);
          if(data===null||value===undefined){
            
            return;
          }
          else{
            if(data.includes("unable")){
                console.log("unable to create");
            }
            else if(data.includes("no user")){
              console.log("no user found");
            }
            else{
                console.log(data);
                 setlistUpdated(true);
            }
          }
        });
    
  };
  return (
    <Dialog onClose={handleClose} open={open}>
      <DialogTitle>Creating a New Board</DialogTitle>
      <DialogContent>
          <DialogContentText>
            Please Enter a name for your DashBoard.
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="DashBoard"
            type="text"
            fullWidth
            variant="standard"
            onChange={(value)=>{setName(value)}}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleClickOpen()}>Create!</Button>
        </DialogActions>
    </Dialog>
  );
}




