package ru.sbt.net;

import java.io.Serializable;

public class PackageToServer implements Serializable {
    private final String methodName;
    private final Object[] args;
    private byte[] bytes;

    public PackageToServer(String methodName, Object[] args) {
        this.methodName = methodName;
        this.args = args;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public byte[] getBytes() {

        return methodName.getBytes();
    }
}
