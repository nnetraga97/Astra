import * as React from "react";
import { ReactSketchCanvas } from "react-sketch-canvas";



const Canvas = class extends React.Component {
  constructor(props) {
    super(props);

    this.canvas = React.createRef();
  }

  render() {
    return (
      <div>
        <ReactSketchCanvas
          ref={this.canvas}
          strokeWidth={5}
          strokeColor="black"
          height="1800"
        />
        
      </div>
    );
  }
};

export default Canvas;