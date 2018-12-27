package me.thaithien.associationrules.krishnamoorthy2018efficient;

import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.Itemset;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.SimpleDatabase;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.Transaction;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.TransactionItem;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.*;
import java.util.stream.Collectors;

public class Common {

    /**
     * Definition 7
     * Calculate the utility of an itemset X in transaction T
     * @deprecated use Itemset instead of String
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
     * Definition 7
     * @param itemSet
     * @param t
     * @param db
     * @return
     */
    public static int calU(Itemset itemSet, Transaction t, SimpleDatabase db){
        int sumU = 0;
        for (String item: itemSet.content){
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
     * @deprecated
     * @param inStr example: ac
     * @return example ["a", "c"]
     */
    public static List<String> splitStringToCharacter(String inStr){
        String[] rStr = inStr.split("(?!^)");
        List<String> result = Arrays.asList(rStr);
        return result;
    }

    /**
     * definition 10
     * @param item
     * @param db
     * @return
     */
    public static int calTWU(String item, SimpleDatabase db){
        int twu = 0;
        for (Transaction t : db.transactionList){
            if (t.itemInTransaction.contains(item)){
                twu += t.TU;
            }
        }
        return twu;
    }

    /**
     * Definition 14
     * @param t
     * @param mapTWU
     */
    public static void orderItemsByTWU(Transaction t, Map<String, Integer> mapTWU){
        Collections.sort(t.transactionContent, new Comparator<TransactionItem>() {
            @Override
            public int compare(TransactionItem o1, TransactionItem o2) {
                return mapTWU.get(o1.name).compareTo(mapTWU.get(o2.name));
            }
        });
    }

    /**
     * Definition 14
     * @param items
     * @param mapTWU
     */
    public static void orderItemsByTWU(List<String> items, Map<String, Integer> mapTWU){
        Collections.sort(items, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return mapTWU.get(o1).compareTo(mapTWU.get(o2));
            }
        });
    }

    /**
     * Definition 14
     * @param items
     * @param mapTWU
     */
    public static void orderItemsByTWU(Itemset items, Map<String, Integer> mapTWU){
        orderItemsByTWU(items.content, mapTWU);
    }

    /**
     * Definition 15
     * Extension of the itemset
     * @deprecated input itemset as List instead
     * @param itemset
     */
    public static List<String> extension(String itemset, SimpleDatabase db){
        List<String> chars = splitStringToCharacter(itemset);
        String lastchar = chars.get(chars.size()-1);
        int idx = db.orderItemTWU.indexOf(lastchar);
        List<String> result = new ArrayList<>();
        for (int i = idx + 1; i < db.orderItemTWU.size(); i++){
            result.add(db.orderItemTWU.get(i));
        }
        return result;
    }



    /**
     * Definition 15
     * Extension of the itemset
     * @param itemset
     */
    public static Itemset extension(Itemset itemset, SimpleDatabase db){
        String lastItem = itemset.get(itemset.size()-1);
        int idx = db.orderItemTWU.indexOf(lastItem);
        List<String> result = new ArrayList<>();
        for (int i = idx + 1; i < db.orderItemTWU.size(); i++){
            result.add(db.orderItemTWU.get(i));
        }
        Itemset resultItemset = new Itemset(result);
        return resultItemset;
    }

    /**
     * Definition 2
     * @deprecated input itemset as List instead of single string
     * @return
     */
    public static int calMIU(String itemset, SimpleDatabase db){
        if (itemset.length()==0){
            return 0;
        }
        List<String> items = splitStringToCharacter(itemset);
        List<Integer> muList = items.stream().map(item -> db.mapItemMU.get(item)).collect(Collectors.toList());
        return Collections.min(muList);
    }

    /**
     * Definition 2
     * @param itemset
     * @param db
     * @return
     */
    public static int calMIU(Itemset itemset, SimpleDatabase db){
        if (itemset.size()==0){
            return 0;
        }
        List<Integer> muList = itemset.content.stream().map(item -> db.mapItemMU.get(item)).collect(Collectors.toList());
        return Collections.min(muList);
    }


    /**
     * Definition 16
     * @deprecated input itemset as List instead of single string
     * @param itemset
     * @param db
     * @return
     */
    public static int calSMU(String itemset, SimpleDatabase db){
        int a = calMIU(itemset, db);
        StringBuilder stringBuilder = new StringBuilder();
        for (String c : Common.extension(itemset, db)){
            stringBuilder.append(c);
        }
        int b = calMIU(stringBuilder.toString(), db);
        if (a < b){
            return a;
        } else {
            return b;
        }
    }

    /**
     * Definition 16
     * @param itemset
     * @param db
     * @return
     */
    public static int calSMU(Itemset itemset, SimpleDatabase db){
        int a = calMIU(itemset, db);

        int b = calMIU(Common.extension(itemset, db), db);
        if (a < b){
            return a;
        } else {
            return b;
        }
    }
}
