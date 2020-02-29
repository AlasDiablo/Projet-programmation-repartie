import common.Matrix;
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
    public void testProduct() {
        Matrix a = new Matrix(
                new Double[] {1d, 0d},
                new Double[] {2d, -1d}
        );
        Matrix b = new Matrix(
                new Double[] {3d, 4d},
                new Double[] {-2d, -3d}
        );
        productOfMatrix(a, b);
    }

    @Test
    public void testProduct2() {
        Matrix a = new Matrix(
                new Double[] {11d, 12d, 13d},
                new Double[] {21d, 22d, 23d},
                new Double[] {31d, 32d, 33d}
        );
        Matrix b = new Matrix(
                new Double[] {111d, 112d, 113d},
                new Double[] {221d, 222d, 223d},
                new Double[] {331d, 332d, 333d}
        );
        productOfMatrix(a, b);
    }

    @Test
    public void testProduct3() {
        Matrix a = new Matrix(
                new Double[] {11d, 12d, 13d},
                new Double[] {21d, 22d, 23d}
        );
        Matrix b = new Matrix(
                new Double[] {111d, 112d},
                new Double[] {221d, 222d},
                new Double[] {331d, 332d}
        );
        productOfMatrix(a, b);
    }

    private void productOfMatrix(Matrix a, Matrix b) {
        int aX = a.getSizeX(),
                aY = a.getSizeY(),
                bX = b.getSizeX(),
                bY = b.getSizeY(),
                rX = bX,
                rY = aY;
        System.out.println("X = " + rX + ", Y = " + rY);
        List<Pair<Pair<Integer, Integer>, String>> toCalcul = new ArrayList<>();
        List<List<Double>> rawMatrix = new ArrayList<>();
        for (int i = 0; i < aY; i++) {
            rawMatrix.add(new ArrayList<>());
        }

        for (int y = 0; y < rY; y++) {
            for (int x = 0; x < rX; x++) {
                Pair<Integer, Integer> pos = new Pair<>(x, y);
                StringBuilder calcul = new StringBuilder();
                for (int i = 0; i < aX; i++) {
                    calcul.append(a.getAt(i, y));
                    calcul.append("x");
                    calcul.append(b.getAt(x, i));
                    if (i + 1 < aX) calcul.append("+");
                }
                toCalcul.add(new Pair<>(pos, calcul.toString()));
            }
        }

        toCalcul.forEach(e -> System.out.println("at x = " + e.getKey().getKey() + ", y = " + e.getKey().getValue() + ": " + e.getValue()));

    }


}
