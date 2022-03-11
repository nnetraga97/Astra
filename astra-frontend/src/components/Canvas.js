import * as React from "react";
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import './styles.css'




const Document = class extends React.Component {
  constructor(props) {
    super(props);
    this.state={text:""}
    this.handleChange = this.handleChange.bind(this)
  }

  handleChange(value){
    this.setState({text:value})
  }

  render() {
    return (
      <div className="container">
        <ReactQuill value={this.state.text}
        onChange={this.handleChange}
        
        />
      </div>
    );
  }
};

export default Document;