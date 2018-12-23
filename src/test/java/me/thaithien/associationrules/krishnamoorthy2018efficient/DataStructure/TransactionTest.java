package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTest {
    @Test
    public void testConstructor(){
        String inputStr = "a,1 c,2 d,5";
        Transaction t = new Transaction(inputStr);
        for (TransactionItem ti : t.transactionContent){
            System.out.println(ti);
        }

        assertEquals(t.transactionContent.get(0).IU, 1);
        assertEquals(t.transactionContent.get(1).IU, 2);
        assertEquals(t.transactionContent.get(2).IU, 5);

        System.out.println(t);
    }
}