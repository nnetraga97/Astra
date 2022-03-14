import { AppBar, Toolbar, Typography, makeStyles, Button } from "@material-ui/core";
import { formHelperTextClasses } from "@mui/material";
import React from "react";
import { Link as RouterLink } from "react-router-dom";
import Logoimg from "../resources/Astra-logos_white.png";

const useStyles = makeStyles(() => ({
  header: {
    backgroundColor: "#400CCC",
    paddingRight: "19px",
    paddingLeft: "18px",
  },
  logometrics:{
    display:"flex",
    height:200,
    width:200,
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

const headersData = [
  {
    label: "Home",
    href: "/",
  },
  {
    label: "CreateBoard",
    href: "/board",
  },
  
  {
    label: "My Account",
    href: "/account",
  },
  {
    label: "Log Out",
    href: "/logout",
  },
];

export default function Header() {
  const { header,logometrics,buttonmetrics,menuButton,toolbar } = useStyles();

  const displayDesktop = () => {
    return <Toolbar className={toolbar}>
      {astraLogo}
      <div>
      {getMenuButtons()}</div>
    </Toolbar>;
  };
  const getMenuButtons = () => {
    return headersData.map(({ label, href }) => {
      return (
        <Button
          {...{
            key: label,
            color: "inherit",
            to: href,
            component: RouterLink,
            className: menuButton
          }}
        >
          {label}
        </Button>
      );
    });
  };
  const astraLogo = (
    <div>
        <Button to="/" className={buttonmetrics} component={RouterLink}>
        <img src={Logoimg} className={logometrics} />
        </Button>
    </div>
  );

  return (
    <header>
      <AppBar className={header}>{displayDesktop()}</AppBar>
    </header>
  );
}