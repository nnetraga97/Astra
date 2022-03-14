import React from 'react'
import Background from "./Background";
import Logo from './Logo';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { Link as RouterLink } from "react-router-dom";
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import UserService from '../Services/UserService';
import {Navigate} from 'react-router-dom';

const theme = createTheme();

const state = {
  redirect:'false'
}

export default function SignUp() {
  const handleSubmit = (event) =>{
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const email=data.get('email');
    const password= data.get('password');
     const confirmpassowrd= data.get('passwordconfirm');
    console.log({
      email:data.get('email'),
      password: data.get('password'),
      confirmpassowrd: data.get('passwordconfirm')
    });
    
    if(password!==confirmpassowrd)
      return;
      else{
      UserService.createUser(data.get('email'),data.get('password')).then((res)=>{
          let rdata = res.data;
          let data = String(rdata);
          if(data.includes("error")){
            console.log('error');
          }
          else if(data.includes("exists")){
            console.log('user exists');
          }
          else{
            console.log('created user');
            console.log('data');
            <Navigate to="/Home"/>
          }
      })
    }
  }
return (
  
    
    <div>
        <div>
            <Background/>
            <div>
            <Logo/>
            <ThemeProvider theme={theme}>
              <Container component="main" maxWidth="xs" sx={{
                paddingTop:'200px'
              }}>
                <CssBaseline/>
                <Box
                  sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    zIndex:2,
                    backgroundColor:'#FFFFFF',
                    paddingRight:'20px',
                    paddingLeft:'20px'
                  }}
                >
                  <Avatar sx={{m:1,bgcolor:'scondary.main'}}/>
                  <Typography component="h1" variant="h5">
                    Sign Up
                  </Typography>
                  <Box component="form" onSubmit={handleSubmit} noValidate 
                  sx={{mt:1}}>
                    <TextField
                      margin="normal"
                      required
                      fullWidth
                      id="email"
                      label="Email Address"
                      name="email"
                      autoComplete="email"
                      autoFocus
                    />
                    <TextField
                      margin="normal"
                      required
                      fullWidth
                      name="password"
                      label="Password"
                      type="password"
                      id="password"
                      autoComplete="current-password"
                    />
                    <TextField
                      margin="normal"
                      required
                      fullWidth
                      id="passwordconfirm"
                      label=" Confirm Passowrd"
                      name="passwordconfirm"
                      autoFocus
                    />
                    <FormControlLabel
                      control={<Checkbox value="remember" color="primary" />}
                      label="Remember me"
                    />
                    <Button
                      type="submit"
                      fullWidth
                      variant="contained"
                      
                      sx={{ mt: 3, mb: 2}}
                    >
                      Sign Up!
                    </Button>
                    
                  </Box>
                </Box>
              </Container>
            </ThemeProvider>
            
            </div>
            
        </div>
        
    </div>
    
  );
}