import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

public class SapTest {

    @Test
    public void digraph4_length() {
        SAP sap = createSap("data/digraph4.txt");

        Assert.assertEquals(3, sap.length(1, 4));
    }

    @Test
    public void digraph3_length() {
        SAP sap = createSap("data/digraph3.txt");

//        Assert.assertEquals(3, sap.length(10, 7));
        Assert.assertEquals(5, sap.length(8, 13));
    }

    @Test
    public void digraph1_length() {
        SAP sap = createSap("data/digraph1.txt");

        Assert.assertEquals(4, sap.length(3, 11));
        Assert.assertEquals(3, sap.length(9, 12));
        Assert.assertEquals(4, sap.length(7, 2));
        Assert.assertEquals(-1, sap.length(1, 6));
    }

    @Test
    public void digraph1_ancestor() {
        SAP sap = createSap("data/digraph1.txt");

        Assert.assertEquals(1, sap.ancestor(3, 11));
        Assert.assertEquals(5, sap.ancestor(9, 12));
        Assert.assertEquals(0, sap.ancestor(7, 2));
        Assert.assertEquals(-1, sap.ancestor(1, 6));
    }

    private SAP createSap(String filepath) {
        In in = new In(filepath);
        Digraph G = new Digraph(in);
        return new SAP(G);
    }
}
