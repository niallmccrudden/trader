var _ = require("lodash");
var bodyParser = require('body-parser')
var express = require('express');
var app = express();
var server = require('http').createServer(app); 
var io = require('socket.io')(server);

app.use(express.static('public'));

// parse application/json
app.use(bodyParser.json());

app.use(require('./server/routes/index'));

app.set('view engine', 'pug')
app.set('views', './server/views')

server.listen(3008, function () {
  console.log('Example app listening on port 3008!')
});

io.on('connection', function(socket){
  console.log("Socket connected: " + socket.id);
});

var amqp = require('amqplib/callback_api');

amqp.connect('amqp://localhost:5673', function(err, conn) {
  conn.createChannel(function(err, ch) {
    var q = 'processed-trades-queue';

    ch.assertQueue(q, {durable: true});
    console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", q);
    ch.consume(q, function(msg) {
      console.log(" [x] Received %s", msg.content.toString());
      io.emit('trade message', msg.content.toString());
    }, {noAck: true});
  });
});