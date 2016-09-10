package ru.sbt.net;

import java.io.Serializable;

public class PackageFromServer implements Serializable {
    private static final long serialVersionUID = 6631604006365365537L;
    private final Object returnObject;
    private final Throwable throwable;


    public PackageFromServer(Object returnObject, Throwable throwable) {
        this.returnObject = returnObject;
        this.throwable = throwable;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}