package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceRegister extends Remote {
    void register(ServiceNode node) throws RemoteException;
}
