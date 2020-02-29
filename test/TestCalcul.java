import common.Matrix;
import common.ServiceNode;
import javafx.util.Pair;
import node.Node;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestCalcul {

    private Node node;

    @Before
    public void before() {
        this.node = new Node();
    }

    @Test
    public void sum() throws RemoteException {
        assertEquals("le nombre n'est pas bon", 2, this.node.sum(1, 1), 0);
        assertEquals("le nombre n'est pas bon", 3, this.node.sum(2, 1), 0);
        assertEquals("le nombre n'est pas bon", 7, this.node.sum(2, 5), 0);
        assertEquals("le nombre n'est pas bon", 6, this.node.sum(1, 5), 0);
    }

    @Test
    public void mul() throws RemoteException {
        assertEquals("le nombre n'est pas bon", 1, this.node.mul(1, 1), 0);
        assertEquals("le nombre n'est pas bon", 2, this.node.mul(2, 1), 0);
        assertEquals("le nombre n'est pas bon", 10, this.node.mul(2, 5), 0);
        assertEquals("le nombre n'est pas bon", 5, this.node.mul(1, 5), 0);
    }

    @Test
    public void pars_1() throws RemoteException {
        assertEquals("le nombre n'est pas bon", 5, this.node.parseAndCalcul("1x1+2x2"), 0);
    }

    @Test
    public void pars_2() throws RemoteException {
        assertEquals(
                "le nombre n'est pas bon",
                56, this.node.parseAndCalcul(
                        "1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2+1x2"
                ),
                0
        );
    }

    @Test
    public void testProduct1() {
        Matrix a = new Matrix(
                new Double[] {1d, 0d},
                new Double[] {2d, -1d}
        );
        Matrix b = new Matrix(
                new Double[] {3d, 4d},
                new Double[] {-2d, -3d}
        );
        Matrix c = new Matrix(
                new Double[] {3d, 4d},
                new Double[] {8d, 11d}
        );
        assertEquals("La matrice calculé n'est pas bonne", c, productOfMatrix(a, b));
    }

    @Test
    public void testProduct2() {
        Matrix a = new Matrix(
                new Double[] {1d, 2d, 0d},
                new Double[] {4d, 3d, -1d}
        );
        Matrix b = new Matrix(
                new Double[] {5d, 1d},
                new Double[] {2d, 3d},
                new Double[] {3d, 4d}
        );
        Matrix c = new Matrix(
                new Double[] {9d, 7d},
                new Double[] {23d, 9d}
        );
        assertEquals("La matrice calculé n'est pas bonne", c, productOfMatrix(a, b));
    }

    @Test
    public void testProduct3() {
        Matrix a = new Matrix(
                new Double[] {5d, 1d},
                new Double[] {2d, 3d},
                new Double[] {3d, 4d}
        );
        Matrix b = new Matrix(
                new Double[] {1d, 2d, 0d},
                new Double[] {4d, 3d, -1d}
        );
        Matrix c = new Matrix(
                new Double[] {9d, 13d, -1d},
                new Double[] {14d, 13d, -3d},
                new Double[] {19d, 18d, -4d}
        );
        assertEquals("La matrice calculé n'est pas bonne", c, productOfMatrix(a, b));
    }

    /**
     * Fonction non repatie de Calculation
     * @see server.Calculation
     */
    public Matrix productOfMatrix(Matrix a, Matrix b) {
        int aX = a.getSizeX(),
                aY = a.getSizeY(),
                bX = b.getSizeX(),
                bY = b.getSizeY();
        List<Pair<Pair<Integer, Integer>, String>> toCalcul = new ArrayList<>();
        List<List<Double>> rawMatrix = new ArrayList<>();
        for (int i = 0; i < aY; i++) {
            rawMatrix.add(new ArrayList<>());
        }

        for (int y = 0; y < aY; y++) {
            for (int x = 0; x < bX; x++) {
                Pair<Integer, Integer> pos = new Pair<>(y, x);
                StringBuilder calcul = new StringBuilder();
                for (int i = 0; i < aX; i++) {
                    calcul.append(a.getAt(i, y)).append("x").append(b.getAt(x, i));
                    if (i + 1 < aX) calcul.append("+");
                }
                toCalcul.add(new Pair<>(pos, calcul.toString()));
            }
        }

        while (toCalcul.size() != 0) {
            Pair<Pair<Integer, Integer>, String> calc = toCalcul.remove(0);
            double res;
            try {
                res = node.parseAndCalcul(calc.getValue());
                rawMatrix.get(calc.getKey().getKey()).add(calc.getKey().getValue(), res);
            } catch (RemoteException e) {
                toCalcul.add(calc);
            }
        }

        return new Matrix(rawMatrix);
    }


}
