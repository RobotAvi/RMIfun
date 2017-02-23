package com.blogspot.positiveguru;

/*
 * Interface for remote messages
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

interface PlayersRemoteInterface extends Remote {
     String getMessage() throws RemoteException;
     void setMessage(String msg) throws RemoteException;

}
