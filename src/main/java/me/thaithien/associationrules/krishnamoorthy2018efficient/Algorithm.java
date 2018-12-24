package me.thaithien.associationrules.krishnamoorthy2018efficient;

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
        }
    }
}