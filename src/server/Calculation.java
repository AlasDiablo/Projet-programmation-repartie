package server;

import common.Matrix;
import common.ServiceCalculation;
import common.ServiceNode;
import javafx.util.Pair;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Calculation implements ServiceCalculation {

    private Register register;

    public Calculation(Register register) {
        this.register = register;
    }

    @Override
    public Matrix productOfMatrix(Matrix a, Matrix b) throws RemoteException {
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
            List<ServiceNode> nodeToRemove = new ArrayList<>();
            this.register.getNodes().forEach(node -> new Thread(() -> {
                synchronized (toCalcul) {
                    Pair<Pair<Integer, Integer>, String> calc = toCalcul.remove(0);
                    double res;
                    try {
                        res = node.parseAndCalcul(calc.getValue());
                        rawMatrix.get(calc.getKey().getKey()).add(calc.getKey().getValue(), res);
                    } catch (RemoteException e) {
                        nodeToRemove.add(node);
                        toCalcul.add(calc);
                    }
                }
            }).start());
            this.register.removeNodes(nodeToRemove);
        }

        return new Matrix(rawMatrix);
    }
}
