package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceCalculation extends Remote {
    Matrix productOfMatrix(Matrix a, Matrix b) throws RemoteException;
}
