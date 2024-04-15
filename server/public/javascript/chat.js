const inputField = document.getElementById("chat-input");
const outputArea = document.getElementById("chat-area");

//opening a web socket whenever site is opened
const socketRoute = document.getElementById("ws-route").value;
const socket = new WebSocket(socketRoute.replace("http", "ws"));
console.log("socket opened");

socket.send("Test message");