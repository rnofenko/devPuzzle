package rn.tool;

import org.junit.Test;
import rn.tool.combination.CombinatorFactory;
import rn.tool.combination.ICombinator;

public class CombinatorTest {

    @Test
    public void combinator_3_2() {
        ICombinator combinator = CombinatorFactory.create(3, 2);
        org.junit.Assert.assertArrayEquals(new int[] {0, 1}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {0, 2}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {0, 3}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {1, 2}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {1, 3}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {2, 3}, combinator.next());
        org.junit.Assert.assertNull(combinator.next());
    }

    @Test
    public void variant_length() {
        ICombinator combinator = CombinatorFactory.variantLength(3);
        org.junit.Assert.assertArrayEquals(new int[] {0}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {1}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {2}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {3}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {0, 1}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {0, 2}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {0, 3}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {1, 2}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {1, 3}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {2, 3}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {0, 1, 2}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {0, 1, 3}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {0, 2, 3}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {1, 2, 3}, combinator.next());
        org.junit.Assert.assertArrayEquals(new int[] {0, 1, 2, 3}, combinator.next());
        org.junit.Assert.assertNull(combinator.next());
    }
}
