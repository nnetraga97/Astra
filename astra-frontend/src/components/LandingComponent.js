import { AppBar, Toolbar, Typography, makeStyles, Button, Box } from "@material-ui/core";
import zIndex from "@material-ui/core/styles/zIndex";
import React, { useState } from "react";
import { Link as RouterLink } from "react-router-dom";
import Header from "./Header";
import './styles.css'




const useStyles = makeStyles(() => ({
  content: {
    position:"absolute",
    zIndex:1,
    width:400,
    height:400,
    justifyContent: "space-between",
    display: "flex",
    paddingTop: "300px",
    paddingLeft: "750px",
  },
  frontButtons: {
    fontFamily: "Open Sans, sans-serif",
    fontWeight: 200,
    size: "15px",
    marginLeft: "38px",
 },

 mainTypo:{
    fontFamily: "Open Sans, sans-serif",
    fontWeight: 200,
    size: "15px",
    fontSize:"25px"
 },
 logometrics:{
    position:"absolute",
    display:"flex",
    zIndex:1
 }
}));



export default function LandingComponent() {

    const {content,frontButtons,mainTypo,logometrics} = useStyles()

  return (
    <div className={content}>
        <Box
        sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            color:"#FFFFFF"
            
          }}>
              <Box>
                <Typography component="h1" variant="body1" className={mainTypo}>
                        Welcome to Astra! Astra is a collaboration platform that lets users access 
                        and update real-time documents.
                    </Typography>
                <Button
                {...{
                    key: 'Signin',
                    color: "inherit",
                    to: '/Login',
                    component: RouterLink,
                    className: frontButtons
                }}
                >
                <Typography component="h1" variant="body1" className={mainTypo}>
                        Sign in
                    </Typography>
                </Button>
                <Button
                {...{
                    key: 'Sign Up',
                    color: "inherit",
                    to: '/SignUp',
                    component: RouterLink,
                    className: frontButtons
                }}
                >
                <Typography component="h1" variant="body1" className={mainTypo}>
                        Sign Up
                    </Typography>
                </Button>
                <Button
                {...{
                    key: 'About',
                    color: "inherit",
                    to: '/About',
                    component: RouterLink,
                    className: frontButtons
                }}
                >
                <Typography component="h1" variant="body1" className={mainTypo}>
                        About
                    </Typography>
                </Button>
            </Box>

            
        </Box>
    </div>
  );
}