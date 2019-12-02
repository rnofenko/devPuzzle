package rn.puzzle.string.medium;

import org.junit.Assert;
import org.junit.Test;

public class SerializeAndDeserializeBinaryTreeTests {

    private SerializeAndDeserializeBinaryTreeDeep serializer = new SerializeAndDeserializeBinaryTreeDeep();

    @Test
    public void test0() {
        test("1,2,T,3");
        test("1,2,3");
        test("1,2,,3");
        test("1,2,,3,,4");
        test("1,2,,3,,4,5,6,T,7");
        test("1,2,,3,,4,5,6,T,7");
    }

    @Test
    public void test1() {
        test("1,2,3,T,T,4,,5,,6");
    }

    private void test(String tree) {
        Object node = serializer.deserialize(tree);
        String data = serializer.serialize(node);
        Assert.assertEquals(tree, data);
    }
}
