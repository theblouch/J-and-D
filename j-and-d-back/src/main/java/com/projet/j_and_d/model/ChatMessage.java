package com.projet.j_and_d.model;

import java.util.ArrayList;
import java.util.List;

public class ChatMessage {
    private List<String> messages = new ArrayList<>();

    public void add(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
