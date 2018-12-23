package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

/**
 * A item in transaction
 */
public class TransactionItem {

    /**
     * Item name
     */
    public final String name;

    /**
     * Internal Utility (Purchase Qty)
     */
    public final int IU;

    public TransactionItem(String name, int IU) {
        this.name = name;
        this.IU = IU;
    }

    /**
     * Init from String with format
     * name,IU
     * @param transactionItemStr
     */
    public TransactionItem(String transactionItemStr){
        String[] itemqty = transactionItemStr.split(",");
        this.name = itemqty[0];
        this.IU = Integer.valueOf(itemqty[1]);
    }
}
