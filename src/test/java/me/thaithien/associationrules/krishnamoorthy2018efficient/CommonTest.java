package me.thaithien.associationrules.krishnamoorthy2018efficient;

import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.SimpleDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CommonTest {

    @Test
    public void calU() {
        String itemSet = "ac";
        int result = Common.calU(itemSet, db.transactionList.get(0), db);
        assertEquals(result, 9);
    }

    @Test
    public void splitStringToCharacter() {
        String inputString = "acd";
        List<String> result = Common.splitStringToCharacter(inputString);
        assertEquals(result.get(0), "a");
        assertEquals(result.get(1), "c");
        assertEquals(result.get(2), "d");
    }

    SimpleDatabase db;


    @Before
    public void setUp() throws Exception {
        String transactionFilePath = "testdata/transaction_data.txt";
        String eumufilePath = "testdata/item_profit_and_minimum_thresholds.txt";
        this.db = new SimpleDatabase();
        db.loadTransactionFromFile(transactionFilePath);
        db.loadItemInfosFromFile(eumufilePath);
    }

    @After
    public void tearDown() throws Exception {
    }
}