package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class ItemsetUtilityList {
    private Itemset itemset = new Itemset();

    private List<UtilityInfo> utilityList = new ArrayList<>();

    private int miu;

    private int u;

    private int ru;

    public Itemset getItemset() {
        return itemset;
    }

    public void setItemset(Itemset itemset) {
        this.itemset = itemset;
    }

    public int getU() {
        return u;
    }

    public List<UtilityInfo> getUtilityList() {
        return utilityList;
    }

    public int getMiu() {
        return miu;
    }

    public int getRu() {
        return ru;
    }
}
