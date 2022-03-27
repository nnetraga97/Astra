# Astra
Collaboration Board


A Websocket build collaboration board.

Project Structure - 

Each User will have their own profile and will be able to have multiple dashboards
Each Dashboard will be a Quill Text Editor that can accept changes and has a QR code
Any user with QRcodecan share with another user and they will be able to add to their Astra profile

Project Prerequisites -
---Backend---
Java 11

Java IDE with Home Variable(Recommend using one with built in Server support like Eclipse or VScode)

Maven

Spring JPA

Spring SockJs, Stomp and socket for ws communication

Tomcat Server for API

---Frontend---
React(DOM,router,hooks,state)

Node.js

Quill (React)

Axios

socket.io-client

Material-UI

The project currently has two apis smudged into one, A rest api used by the users to access
data that does not require reat-time communications.
The Api currently handles CRUD of users, relationship between dashboard and user.

For the content of the particular dashboard we use the socket api to get real-time updates to data.


WIP - 
1) Proper Security
2) Error Handling
3) Enhance UI
4) Document Sharing and Qr code generation
5) AWS Hosting
6) DashBoard realtime updates


