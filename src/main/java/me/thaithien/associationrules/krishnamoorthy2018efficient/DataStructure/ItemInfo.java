package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

public class ItemInfo {

    /**
     * item name
     * (Ex: a)
     */
    public final String itemName;

    /**
     * External utility
     * (i.e: Profit $ per unit)
     */
    public final int EU;


    /**
     * Minimum utility
     */
    public final int MU;


    public ItemInfo(String itemName, int EU, int MU) {
        this.itemName = itemName;
        this.EU = EU;
        this.MU = MU;
    }

    /**
     * item_name EU MU per line
     * Example
     * a 5 80
     * @param itemInfoStr
     */
    public ItemInfo(String itemInfoStr){
        String[] info = itemInfoStr.split(" "); // split by space
        this.itemName = info[0];
        this.EU = Integer.parseInt(info[1]);
        this.MU = Integer.parseInt(info[2]);
    }

    @Override
    public String toString() {
        return "("+ itemName + ","
                + String.valueOf(EU) + ","
                + String.valueOf(MU) + ")";
    }
}
