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
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import UserService from '../Services/UserService';

const theme = createTheme();

export default function Login() {
  const handleSubmit = (event) =>{
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email:data.get('email'),
      passowrd: data.get('password'),
    });
    UserService.getUserbyUsername(data.get('email')).then((res)=>{
      let dbdata = res.data;
      if(dbdata==null){
        return;
      }
      else{
        console.log(dbdata);
        let dbemail = dbdata.get('userName');
        let dbpwd = dbdata.get('password');
        if(dbpwd===data.get('password') && dbemail===data.get('email')){
          window.sessionStorage.setItem("userName", dbemail);
        }
        else{
          console.log("Wrong password");
        }
      }
    })
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
                    Sign In
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
                    <FormControlLabel
                      control={<Checkbox value="remember" color="primary" />}
                      label="Remember me"
                    />
                    <Button
                      type="submit"
                      fullWidth
                      variant="contained"
                      sx={{ mt: 3, mb: 2 }}
                    >
                      Sign In
                    </Button>
                    <Grid container>
                      <Grid item xs>
                        <Link href="#" variant="body2">
                          Forgot Password?
                        </Link>
                      </Grid>
                      <Grid item >
                        <Link href="#" variant="body2">
                          {"Don't have an account? Sign Up!"}
                        </Link>
                      </Grid>
                    </Grid>
                  </Box>
                </Box>
              </Container>
            </ThemeProvider>
            
            </div>
            
        </div>
        
    </div>
    
  );
}