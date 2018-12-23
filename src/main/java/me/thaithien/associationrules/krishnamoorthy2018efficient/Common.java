package me.thaithien.associationrules.krishnamoorthy2018efficient;

import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.SimpleDatabase;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.Transaction;

import java.util.Arrays;
import java.util.List;

public class Common {

    /**
     * Definition 7
     * Calculate the utility of an itemset X in transaction T
     * @param itemSet a string of itemset (example: ac)
     * @param t transaction to access iu
     * @param db database to access eu
     * @return
     */
    public static int calU(String itemSet, Transaction t, SimpleDatabase db){
        List<String> listOfItem = splitStringToCharacter(itemSet);
        int sumU = 0;
        for (String item: listOfItem){
            int eu = db.mapItemEU.get(item);
            int iu = t.mapItemIU.get(item);
            sumU += iu * eu;
        }
        return sumU;
    }


    /**
     * split string to list of character
     * @param inStr example: ac
     * @return example ["a", "c"]
     */
    public static List<String> splitStringToCharacter(String inStr){
        String[] rStr = inStr.split("(?!^)");
        List<String> result = Arrays.asList(rStr);
        return result;
    }
}
