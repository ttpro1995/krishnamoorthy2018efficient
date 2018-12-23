package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import com.google.common.collect.ImmutableList;

import java.util.*;

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
    public int TU = 0;

    /**
     * dictionary of item - iu for fast access
     */
    public Map<String, Integer> mapItemIU;

    /**
     * item in transaction for quick access
     */
    public Set<String> itemInTransaction;

    public Transaction(List<TransactionItem> transactionContent) {
        mapItemIU = new HashMap<>();
        this.transactionContent = transactionContent;
        for (TransactionItem item : this.transactionContent){
            mapItemIU.put(item.name, item.IU);
        }
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
        mapItemIU = new HashMap<>();
        transactionContent = new ArrayList<>();
        for (String itemQty : itemQtyList){
            TransactionItem item = new TransactionItem(itemQty);
            transactionContent.add(item);
            mapItemIU.put(item.name, item.IU);
        }
    }

    @Override
    public String toString() {
        StringJoiner contentStr = new StringJoiner(",");
        StringJoiner iuStr = new StringJoiner(",");
        for (TransactionItem item : this.transactionContent){
            contentStr.add(item.name);
            iuStr.add(String.valueOf(item.IU));
        }
        return "T_"+String.valueOf(TID) + " " + contentStr.toString() + " " + iuStr.toString() + " " + String.valueOf(this.TU);
    }
}
