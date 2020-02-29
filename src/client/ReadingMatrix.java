package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.Matrix;

public class ReadingMatrix {

	Matrix matrix;

	public ReadingMatrix(File file) throws IOException {
		this.matrix = this.creerMatrice(file);
	}

	private Matrix creerMatrice(File file) throws IOException {
		List<String[]> tabSplit = this.readFile(file);
		List<List<String>> stringlistSplit = convertirTableau(tabSplit);

		List<List<Double>> newMatrix = new ArrayList<List<Double>>();

		for (int line = 0; line < stringlistSplit.size(); line++) {
			newMatrix.add(new ArrayList<Double>());
			for (int column = 0; column < stringlistSplit.get(line).size(); column++) {
				double number = Double.parseDouble(stringlistSplit.get(line).get(column));
				newMatrix.get(line).add(number);
			}
		}
		return new Matrix(newMatrix);
	}

	private List<List<String>> convertirTableau(List<String[]> tabSplit) {
		List<List<String>> listSplit = new ArrayList<List<String>>();
		int maxLengthLine = 0;
		for (String[] tabLine : tabSplit) {
			if (tabLine.length > maxLengthLine) {
				maxLengthLine = tabLine.length;
			}
		}
		for (String[] tabLine : tabSplit) {

			// converti tableau en liste
			List<String> column = new ArrayList<String>(Arrays.asList(tabLine));

			// ajout de 0 pour les colonnes incomplètes
			if (column.size() < maxLengthLine) {
				for (int i = column.size(); i < maxLengthLine; i++) {
					column.add("0d");
				}
			}
			listSplit.add(column);
		}
		return listSplit;
	}

	private List<String[]> readFile(File file) throws IOException {
		BufferedReader buffreader = null;
		try {
			buffreader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("le fichier est introuvable \n" + e.getMessage());
			System.exit(0);
		}
		String currentLine;
		int maxLengthLine = 0;
		List<String[]> linesList = new ArrayList<String[]>();
		while ((currentLine = buffreader.readLine()) != null) {
			if (currentLine != null) {
				String[] split = currentLine.split(" ");
				if (split.length > maxLengthLine) {
					maxLengthLine = split.length;
				}
				linesList.add(split);
			}
		}
		System.out.println("La matrice a pu être extraite de " + file.getName());
		buffreader.close();
		return linesList;
	}

	public Matrix getMatrix() {
		return this.matrix;
	}

}
