package ru.sbt.net;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
        try(Socket client = new Socket(host, port)) {
            PackageToServer packageToServer = new PackageToServer(method.getName(), args);
            ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(packageToServer);
        }
        return null;
    }
}