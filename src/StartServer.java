import common.Reference;
import common.ServiceCalculation;
import common.ServiceRegister;
import server.Calculation;
import server.Register;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class StartServer {
    public static void main(String[] args) {
        Register register = new Register();
        Calculation calculation = new Calculation(register);

        ServiceRegister serviceRegister = null;
        ServiceCalculation serviceCalculation = null;

        try {
            serviceRegister = (ServiceRegister) UnicastRemoteObject.exportObject(register, 0);
            serviceCalculation = (ServiceCalculation) UnicastRemoteObject.exportObject(calculation, 0);
        } catch (RemoteException e) {
            System.out.println("Failed to converted register and/or calculation\nError log: \n" + e.getMessage());
            System.exit(1);
        }

        try {
            Registry registry = LocateRegistry.getRegistry();
            registry.bind(Reference.SERVER_SERVICE_NAME_FOR_NODE, serviceRegister);
            registry.bind(Reference.SERVER_SERVICE_NAME_FOR_CLIENT, serviceCalculation);
        } catch (RemoteException | AlreadyBoundException e) {
            System.out.println("Failed to log into rmi!");
            System.exit(1);
        }
    }
}
