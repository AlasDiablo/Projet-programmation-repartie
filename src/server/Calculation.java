package server;

import common.Matrix;
import common.ServiceCalculation;
import common.ServiceRegister;
import javafx.util.Pair;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Calculation implements ServiceCalculation {

    private Register register;

    @Override
    public Matrix productOfMatrix(Matrix a, Matrix b) throws RemoteException {
        int aX = a.getSizeX(),
            aY = a.getSizeY(),
            bX = b.getSizeX(),
            bY = b.getSizeY(),
            rX = bX,
            rY = aY;
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
                    calcul.append(a.getAt(i, y)).append("x").append(b.getAt(x, i));
                    if (i + 1 < aX) calcul.append("+");
                }
                toCalcul.add(new Pair<>(pos, calcul.toString()));
            }
        }

        while (toCalcul.size() != 0) {
        }

        return null;
    }
}
