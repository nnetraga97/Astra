import { AppBar, Toolbar, Typography, makeStyles, Button } from "@material-ui/core";
import React from "react";
import { Link as RouterLink } from "react-router-dom";

const useStyles = makeStyles(() => ({
  header: {
    backgroundColor: "#400CCC",
    paddingRight: "19px",
    paddingLeft: "18px",
  },
  logo: {
    fontFamily: "Work Sans, sans-serif",
    fontWeight: 500,
    color: "#FFFEFE",
    textAlign: "left",
  },
  menuButton: {
    fontFamily: "Open Sans, sans-serif",
    fontWeight: 500,
    size: "18px",
    marginLeft: "38px",
 },
 toolbar: {
    display: "flex",
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
    label: "My Boards",
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
  const { header, logo, menuButton,toolbar } = useStyles();

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
    <Typography variant="h6" component="h1" className={logo}>
      ASTRA
    </Typography>
  );

  return (
    <header>
      <AppBar className={header}>{displayDesktop()}</AppBar>
    </header>
  );
}