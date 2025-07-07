package com.example.network;

public class NetworkService {
    private final NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {
        String response = networkClient.connect();
        return "Connected to " + response;
    }
}
