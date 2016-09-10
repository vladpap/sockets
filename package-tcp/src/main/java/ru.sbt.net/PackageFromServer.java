package ru.sbt.net;

import java.io.Serializable;

public class PackageFromServer implements Serializable {
    private final Object returnObject;
    private final Throwable throwable;


    public PackageFromServer(Object returnObject) {
        PackageToServer(returnObject, null);
    }
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
