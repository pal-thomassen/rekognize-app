import React, { Component } from 'react';
import './App.css';

class App extends Component {
    constructor(props) {
        super(props);

        this.state = {
            constraints: { audio: false, video: { width: 400, height: 300 } },
            imageUploaded: false,
            listFaces: []
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

        this.getFacesToList();
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

        photo.setAttribute('src', data);
    }

    handleSubmit(event) {
        event.preventDefault();

        const photo = document.getElementById("photo");
        const externalId = document.getElementById("externalid");
        if (photo.src !== undefined) {
            if (externalId.value !== "") {
                this.postImage(externalId.value, photo.src);
            } else {
                console.log("External ID must be set");
            }
        } else {
            console.log("Photo must be taken first");
        }
    }

    postImage(id, src) {
        fetch("http://localhost:8080/admin/upload", {
            method: 'post',
            mode: 'cors',
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify({
                imageId: id,
                imageSrc: src
            })
        })
            .then(response => {
                this.setState({imageUploaded: true});
            })
            .catch(err => {
                console.log(err);
            })
    }

    handleDelete(event) {
        const faceId = event.currentTarget.getAttribute("data");
        fetch("http://localhost:8080/admin/delete", {
            method: "delete",
            mode: "cors",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify({
                faceId: faceId
            })
        })
            .then(response => {
                this.getFacesToList();
            })
            .catch(err => {
                console.log(err);
            })

    }

    render() {

        let imageUploaded = <div>
        </div>;

        if (this.state.imageUploaded) {
            imageUploaded = <div>
                <h1>Success</h1>
            </div>;
        }

        const listFaces = this.state.listFaces.map(indexedFace => {
            return <li key={indexedFace.awsId} className="li-indexedFace" >
                {indexedFace.externalId}
                <button data={indexedFace.awsId} onClick={(e) => this.handleDelete(e) }>Delete</button>
            </li>
        });

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
                <div className="upload">
                   <form onSubmit={(e) => this.handleSubmit(e)}>
                       <label>
                           External ID:
                           <input type="text" id="externalid" />
                       </label>
                       <input type="submit" value="Last opp" />
                   </form>
                </div>

                {imageUploaded}

                <ul className="indexedFacesList">
                    {listFaces}
                </ul>

            </div>
        );
    }

    getFacesToList() {
        fetch("http://localhost:8080/admin/listfaces", {
            method: 'get',
            mode: 'cors',
            headers: new Headers({
                'Content-Type': 'application/json'
            })
        })
            .then(response => {
                response.json()
                    .then(json => {
                        this.setState({"listFaces": json});
                    });
            })
            .catch(err => {
                console.log(err);
            })
    }
}

export default App;
