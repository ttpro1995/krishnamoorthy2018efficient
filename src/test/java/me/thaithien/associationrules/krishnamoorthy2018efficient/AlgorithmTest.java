package me.thaithien.associationrules.krishnamoorthy2018efficient;

import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.SimpleDatabase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AlgorithmTest {

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
    public void mainMHUI() {
        Algorithm.mainMHUI(db);
    }

    @Test
    public void iterationAfter(){
        List<String> testList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        for (String e1: testList){
            for (String e2: testList){
                if (testList.indexOf(e2) > testList.indexOf(e1)){
                    System.out.println(e1 + " " + e2);
                }
            }
        }
    }
}