package node;

import common.ServiceNode;

import java.rmi.RemoteException;
import java.util.StringTokenizer;

public class Node implements ServiceNode {

    @Override
    public double sum(double a, double b) throws RemoteException {
        System.out.printf("Calc to do: %f + %f\n", a, b);
        return a + b;
    }

    @Override
    public double mul(double a, double b) throws RemoteException {
        System.out.printf("Calc to do: %f * %f\n", a, b);
        return a * b;
    }

    @Override
    public double parseAndCalcul(String formula) throws RemoteException {
        System.out.printf("Formula receive: %s\n", formula);
        double res = 0;
        StringTokenizer tokenizer = new StringTokenizer(formula, "+");
        while (tokenizer.hasMoreTokens()) {
            String[] str = tokenizer.nextToken().split("x");
            double a = Double.parseDouble(str[0]);
            double b = Double.parseDouble(str[1]);
            res = sum(res, mul(a, b));
        }
        return res;
    }
}
