import common.Matrix;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Classe de test de la classe matrix
 * @see common.Matrix
 */
public class TestMatrix {

    /**
     * Fonction qui teste le premier constructeur.
     */
    @Test
    public void test_contructeur_type_1() {
        Double[] ligne_1 = new Double[] {
                1.0, 1.1, 1.2
        };
        Double[] ligne_2 = new Double[] {
                2.0, 2.1, 2.2
        };
        Matrix matrix = new Matrix(ligne_1, ligne_2);

        assertEquals("La matrice créer n'as pas la bonne taille", 3, matrix.getSizeX());
        assertEquals("La matrice créer n'as pas la bonne taille", 2, matrix.getSizeY());
    }

    /**
     * Fonction qui teste le deuxième constructeur.
     */
    @Test
    public void test_contructeur_type_2() {
        List<Double> ligne_1 = new ArrayList<>(Arrays.asList(1.0, 1.1, 1.2));
        List<Double> ligne_2 = new ArrayList<>(Arrays.asList(2.0, 2.1, 2.2));

        Matrix matrix = new Matrix(ligne_1, ligne_2);

        assertEquals("La matrice créer n'as pas la bonne taille", 3, matrix.getSizeX());
        assertEquals("La matrice créer n'as pas la bonne taille", 2, matrix.getSizeY());
    }

    /**
     * Fonction qui teste les accèes à la matrice.
     */
    @Test
    public void test_acces() {
        Matrix matrix = new Matrix(Arrays.asList(1.0, 1.1, 1.2), Arrays.asList(2.0, 2.1, 2.2));

        assertEquals("L'acces a la matrice a donnée un valeurs fausse", 1.0, matrix.getAt(0, 0), 0d);
        assertEquals("L'acces a la matrice a donnée un valeurs fausse", 2.1, matrix.getAt(1, 1), 0d);
        assertEquals("L'acces a la matrice a donnée un valeurs fausse", 2.2, matrix.getAt(2, 1), 0d);
    }

    /**
     * Fonction qui teste une position inconnue de la matrice
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test_acces_avec_erreurs_sup() {
        Matrix matrix = new Matrix(Arrays.asList(1.0, 1.1, 1.2), Arrays.asList(2.0, 2.1, 2.2));
        matrix.getAt(3, 3);
    }

    /**
     * Fonction qui teste une position inconnue de la matrice
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test_acces_avec_erreurs_bot() {
        Matrix matrix = new Matrix(Arrays.asList(1.0, 1.1, 1.2), Arrays.asList(2.0, 2.1, 2.2));
        matrix.getAt(-1, -1);
    }
}
