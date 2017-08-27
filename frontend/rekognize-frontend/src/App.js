import React, { Component } from 'react';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      constraints: { audio: false, video: { width: 400, height: 300 } },
      imagesRekognized: null
    };

    this.handleStartClick = this.handleStartClick.bind(this);
    this.takePicture = this.takePicture.bind(this);
    this.clearPhoto = this.clearPhoto.bind(this);
  }

  componentDidMount() {
    const constraints = this.state.constraints;
    const getUserMedia = (params) => (
      new Promise((successCallback, errorCallback) => {
        navigator.getUserMedia.call(navigator, params, successCallback, errorCallback);
      })
    );

    getUserMedia(constraints)
    .then((stream) => {
      const video = document.querySelector('video');
      const vendorURL = window.URL || window.webkitURL;

      video.src = vendorURL.createObjectURL(stream);
      video.play();
    })
    .catch((err) => {
      console.log(err);
    });
  }

  clearPhoto() {
    const canvas = document.querySelector('canvas');
    const photo = document.getElementById('photo');
    const context = canvas.getContext('2d');
    const { width, height } = this.state.constraints.video;
    context.fillStyle = '#FFF';
    context.fillRect(0, 0, width, height);

    const data = canvas.toDataURL('image/png');
    photo.setAttribute('src', data);
  }

  handleStartClick(event) {
    event.preventDefault();
    this.takePicture();
  }

  takePicture() {
    const canvas = document.querySelector('canvas');
    const context = canvas.getContext('2d');
    const video = document.querySelector('video');
    const photo = document.getElementById('photo');
    const { width, height } = this.state.constraints.video;

    canvas.width = width;
    canvas.height = height;
    context.drawImage(video, 0, 0, width, height);

    const data = canvas.toDataURL('image/png');

    this.rekognizeImage(data);

    photo.setAttribute('src', data);
  }

  rekognizeImage(data) {
      let self = this;
      fetch("http://localhost:8080/rekognize", {
          method: 'post',
          mode: 'cors',
          headers: new Headers({
              'Content-Type': 'application/json'
          }),
          body: JSON.stringify({
              base64EncodedImage: data
          })
      })
      .then((response) => {
          response.json()
              .then((images) => {
                  self.setState({imagesRekognized: images});
              });
      })
      .catch((err) => {
          console.log(err);
      })
  }

  render() {
      let imagesRekognized = null;
     if (this.state.imagesRekognized !== null) {
       imagesRekognized = this.state.imagesRekognized.matches.map((match) => {
           return <li key={match.id} className="match">match: {match.id}, similarity: {match.similarity}</li>
       })
     }


    return (
      <div className="App">
        <div className="camera">
          <video id="video"></video>
          <button className="button button-capture" id="startButton"
            onClick={ this.handleStartClick }>
            Take photo
          </button>
        </div>
         <canvas id="canvas"
           hidden>
         </canvas>
         <div className="output">
          <img id="photo" alt="Your"/>
          <button className="button button-clear" id="saveButton"
            onClick={ this.clearPhoto }>
            Clear Photo
          </button>
        </div>
          <ul className="matches">{imagesRekognized}</ul>
      </div>
    );
  }
}

export default App;
