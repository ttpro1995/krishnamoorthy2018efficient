package me.thaithien.associationrules.krishnamoorthy2018efficient;

import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.SimpleDatabase;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.Transaction;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.TransactionItem;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.*;

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
     * Definition 9
     * Calculate the transaction utility
     * @param t
     * @param db
     * @return
     */
    public static int calTU(Transaction t, SimpleDatabase db){
        int sumTU = 0;
        for (TransactionItem item : t.transactionContent){
            sumTU += calU(item.name, t, db);
        }
        return sumTU;
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

    public static int calTWU(String item, SimpleDatabase db){
        int twu = 0;
        for (Transaction t : db.transactionList){
            if (t.itemInTransaction.contains(item)){
                twu += t.TU;
            }
        }
        return twu;
    }

    public static void orderItemsByTWU(Transaction t, Map<String, Integer> mapTWU){
        Collections.sort(t.transactionContent, new Comparator<TransactionItem>() {
            @Override
            public int compare(TransactionItem o1, TransactionItem o2) {
                return mapTWU.get(o1.name).compareTo(mapTWU.get(o2.name));
            }
        });
    }
}
