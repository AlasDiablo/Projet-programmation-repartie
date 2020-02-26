import node.Node;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

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


}
