package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleDatabaseTest {

    SimpleDatabase db;

    @Before
    public void setUp() throws Exception {
        String transactionFilePath = "testdata/transaction_data.txt";
        String eumufilePath = "testdata/item_profit_and_minimum_thresholds.txt";
        this.db = new SimpleDatabase();
        db.loadTransactionFromFile(transactionFilePath);
        db.loadItemInfosFromFile(eumufilePath);
        db.calTUforAllTransaction();
        db.calTWUforAllTransaction();
    }

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
        db.calTUforAllTransaction();
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

    @Test
    public void calculateTWUforAllTransaction() {
        String transactionFilePath = "testdata/transaction_data.txt";
        String eumufilePath = "testdata/item_profit_and_minimum_thresholds.txt";
        SimpleDatabase db = new SimpleDatabase();
        db.loadTransactionFromFile(transactionFilePath);
        db.loadItemInfosFromFile(eumufilePath);
        db.calTUforAllTransaction();
        db.calTWUforAllTransaction();

        for (String item: db.mapTWU.keySet()){
            System.out.println(item + " " + String.valueOf(db.mapTWU.get(item)));
        }

        assertEquals((long) 72, (long) db.mapTWU.get("f"));
        assertEquals((long) 97, (long) db.mapTWU.get("g"));
        assertEquals((long) 124, (long) db.mapTWU.get("d"));
        assertEquals((long) 128, (long) db.mapTWU.get("b"));
        assertEquals((long) 180, (long) db.mapTWU.get("e"));
        assertEquals((long) 186, (long) db.mapTWU.get("a"));
        assertEquals((long) 242, (long) db.mapTWU.get("c"));

    }

    @Test
    public void orderItemAllTransaction() {
        db.orderItemAllTransaction();
        for (Transaction t: db.transactionList){
            System.out.println(t);
        }
    }
}