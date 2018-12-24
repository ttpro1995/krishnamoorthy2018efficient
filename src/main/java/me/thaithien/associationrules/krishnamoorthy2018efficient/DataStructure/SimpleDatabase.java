package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import com.google.common.collect.ImmutableList;
import me.thaithien.associationrules.krishnamoorthy2018efficient.Common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleDatabase {

    public List<Transaction> transactionList;

    public Set<String> allItem;

    public ImmutableList<ItemInfo> itemInfos;

    public Map<String, Integer> mapItemEU;

    public Map<String, Integer> mapItemMU;

    /**
     * Table 4 in paper
     * key - value of Transaction Weight Utility
     */
    public Map<String, Integer> mapTWU;

    public List<String> orderItemTWU;

    private int counter = 0;

    /**
     * One transaction per line
     * name,qty space name,qty
     * Example:
     * a,2 b,5 c,3
     * b,9 d,1
     * @param path
     */
    public void loadTransactionFromFile(String path){
        try {
            this.allItem = new HashSet<>();
            Stream<String>  ss = Files.lines(Paths.get(path));
            this.transactionList = ss.map(Transaction::new).collect(Collectors.toList());
            for (Transaction transaction : transactionList){
                counter += 1;
                transaction.TID = counter;
                allItem.addAll(transaction.itemInTransaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * item_name EU MU per line
     * Example
     * a 5 80
     * b 2 64
     * @param path
     */
    public void loadItemInfosFromFile(String path){
        try {
            mapItemEU = new HashMap<>();
            mapItemMU = new HashMap<>();
            Stream<String>  ss = Files.lines(Paths.get(path));
            List<ItemInfo> iflist = ss.map(ItemInfo::new).collect(Collectors.toList());
            for (ItemInfo info : iflist){
                mapItemEU.put(info.itemName, info.EU);
                mapItemMU.put(info.itemName, info.MU);
            }
            this.itemInfos = ImmutableList.copyOf(iflist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calTUforAllTransaction(){
        for (Transaction transaction: transactionList){
            int tu = Common.calTU(transaction, this);
            transaction.TU = tu;
        }
    }

    public void calTWUforAllTransaction(){
        this.mapTWU = new HashMap<>();
        this.orderItemTWU = new ArrayList<>();
        for (String item: this.allItem){
            int twu = Common.calTWU(item, this);
            this.mapTWU.put(item, twu);
            this.orderItemTWU.add(item);
        }

        Collections.sort(this.orderItemTWU, Comparator.comparing(o -> mapTWU.get(o)));
    }

    /**
     * Table 5
     */
    public void orderItemAllTransaction(){
        for (Transaction t: this.transactionList){
            Common.orderItemsByTWU(t, mapTWU);
        }
    }


}
