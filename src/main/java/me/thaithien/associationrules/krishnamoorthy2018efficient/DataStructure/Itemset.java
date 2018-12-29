package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Itemset {
    public List<String> content = new ArrayList<>();

    /**
     * add new content to itemset
     * @param item
     */
    public void add(String item){
        content.add(item);
    }

    public String get(int i){
        return content.get(i);
    }

    public int size(){
        return content.size();
    }

    public Itemset(List<String> content) {
        this.content = new ArrayList<>(content);
    }

    public Itemset() {
        this.content = new ArrayList<>();
    }

    @Override
    public String toString() {
        return content.toString();
    }

    /**
     * create new itemset = this - o2
     * new itemset that have item in this, without item from o2
     * @param o2
     * @return
     */
    public Itemset substract(Itemset o2){
        Itemset result = new Itemset();
        result.content.addAll(this.content);
        result.content.removeAll(o2.content);
        return result;
    }
}
