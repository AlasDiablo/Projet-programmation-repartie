package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceNode extends Remote {
    void sum(int a, int b) throws RemoteException;
    void mul(int a, int b) throws RemoteException;
}
