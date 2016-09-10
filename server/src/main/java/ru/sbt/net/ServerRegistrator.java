package ru.sbt.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRegistrator {
    private final Integer port;
    private final Object implementation;

    public ServerRegistrator(Integer port, Object implementation) {
        this.port = port;
        this.implementation = implementation;
    }

    private void lissen() {
        try (ServerSocket socket = new ServerSocket(this.port)) {
            while (true) {
                new ServerThreads(socket.accept(), implementation).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + this.port);
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        ServerRegistrator registrator = new ServerRegistrator(5000, new CalculatorImpl());
        registrator.lissen();

    }

}