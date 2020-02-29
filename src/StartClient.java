import common.Matrix;
import common.Reference;
import common.ServiceCalculation;
import common.ServiceRegister;
import server.Calculation;
import server.Register;

import java.io.File;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import client.ReadingMatrix;

public class StartClient {
	public static void main(String[] args) throws IOException {
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

		ReadingMatrix readMatrix1 = new ReadingMatrix(new File("src/locationMatrix/matrice1.txt").getAbsoluteFile());
		ReadingMatrix readMatrix2 = new ReadingMatrix(new File("src/locationMatrix/matrice2.txt").getAbsoluteFile());
		Matrix matrix1 = readMatrix1.getMatrix();
		Matrix matrix2 = readMatrix2.getMatrix();

		System.out.println(matrix1.toString());
		System.out.println(matrix2.toString());

		calculation.productOfMatrix(matrix1, matrix2);
	}
}
