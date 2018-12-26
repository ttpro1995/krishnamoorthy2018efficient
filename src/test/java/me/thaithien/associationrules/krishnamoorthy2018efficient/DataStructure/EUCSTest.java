package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import org.junit.Test;

import static org.junit.Assert.*;

public class EUCSTest {

    @Test
    public void put() {
        EUCS.put("a", "b", 1);
        int result = EUCS.get("a", "b");
        assertEquals(1, result);
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
    }
}