package ru.sbt.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRegistrator {
    private final String host;
    private final Integer port;
    private final Object implementation;

    public ServerRegistrator(String host, Integer port, Object implementation) {
        this.host = host;
        this.port = port;
        this.implementation = implementation;
    }

    private void lissen() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.port);
        try (Socket client = serverSocket.accept()) {
            while (client.isConnected()) {
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                PackageToServer toServer = null;
                try {
                    toServer = (PackageToServer) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(toServer.getMethod().getName());

            }
        }
    }

    public static void main(String[] args) {
        ServerRegistrator registrator = new ServerRegistrator("localhost", 5000, new CalculatorImpl());
        try {
            registrator.lissen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}