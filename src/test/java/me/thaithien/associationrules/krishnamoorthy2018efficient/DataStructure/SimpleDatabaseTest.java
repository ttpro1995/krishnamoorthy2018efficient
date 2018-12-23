package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleDatabaseTest {

    @Test
    public void loadTransactionFromFile() {
        String filePath = "testdata/transaction_data.txt";
        SimpleDatabase db = new SimpleDatabase();
        db.loadTransactionFromFile(filePath);
        for (Transaction transaction : db.transactionList){
            System.out.println(transaction);
        }
    }

    @Test
    public void loadItemInfosFromFile() {
        String filePath = "testdata/item_profit_and_minimum_thresholds.txt";
        SimpleDatabase db = new SimpleDatabase();
        db.loadItemInfosFromFile(filePath);
        for (ItemInfo info : db.itemInfos){
            System.out.println(info);
        }
    }

    @Test
    public void callTUforAllTransaction() {
        String transactionFilePath = "testdata/transaction_data.txt";
        String eumufilePath = "testdata/item_profit_and_minimum_thresholds.txt";
        SimpleDatabase db = new SimpleDatabase();
        db.loadTransactionFromFile(transactionFilePath);
        db.loadItemInfosFromFile(eumufilePath);
        db.callTUforAllTransaction();
        for (Transaction transaction : db.transactionList){
            System.out.println(transaction);
        }

        assertEquals(db.transactionList.get(0).TU, 11);
        assertEquals(db.transactionList.get(1).TU, 70);
        assertEquals(db.transactionList.get(2).TU, 33);
        assertEquals(db.transactionList.get(3).TU, 29);
        assertEquals(db.transactionList.get(4).TU, 27);
        assertEquals(db.transactionList.get(5).TU, 33);
        assertEquals(db.transactionList.get(6).TU, 18);
        assertEquals(db.transactionList.get(7).TU, 21);
    }
}