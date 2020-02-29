import common.Reference;
import common.ServiceNode;
import common.ServiceRegister;
import node.Node;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class StartNode {
    public static void main(String[] args) {
        String serveur="localhost";
        int port=1099;
        if(args.length > 0)
            serveur=args[0];
        if(args.length > 1)
            port=Integer.parseInt(args[1]);

        try {

            Registry registry = LocateRegistry.getRegistry(serveur, port);
            ServiceRegister register = (ServiceRegister) registry.lookup(Reference.SERVER_SERVICE_NAME_FOR_NODE);

            Node node = new Node();
            ServiceNode serviceNode = null;
            try {
                serviceNode = (ServiceNode) UnicastRemoteObject.exportObject(node, 0);
            } catch (RemoteException e) {
                System.out.println("Failed to create node object");
                System.exit(1);
            }

            register.register(serviceNode);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Failed to connect into the rmi server!");
            System.exit(1);
        }
    }
}
