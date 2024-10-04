const express = require('express');
const http = require('http');
const socketIo = require('socket.io');
const path = require('path');
const axios = require('axios'); // Import Axios

const app = express();
const server = http.createServer(app);
const io = socketIo(server);

app.use(express.static(path.join(__dirname))); // Serve static files from the current directory

// Serve the HTML file
app.get('/server1.html', (req, res) => {
    res.sendFile(path.join(__dirname, 'server1.html'));
});

// Socket.io setup
io.on('connection', (socket) => {
    console.log('A user connected to Server 1');

    socket.on('message', async (data) => {
        const { message, technique, key } = data;
        console.log(`Encrypted Message from Server 1: ${message} using ${technique} with key ${key}`);
        
        // Send message to Server 2 via Axios
        try {
            await axios.post('http://localhost:4000/message', { message, technique, key });
        } catch (error) {
            console.error('Error sending message to Server 2:', error);
        }

        socket.broadcast.emit('message', data); // Broadcast message to all clients connected to Server 1
    });
});

server.listen(3000, () => {
    console.log('Server 1 is running on port 3000');
});
