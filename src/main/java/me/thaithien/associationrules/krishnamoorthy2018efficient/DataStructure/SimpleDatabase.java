package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleDatabase {

    public List<Transaction> transactionList;

    public ImmutableList<ItemInfo> itemInfos;

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
            Stream<String>  ss = Files.lines(Paths.get(path));
            this.transactionList = ss.map(Transaction::new).collect(Collectors.toList());
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
            Stream<String>  ss = Files.lines(Paths.get(path));
            List<ItemInfo> iflist = ss.map(ItemInfo::new).collect(Collectors.toList());
            this.itemInfos = ImmutableList.copyOf(iflist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
