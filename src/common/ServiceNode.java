package common;

import javafx.util.Pair;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceNode extends Remote {
    double sum(double a, double b) throws RemoteException;
    double mul(double a, double b) throws RemoteException;
    double parseAndCalcul(String formula) throws RemoteException;
}
