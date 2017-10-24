package com.prince.myproj.brain.controllers;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/ws")
public class WSController {
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        System.out.println("Received: " + message);



        session.getBasicRemote().sendText("This is the last server message");
    }

    @OnOpen
    public void onOpen() {
        System.out.println("one Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("one Connection closed");
    }
}
