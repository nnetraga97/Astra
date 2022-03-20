import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Dashboard from './components/DashBoard';
import Home from './components/Home';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router , Routes,
  Route} from "react-router-dom";
import App from './App';
import Login from './components/Login';
import SignUp from './components/SignUp';
import About from './components/About';


ReactDOM.render(
  <React.StrictMode>
    <Router>
      <Routes>
      <Route path="/" element={<App/>}>
         
      </Route>
      <Route path="/Login" element={<Login/>}>
        
      </Route>
      <Route path="/SignUp" element={<SignUp/>}>
          
      </Route>
      <Route path="/Home" element={<Home/>}>
          
      </Route>
      <Route path="/Logout" element={<App/>}>
      </Route>
      <Route path="/board" element={<Dashboard/>}>
      </Route>
      <Route path="/About" element={<About/>}>
      </Route>
      </Routes>

    </Router>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();


