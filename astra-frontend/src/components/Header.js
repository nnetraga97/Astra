import 'react-devtools';
import { AppBar, Toolbar, Typography, makeStyles, Button, Paper } from "@material-ui/core";
import { formHelperTextClasses } from "@mui/material";
import React, { useRef, useState,useEffect } from 'react'
import { Link as RouterLink } from "react-router-dom";
import Logoimg from "../resources/Astra-logos_white.png";
import UserService from "../Services/UserService";
import CreateBoardPopUp from "./CreateBoardPopUp";
import PropTypes from 'prop-types';
const useStyles = makeStyles(() => ({
  header: {
    backgroundColor: "#400CCC",
    paddingRight: "19px",
    paddingLeft: "18px",
  },
  logometrics:{
    display:"flex",
    height:65,
    width:100,
    zIndex:0
  },
  buttonmetrics:{
    position:"fixed",
    display:"flex",
    paddingTop:"0px",
    paddingLeft:"0px",
    
    
  },
  menuButton: {
    fontFamily: "Open Sans, sans-serif",
    fontWeight: 500,
    size: "18px",
    marginLeft: "38px",
 },
 toolbar: {
    display: "flex",
    flexDirection:"row",
    justifyContent: "space-between",
  },
}));

CreateBoardPopUp.propTypes = {
  onClose: PropTypes.func.isRequired,
  open: PropTypes.bool.isRequired,
  selectedValue: PropTypes.string.isRequired,
};

export default function Header() {

  const { header,logometrics,buttonmetrics,menuButton,toolbar } = useStyles();

  const [open, setOpen] = useState(false);
  const [selectedValue, setSelectedValue] = useState();

  const handleClickOpen = () => {
    setOpen(true);
    console.log(open);
  };

  const handleClose = (value) => {
    setOpen(false);
    setSelectedValue(value);
  };
  const displayDesktop = () => {
    return <Toolbar className={toolbar}>
     
        
      <div>
      
      
      <CreateBoardPopUp
        selectedValue={selectedValue}
        open={open}
        onClose={handleClose}
      />

      <Button
          {...{
            key: "logo",
            to: "/",
            component: RouterLink,
            className: menuButton,
          }}
        >
          <img src={Logoimg} className={logometrics} />
        </Button>
      <Button
          {...{
            key: "home",
            color: "inherit",
            to: "/",
            component: RouterLink,
            className: menuButton
          }}
        >
        Home
        </Button>
        <Button
          {...{
            key: "CreateBoard",
            color: "inherit",
            className: menuButton,
            onClick:handleClickOpen
          }}
        >
          Create Board
        </Button>
        
        <Button
          {...{
            key:"Log Out",
            color: "inherit",
            to: "/logout",
            component: RouterLink,
            className: menuButton
          }}
        >
          Log Out
        </Button>
    
      
      </div>
    </Toolbar>;
  };
 

  return (
    <header>
      <AppBar className={header}>{displayDesktop()}</AppBar>
    </header>
  );
}