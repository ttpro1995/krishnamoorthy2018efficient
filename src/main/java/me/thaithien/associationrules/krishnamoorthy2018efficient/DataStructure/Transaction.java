package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * each item in Table 2 (paper)
 */
public class Transaction {

    /**
     * Transaction ID
     */
    public int TID;

    /**
     * Transaction Item with name and IU
     */
    public List<TransactionItem> transactionContent;

    /**
     * Definition 9
     * The transaction utility TU
     */
    public int TU;

    public Transaction(List<TransactionItem> transactionContent) {
        this.transactionContent = transactionContent;
    }

    /**
     * One transaction per line
     * name,qty space name,qty
     * Example:
     * a,2 b,5 c,3
     * @param transactionStr
     */
    public Transaction(String transactionStr){
        String[] itemQtyList = transactionStr.split(" "); // split by space

        transactionContent = new ArrayList<>();
        for (String itemQty : itemQtyList){
            transactionContent.add(new TransactionItem(itemQty));
        }
    }
}
