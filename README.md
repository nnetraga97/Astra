# Astra
Collaboration Board


A Websocket build collaboration board.

Project Structure - 

Each User will have their own profile and will be able to have multiple dashboards
Each Dashboard will be a Quill Text Editor that can accept changes and has a doc_id
Any user with doc_id can share with another user and they will be able to add to their Astra profile

Project Prerequisites -
---Backend---
Java 11

Java IDE with Home Variable(Recommend using one with built in Server support like Eclipse or VScode)

Maven

Spring JPA

Spring Netty Java Socket.io Server for ws communication

Tomcat Server for API(this server might not be necessary, but have not yet found a way to host rest on socket.io server)

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
1) Proper Configuration of Model (Many to One and One to Many), currently just using IDS
2) Proper Custom Logging Security - Should be simple,
3) Document Creation and proper axios communication
4) Error Handling
5) MaterialUI to enhance UI
6) Host front-end on AWS S3 with cloudfront backing
7) Configure model to work with cloud database, currently using h2 inmem
8) Configure AWS AIM
9) Will the server be hosted on EC2?
10) See whether we really need socket.io or can we not just use the basic spring websocket

