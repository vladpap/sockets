package ru.sbt.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThreads extends Thread {
    private final Socket socket;
    private final Object implementation;

    public ServerThreads(Socket socket, Object implementation) {
        this.socket = socket;
        this.implementation = implementation;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            PackageToServer packageToServer = null;
            try {
                packageToServer = (PackageToServer) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Object result = null;
            Throwable throwable = null;
            try {
                result = packageToServer.getMethod().invoke(implementation, packageToServer.getArgs());
            } catch (Throwable e) {
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throwable = e;
            }
            PackageFromServer packageFromServer = new PackageFromServer(result, throwable);
            oos.writeObject(packageFromServer);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}