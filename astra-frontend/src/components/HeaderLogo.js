import React from 'react';
import Logoimg from "../resources/Astra-logos_white.png";
import {  Button, makeStyles } from "@material-ui/core";
import { Link as RouterLink } from "react-router-dom";

const useStyles = makeStyles(() => ({

    logometrics:{
      position:"fixed",
      display:"flex",
      zIndex:1
   },
   buttonmetrics:{
    position:"fixed",
    display:"flex", 
   }
  }));
  
export default function HeaderLogo() {
    const {logometrics,buttonmetrics} = useStyles() 
  
return (
    
    <div>
        <Button to="/" className={buttonmetrics} component={RouterLink}>
        <img src={Logoimg} className={logometrics} />
        </Button>
    </div>
  );
}