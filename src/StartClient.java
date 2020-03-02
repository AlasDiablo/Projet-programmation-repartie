import common.Matrix;
import common.Reference;
import common.ServiceCalculation;

import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.ReadingMatrix;

public class StartClient {
	public static void main(String[] args) throws IOException {

		if (args.length < 2) {
			System.out.println("Missing file location");
			System.exit(1);
		}
		String file1 = args[0];
		String file2 = args[1];

		String server="localhost";
		int port=1099;
		if(args.length > 2)
			server=args[0];
		if(args.length > 3)
			port=Integer.parseInt(args[1]);

		ServiceCalculation serviceCalculation = null;

		try {
			Registry registry = LocateRegistry.getRegistry(server, port);
			serviceCalculation = (ServiceCalculation) registry.lookup(Reference.SERVER_SERVICE_NAME_FOR_CLIENT);
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Failed to log into rmi!");
			System.exit(1);
		}

		ReadingMatrix readMatrix1 = new ReadingMatrix(new File(file1));
		ReadingMatrix readMatrix2 = new ReadingMatrix(new File(file2));
		Matrix matrix1 = readMatrix1.getMatrix();
		Matrix matrix2 = readMatrix2.getMatrix();

		Matrix result = serviceCalculation.productOfMatrix(matrix1, matrix2);
		System.out.println(result.toString());
	}
}
