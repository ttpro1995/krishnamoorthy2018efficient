package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import me.thaithien.associationrules.krishnamoorthy2018efficient.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ItemsetUtilityList {
    private Itemset itemset = new Itemset();

    private List<UtilityInfo> utilityList = new ArrayList<>();

    private int miu = 0;

    private int u = 0;

    private int ru = 0;

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

    public void addUtilityList(UtilityInfo info){
        this.utilityList.add(info);
        u += info.u;
        ru += info.ru;
    }

    /**
     * if tid exist, update
     * else, add
     * @param info
     */
    public void addOrUpdateUtilityList(UtilityInfo info){
        UtilityInfo e = findTid(info.tid);
        if (e == null){ // add
            e = info;
            addUtilityList(e);
        } else { // update
            e.u = info.u;
            e.ru = info.ru;
        }
    }

    public int getMiu() {
        return miu;
    }

    public int getRu() {
        return ru;
    }

    public void setMiu(int miu){
        this.miu = miu;
    }

    @Override
    public String toString() {
        StringJoiner builder = new StringJoiner("\n");
        builder.add(this.itemset.toString());
        builder.add("MIU "+String.valueOf(this.miu));
        builder.add("U "+String.valueOf(this.u));
        builder.add("RU "+String.valueOf(this.ru));
        for (UtilityInfo info : this.utilityList){
            builder.add(info.toString());
        }
        return builder.toString();
    }

    /**
     * find UtilityInfo with tid
     * @param tid
     * @return
     */
    public UtilityInfo findTid(int tid){
        for (UtilityInfo info : utilityList){
            if (info.tid == tid){
                return info;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        return utilityList.isEmpty();
    }

}
