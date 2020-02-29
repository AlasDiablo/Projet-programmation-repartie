package client;

import java.io.File;
import java.io.IOException;

public class Client {

	public static void main(String[] args) throws IOException {
		ReadingMatrix readMatrix = new ReadingMatrix(new File("src/locationMatrix/matrice1.txt").getAbsoluteFile());
		System.out.println(readMatrix.getMatrix().toString());
	}
}
