package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe qui représente les matrices.
 */
public class Matrix implements Serializable {
	/**
	 * Matrice representée par une liste composée de liste(s) (tableau en deux
	 * dimensions).
	 */
	private List<List<Double>> matrix;
	/**
	 * Taille de la matrice.
	 */
	private int sizeX, sizeY;

	/**
	 * Constructeur qui associe des tableaux à chaque ligne.
	 * 
	 * @param lines tableau correpondant a toutes les lines.
	 */
	public Matrix(Double[]... lines) {
		this.matrix = new ArrayList<>();
		Arrays.stream(lines).forEach(line -> {
			if (this.sizeX < line.length)
				this.sizeX = line.length;
			this.matrix.add(Arrays.asList(line));
		});
		this.sizeY = lines.length;
	}

	/**
	 * Constructeur qui associe des listes à chaque ligne.
	 * 
	 * @param lines tableau correpondant a toutes les lines.
	 */
	@SafeVarargs
	public Matrix(List<Double>... lines) {
		this.matrix = new ArrayList<>();
		Arrays.stream(lines).forEach(line -> {
			if (this.sizeX < line.size())
				this.sizeX = line.size();
			this.matrix.add(line);
		});
		this.sizeY = lines.length;
	}

	/**
	 * Fonction d'accès à une valeur pour une position donnée.
	 * 
	 * @param x position sur la ligne.
	 * @param y position sur la colonne.
	 * @return retourne un flottant correpondant à la coordonnée x et y dans la
	 *         matrice..
	 * @throws ArrayIndexOutOfBoundsException erreur levée lorsque les coordonnées
	 *                                        d'une valeur déborde de la matrice.
	 */
	public Double getAt(int x, int y) throws ArrayIndexOutOfBoundsException {
		if (x >= this.sizeX || x < 0)
			throw new ArrayIndexOutOfBoundsException("X value to big!\n X = " + x + ", Y = " + y);
		if (y >= this.sizeY || y < 0)
			throw new ArrayIndexOutOfBoundsException("Y value to big!\n X = " + x + ", Y = " + y);
		try {
			return this.matrix.get(y).get(x);
		} catch (Exception e) {
			return 0d;
		}
	}

	/**
	 * Fonction qui permette d'avoir la longeurs de la matrice.
	 * 
	 * @return entier contrespondant a la longeurs.
	 */
	public int getSizeX() {
		return this.sizeX;
	}

	/**
	 * Fonction qui permette d'avoir la hautteur de la matrice.
	 * 
	 * @return entier contrespondant a la hautteur.
	 */
	public int getSizeY() {
		return this.sizeY;
	}
}
