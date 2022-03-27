import { Box, Button } from '@material-ui/core';
import { cardActionAreaClasses } from '@mui/material';
import React, { useRef, useState,useEffect } from 'react'
import { useSpring,animated } from 'react-spring';
import UserService from '../Services/UserService';
import Background from "./Background";
import Header from './Header';
import Logo from './Logo';
import {useNavigate} from 'react-router-dom';




export default function Home() {
  

  const[currentDocId,setcurrentDocId] = useState(null);
  const[boardsList,setboardsList] = useState(cards);
  const[user,setUser] = useState();
  const[userId,setUserId] = useState();
  const[listUpdated,setlistUpdated] = useState(false);


  useEffect(()=>{
    setUser(window.sessionStorage.getItem('userName'));
    setUserId(window.sessionStorage.getItem('userId'));
    UserService.getUserbyUsername(window.sessionStorage.getItem('userName')).then((res)=>{
      let data = res.data;
      if(data==null){
        return;
      }
      else{
        setboardsList(data.boardList);
        console.log(boardsList);
      }
    });
  
},[]);

  useEffect(()=>{

      UserService.getUserbyUsername(window.sessionStorage.getItem('userName')).then((res)=>{
        let data = res.data;
        if(data==null){
          return;
        }
        else{
          
          if(listUpdated===true){
          setboardsList(data.boardList);
        }
          console.log(data);
          console.log(boardsList);
        }
      });
    
  },[listUpdated]);

  const createBoard = (event)=>{
    event.preventDefault();
   
    UserService.createBoard(userId, "test").then((res)=>{
      let data = String(res.data);
      if(data===null){
        return;
      }
      else{
        if(data.includes("unable")){
            console.log("unable to create");
        }
        else if(data.includes("no user")){
          console.log("no user found");
        }
        else{
            console.log(data);
            setlistUpdated(true);
        }
      }
    });
  }
  const navigate = useNavigate();
  const openBoard=(event)=>{
    console.log(event);
    window.sessionStorage.setItem('currentBoard',3);
    navigate('/board');
    }
 
return (
    
    <div>
        <div>
          <div className='navbar'>
                    <Header/>
                    
          </div>
            <Background/>
            <div className='containers'>
              
            <Box 
            sx={{
              zIndex:3
            }}
            >
            <div className="row">
              {boardsList.map((card, i) => (
                <div className="column">
                  <Card>
                    <div className="card-title">{card.name}</div>
                    <div className="card-id">{card.id}</div>
                    <div className="card-body">{card.data}</div>
                    <Image ratio={card.imageRatio} src={card.image} />
                    <Button sx={{ zIndex:3, color:'white'}} onClick={openBoard}>Open Board</Button>
                  </Card>
                </div>
              ))}
            </div>
            
            </Box>
            </div>
        </div>
        <Button sx={{ zIndex:3, color:'white'}} onClick={createBoard}>Create Board</Button>
    </div>
  );
}


function Card({children}){

 

  const ref = useRef();

  

  const[isHovered,setHovered] = useState(false);

  const [animatedProps,setAnimatedProps] = useSpring(()=>{
    return{

      xys:[0,0,1],
      
      config:{mass:10, tension:400,friction:40, precision: 0.00001}
    };
  });


  return(
    <animated.div
      ref={ref}
      className="card"
      onMouseEnter={()=>setHovered(false)}
      
      onMouseMove={({clientX,clientY})=>{

        const x = 
        clientX - 
        (ref.current.offsetLeft - (window.scrollX||window.pageXOffset||document.body.scrollLeft));
        // Get mouse y position within card
        const y =
        clientY -
        (ref.current.offsetTop -
          (window.scrollY || window.pageYOffset || document.body.scrollTop));

        // Set animated values based on mouse position and card dimensions
        const dampen = 50; // Lower the number the less rotation
        const xys = [
        -(y - ref.current.clientHeight / 2) / dampen, // rotateX
        (x - ref.current.clientWidth / 2) / dampen, // rotateY
        1.07 // Scale
        ];

        // Update values to animate to
        setAnimatedProps({ xys: xys });
      }}
        onMouseLeave={() => {
        setHovered(false);
        // Set xys back to original
        setAnimatedProps({ xys: [0, 0, 1] });
        }}
        style={{
        // If hovered we want it to overlap other cards when it scales up
        zIndex: isHovered ? 2 : 1,
        // Interpolate function to handle css changes
        transform: animatedProps.xys.to(
        (x, y, s) =>
          `perspective(600px) rotateX(${x}deg) rotateY(${y}deg) scale(${s})`
        )
        }}
        >
        {children}
        </animated.div>
        );
}


function Image({ ratio, src }) {
  return (
    <div className="image-container">
      <div className="image-inner-container">
        <div
          className="ratio"
          style={{
            paddingTop: ratio * 100 + '%'
          }}
        >
          <div className="ratio-inner">
            <img src={src} />
          </div>
        </div>
      </div>
    </div>
  );
}

const cards = [
  
  {
    title: 'Build faster ⚡️',
    description:
      'Create a React web app in the fraction of the time using our library of themes and building blocks. We have everything from navbars and content grids to authentication flows and commenting systems. New blocks are added every week.',
    image: 'https://6jlvz1j5q3.csb.app/undraw_collection.svg',
    imageRatio: 784 / 1016
  },
  {
    title: 'Tweak anything 👩‍🎨',
    description:
      'Built with developers in mind. Change element structure, edit CSS, create components, add props and state. We give you access to the underlying React code so you can do what you need right in our tool.',
    image: 'https://6jlvz1j5q3.csb.app/undraw_upload.svg',
    imageRatio: 839 / 1133
  },
  {
    title: 'Export your code 🚀',
    description:
      "Export your project as a high-quality React codebase. We're lazer focused on helping you build and iterate quickly, but expect that you'll eventually want to export and wrap things up in your favorite code editor.",
    image: 'https://6jlvz1j5q3.csb.app/undraw_static_assets.svg',
    imageRatio: 730 / 1030
  }
];


