package me.thaithien.associationrules.krishnamoorthy2018efficient;

import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.Itemset;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.SimpleDatabase;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CommonTest {

    @Test
    public void calU() {
        // String itemSet = "ac";
        Itemset itemSet = new Itemset(Arrays.asList("a", "c"));
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
        db.calTUforAllTransaction();
        db.calTWUforAllTransaction();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calTU() {
        int tu = Common.calTU(db.transactionList.get(2), db);
        assertEquals(tu, 33);
    }

    @Test
    public void calTWU() {
        int twu = Common.calTWU("g", db);
        assertEquals(twu, 97);
    }

    @Test
    public void orderItemsByTWU() {
        Transaction t = db.transactionList.get(0);
        Common.orderItemsByTWU(t, db.mapTWU);
        System.out.println(t);
        List<String> itemStr = Arrays.asList("a", "b", "c", "d", "e", "f");
        Common.orderItemsByTWU(itemStr, db.mapTWU);
        System.out.print(itemStr.toString());
    }

    @Test
    public void ext() {
//        List<String> s1 = Common.extension("fd", db);
//        List<String> s2 = Common.extension("b", db);
        Itemset s1 = Common.extension(new Itemset(Arrays.asList("f", "d")), db);
        Itemset s2 = Common.extension(new Itemset(Arrays.asList("b")), db);
        System.out.println(s1);
        System.out.println(s2);
        Itemset s12 = Common.extension(new Itemset(Arrays.asList("f", "d")), db.mapTWU);
        Itemset s22 = Common.extension(new Itemset(Arrays.asList("b")), db.mapTWU);
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void calMIU() {
//        int aMU = Common.calMIU("a", db);
//        int afMU = Common.calMIU("af", db);
        int aMU = Common.calMIU(new Itemset(Arrays.asList("a")), db);
        int afMU = Common.calMIU(new Itemset(Arrays.asList("a", "f")), db);
        assertEquals(80, aMU);
        assertEquals(44, afMU);
        int aMU2 = Common.calMIU(new Itemset(Arrays.asList("a")), db.mapItemMU);
        int afMU2 = Common.calMIU(new Itemset(Arrays.asList("a", "f")), db.mapItemMU);
        assertEquals(80, aMU2);
        assertEquals(44, afMU2);
    }

    @Test
    public void calSMU() {
//        int eaSMU = Common.calSMU("ea", db);
//        int dcSMU = Common.calSMU("dc", db);
//        int fSMU = Common.calSMU("f", db);
//        int gSMU = Common.calSMU("g", db);
        int eaSMU = Common.calSMU(new Itemset(Arrays.asList("e", "a")), db);
        int dcSMU = Common.calSMU(new Itemset(Arrays.asList("d", "c")), db);
        int fSMU = Common.calSMU(new Itemset(Arrays.asList("f")), db);
        int gSMU = Common.calSMU(new Itemset(Arrays.asList("g")), db);
        assertEquals(0, dcSMU);
        assertEquals(62, eaSMU);
        assertEquals(44, fSMU);
        assertEquals(57, gSMU);
        int eaSMU2 = Common.calSMU(new Itemset(Arrays.asList("e", "a")), db.mapItemMU, db.mapTWU);
        int dcSMU2 = Common.calSMU(new Itemset(Arrays.asList("d", "c")), db.mapItemMU, db.mapTWU);
        int fSMU2 = Common.calSMU(new Itemset(Arrays.asList("f")), db.mapItemMU, db.mapTWU);
        int gSMU2 = Common.calSMU(new Itemset(Arrays.asList("g")), db.mapItemMU, db.mapTWU);
        assertEquals(0, dcSMU2);
        assertEquals(62, eaSMU2);
        assertEquals(44, fSMU2);
        assertEquals(57, gSMU2);
    }

    @Test
    public void calRU() {
        Itemset ac = new Itemset(Arrays.asList("a", "c"));
        int rac1 = Common.calRU(ac, db.transactionList.get(0), db);
        int rac7 = Common.calRU(ac, db.transactionList.get(6), db);
        assertEquals(2, rac1);
        assertEquals(7, rac7);
        int racAll = Common.calRU(ac, db);
        assertEquals(75, racAll);
    }

    @Test
    public void calRU1() {
    }
}