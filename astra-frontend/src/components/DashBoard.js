import React from 'react'
import Background from "./Background";
import Logo from './Logo';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

export default function About() {
return (
    
    <div>
        <div>
            <div className="container">
              <ReactQuill            
              />
            </div>
        </div>
        <Logo/>
    </div>
  );
}

