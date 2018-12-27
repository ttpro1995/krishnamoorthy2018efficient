package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import java.util.ArrayList;
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
}
