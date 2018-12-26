package me.thaithien.associationrules.krishnamoorthy2018efficient;

import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.EUCS;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.SimpleDatabase;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.Transaction;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.TransactionItem;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    public static void mainMHUI(SimpleDatabase db) {
        // scan D and compute TWU for db
        db.calTUforAllTransaction();
        db.calTWUforAllTransaction();

        // sort x in T as per TWU ascending order
        db.orderItemAllTransaction();
        List<Integer> tuList = new ArrayList<>();
        for (Transaction t : db.transactionList) {
            int tu = 0;
            for (TransactionItem item : t.transactionContent) {
                int twu = Common.calTWU(item.name, db);
                int smu = Common.calSMU(item.name, db);
                if (twu > smu) { // TWU M Prune
                    tu = tu + Common.calU(item.name, t, db);
                }
            }
            tuList.add(tu);
            t.TU = tu; // update new TU
            for (int i = 0; i< t.transactionContent.size(); i++){
                for (int j = 0 ; j< t.transactionContent.size(); j++){
                    String x1 = t.transactionContent.get(i).name;
                    String x2 = t.transactionContent.get(j).name;
                    if (x1.equals(x2)){
                        continue;
                    }
                    EUCS.increment(x1, x2, t.TU);
                }
            }
        }

        // Iteratively construct 1-itemset ULs
        List<List> listOneItemset = new ArrayList<>();
        for (String item : db.orderItemTWU){ // 1-item already sorted by TWU
            List<String> a = new ArrayList<>();
            a.add(item);
            listOneItemset.add(a);
        }

        //TODO: explore search tree
    }
}