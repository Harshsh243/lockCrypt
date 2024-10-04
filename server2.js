const express = require('express');
const http = require('http');
const socketIo = require('socket.io');
const path = require('path');

const app = express();
const server = http.createServer(app);
const io = socketIo(server);

app.use(express.static(path.join(__dirname))); // Serve static files from the current directory

// Serve the HTML file
app.get('/server2.html', (req, res) => {
    res.sendFile(path.join(__dirname, 'server2.html'));
});

// New route to receive messages from Server 1
app.use(express.json()); // Middleware to parse JSON bodies
app.post('/message', (req, res) => {
    const { message, technique, key } = req.body;
    console.log(`Message received from Server 1: ${message} using ${technique} with key ${key}`);
    
    // Broadcast message to all clients connected to Server 2
    io.emit('message', { message, technique, key });
    res.sendStatus(200); // Respond with 200 OK
});

// Socket.io setup
io.on('connection', (socket) => {
    console.log('A user connected to Server 2');

    socket.on('message', (data) => {
        const { message, technique, key } = data;
        console.log(`Message from Server 2: ${message} using ${technique} with key ${key}`);
        socket.broadcast.emit('message', data);
    });
});

server.listen(4000, () => {
    console.log('Server 2 is running on port 4000');
});
