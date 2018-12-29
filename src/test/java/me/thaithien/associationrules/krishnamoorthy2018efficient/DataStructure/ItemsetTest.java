package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ItemsetTest {

    @Test
    public void substract() {
        Itemset itemset1 = new Itemset(Arrays.asList("a", "b", "d", "e"));
        Itemset itemset2 = new Itemset(Arrays.asList("c", "f", "a", "e"));
        Itemset itemset3 = itemset1.substract(itemset2);
        System.out.println(itemset1);
        System.out.println(itemset2);
        System.out.println(itemset3);
    }
}