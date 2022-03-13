import { AppBar, Toolbar, Typography, makeStyles, Button } from "@material-ui/core";
import zIndex from "@material-ui/core/styles/zIndex";
import React, { useState } from "react";
import { Link as RouterLink } from "react-router-dom";
import Header from "./Header";
import './styles.css'
import img from "../resources/Background.mp4";
import LandingComponent from "./LandingComponent";
import Logo from "./Logo";
import Background from "./Background";


const useStyles = makeStyles(() => ({
  video: {
    position:"fixed",
    zIndex:-1,
    width:1900,
    justifyContent: "center",
    display: "flex",
  },
  logometrics:{
    position:"fixed",
    display:"flex",
    height:200,
    width:200,

    zIndex:1
 }
}));



export default function LandingPage() {

    const {video,logometrics} = useStyles()

  return (
      
    <div>
        <div>
            <Background/>
            <LandingComponent/>
            
        </div>
        <Logo/>
    </div>
  );
}