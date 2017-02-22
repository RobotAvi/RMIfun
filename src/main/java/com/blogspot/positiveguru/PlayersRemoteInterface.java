package com.blogspot.positiveguru;

/**
 * Interface for remote mesages
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayersRemoteInterface extends Remote {
    public String getMessage() throws RemoteException;
    public void setMessage(String msg) throws RemoteException;

}
