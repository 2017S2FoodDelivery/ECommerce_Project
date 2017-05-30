/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var express=require('express');
var app=express();
var server=require('http').createServer(app);
var io=require('socket.io').listen(server);
users=[];
connections=[];
server.listen(process.env.PORT || 8083);
console.log("Server is running......");
app.get('/', function(req, res){
    res.sendFile(__dirname+'/Chatbox.html');
});
//Connected
io.sockets.on('connection', function(socket){
    //console.log('Client connected.');
    connections.push(socket);
    console.log('Connected: %s client(s) connected',connections.length);
    //Disconnect
    socket.on('disconnect',function(data){
        //console.log('Client disconnected.');
        //if(!socket.username) return;
        users.splice(users.indexOf(socket.username),1);
        updateUsernames();
        connections.splice(connections.indexOf(socket),1);
        console.log('Disconnected: %s client(s) connected',connections.length);
    });
    //Send Message
    socket.on('send message', function(data){
        //console.log(data);
        io.sockets.emit('new message', {msg:data,users:socket.username});
    });
    //New User
    socket.on('new user', function(data, callback){
        callback(true);
        socket.username=data;
        users.push(socket.username);
        updateUsernames();
    });
    function updateUsernames(){
        io.sockets.emit('get users', users);
    }
})