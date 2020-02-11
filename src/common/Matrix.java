package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class qui represente les matrice.
 */
public class Matrix implements Serializable {
    /**
     * Matrice represté avec deux liste (tableau 2d).
     */
    private List<List<Double>> matrix;
    /**
     * Tail de la matrice.
     */
    private int sizeX, sizeY;

    /**
     * Contruteurs qui prende des tableaus qui conreponde a chaque lignes.
     * @param lines tableau conrepondant a tous les lines.
     */
    public Matrix(Double[] ... lines) {
        this.matrix = new ArrayList<>();
        Arrays.stream(lines).forEach(line -> {
            if (this.sizeX < line.length) this.sizeX  = line.length;
            this.matrix.add(Arrays.asList(line));
        });
        this.sizeY = lines.length;
    }

    /**
     * Contruteurs qui prende des listes qui conreponde a chaque lignes.
     * @param lines tableau conrepondant a tous les lines.
     */
    public Matrix(List<Double>... lines) {
        this.matrix = new ArrayList<>();
        Arrays.stream(lines).forEach(line -> {
            if (this.sizeX < line.size()) this.sizeX  = line.size();
            this.matrix.add(line);
        });
        this.sizeY = lines.length;
    }

    /**
     * Fonction d'acces a un valueurs a un possition donnée.
     * @param x possition sur la ligne.
     * @param y possition de la collone.
     * @return retourne un flotent correpondant a la valeurs de la possition.
     * @throws ArrayIndexOutOfBoundsException erreur levais si on veux un valuer qui se trouve a un possition qui n'exite pas.
     */
    public Double getAt(int x, int y) throws ArrayIndexOutOfBoundsException {
        if (x >= this.sizeX)
            throw new ArrayIndexOutOfBoundsException("X value to big!");
        if (y >= this.sizeY)
            throw new ArrayIndexOutOfBoundsException("Y value to big!");
        try {
            return this.matrix.get(y).get(x);
        } catch (Exception e) {
            return 0d;
        }
    }

    /**
     * Fonction qui permette d'avoir la longeurs de la matrice.
     * @return entier contrespondant a la longeurs.
     */
    public int getSizeX() {
        return this.sizeX;
    }

    /**
     * Fonction qui permette d'avoir la hautteur de la matrice.
     * @return entier contrespondant a la hautteur.
     */
    public int getSizeY() {
        return this.sizeY;
    }
}
