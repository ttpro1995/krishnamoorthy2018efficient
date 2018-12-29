package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EUCSTest {

    @Test
    public void put() {
        EUCS.put("a", "b", 1);
        int result = EUCS.get("a", "b");
        assertEquals(1, result);

        Itemset ab = new Itemset(Arrays.asList("a","b"));
        Itemset ac = new Itemset(Arrays.asList("a", "c"));
        Itemset ab2 = new Itemset(Arrays.asList("a" ,"b"));
        Itemset ac2 = new Itemset(Arrays.asList("a" ,"c"));
        EUCS.put(ab, ac, 22);
        int result2 = EUCS.get(ab2, ac2);
        assertEquals(22, result2);
    }

    @Test
    public void get() {
        EUCS.put("x", "y", 4);
        int r1 = EUCS.get("x", "y");
        int r2 = EUCS.get("y", "x");
        assertEquals(4, r1);
        assertEquals(4, r2);
    }

    @Test
    public void increment() {
        EUCS.put("aa", "bb",2);
        EUCS.increment("aa", "bb", 4);
        int result = EUCS.get("aa", "bb");
        assertEquals(6, result);

        Itemset ax = new Itemset(Arrays.asList("a","x"));
        Itemset ay = new Itemset(Arrays.asList("a", "y"));
        Itemset ax2 = new Itemset(Arrays.asList("a" ,"x"));
        Itemset ay2 = new Itemset(Arrays.asList("a" ,"y"));
        Itemset ax3 = new Itemset(Arrays.asList("a" ,"x"));
        Itemset ay3 = new Itemset(Arrays.asList("a" ,"y"));

        EUCS.put(ax, ay, 10);
        EUCS.increment(ax2, ay2, 2);
        int result2 = EUCS.get(ax3, ay3);
        assertEquals(12, result2);
    }
}