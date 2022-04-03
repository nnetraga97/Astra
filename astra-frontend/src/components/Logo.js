import React from 'react';
import Logoimg from "../resources/Astra-logos_white.png";
import {  Button, makeStyles } from "@material-ui/core";
import { Link as RouterLink } from "react-router-dom";

const useStyles = makeStyles(() => ({

    logometrics:{
      position:"fixed",
      display:"flex",
      height:130,
      width:155,
      zIndex:1
   },
   buttonmetrics:{
    position:"fixed",
    display:"flex",
    paddingTop:"80px",
    paddingLeft:"80px",
    
   }
  }));
  
export default function Logo() {
    const {logometrics,buttonmetrics} = useStyles() 
  
return (
    
    <div>
        <Button to="/" className={buttonmetrics} component={RouterLink}>
        <img src={Logoimg} className={logometrics} />
        </Button>
    </div>
  );
}