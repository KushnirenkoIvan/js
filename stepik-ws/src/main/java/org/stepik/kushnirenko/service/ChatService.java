package org.stepik.kushnirenko.service;

import org.stepik.kushnirenko.socket.ChatWebSocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService {

    private Set<ChatWebSocket> webSockets;

    public ChatService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        for (ChatWebSocket socket : webSockets) {
            socket.sendString(data);
        }
    }

    public void add(ChatWebSocket chatWebSocket) {
        webSockets.add(chatWebSocket);
    }

    public void remove(ChatWebSocket chatWebSocket) {
        webSockets.remove(chatWebSocket);
    }
}
