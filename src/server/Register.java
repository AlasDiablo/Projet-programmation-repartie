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
        return nodes;
    }

    public void removeNodes(List<ServiceNode> nodeToRemove) {
        this.nodes.removeAll(nodeToRemove);
    }
}
