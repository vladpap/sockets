package ru.sbt.net;

import java.io.Serializable;
import java.lang.reflect.Method;

public class PackageToServer implements Serializable {
    private static final long serialVersionUID = 6631604036553063657L;
    private final SerializableMethod method;
    private final Object[] args;

    public PackageToServer(Method method, Object[] args) {
        this.method = new SerializableMethod(method);
        this.args = args;
    }

    public Method getMethod() {
        return this.method.getMethod();
    }

    public Object[] getArgs() {
        return args;
    }

}
