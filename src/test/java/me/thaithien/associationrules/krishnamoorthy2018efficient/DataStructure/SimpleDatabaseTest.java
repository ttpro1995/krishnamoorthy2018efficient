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
}