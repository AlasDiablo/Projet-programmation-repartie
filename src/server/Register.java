package server;

import common.ServiceNode;
import common.ServiceRegister;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Register implements ServiceRegister {

    private List<ServiceNode> nodes;

    public Register() {
        this.nodes = new ArrayList<>();
    }

    @Override
    public void register(ServiceNode node) throws RemoteException {
        this.nodes.add(node);
    }

    public List<ServiceNode> getNodes() {
        List<ServiceNode> toRemove = new ArrayList<>();
        List<ServiceNode> toReturn = new ArrayList<>();
        this.nodes.forEach(node -> {
            try {
                node.mul(0, 0);
                toReturn.add(node);
            } catch (RemoteException e) {
                toRemove.add(node);
            }
        });
        this.nodes.removeAll(toRemove);
        return toReturn;
    }
}
