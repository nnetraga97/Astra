import React from 'react'
import { makeStyles, Paper } from '@material-ui/core';
import img from "../resources/Background.mp4";
const useStyles = makeStyles(() => ({
    video: {
      position:"fixed",
      zIndex:-1,
      width:1900,
      justifyContent: "center",
      display: "flex",
    },
    
  }));
export default function Background() {

    const {video} = useStyles()

  return (
      
   
        <Paper elevation={0}>
        <video autoPlay loop muted id='video' className={video}>
                <source src={img}
                type="video/mp4"/>
            </video>
        </Paper>
      
  );
}