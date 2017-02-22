package com.blogspot.positiveguru;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PlayersMessage extends UnicastRemoteObject implements PlayersRemoteInterface {
    String msg = "";

    PlayersMessage() throws RemoteException {
    }

    public String getMessage() throws RemoteException {
        return msg;
    }

    public void setMessage(String msg) throws RemoteException {
        this.msg = msg;
    }
}
