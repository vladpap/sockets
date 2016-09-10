package ru.sbt.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientInvocationHandler implements InvocationHandler {
    private final String host;
    private final int port;

    public ClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try (Socket client = new Socket(host, port)) {
            PackageToServer packageToServer = new PackageToServer(method, args);
            ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(packageToServer);
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            PackageFromServer packageFromServer = null;
            try {
                packageFromServer = (PackageFromServer) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (packageFromServer.getThrowable() != null) {
                throw packageFromServer.getThrowable();
            }
            result = packageFromServer.getReturnObject();
        }
        return result;
    }
}