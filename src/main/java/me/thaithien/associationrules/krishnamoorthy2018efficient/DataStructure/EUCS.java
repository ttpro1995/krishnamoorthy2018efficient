package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Implement as in paper
 * FHM: Faster High-Utility Itemset Mining using Estimated Utility Co-occurrence Pruning
 * Philippe Fournier-Viger, Cheng-Wei Wu, Souleymane Zida, Vincent S. Tseng
 *
 */
public class EUCS {
    private static Table<String, String, Integer> data = HashBasedTable.create();

    /**
     * put value to eucs
     * order of x1 and x2 is not matter
     * @param x1 item
     * @param x2 item
     * @param value
     */
    public static void put(String x1, String x2, int value){
        data.put(x1, x2, value);
        data.put(x2, x1, value);
    }

    /**
     * get value from eucs
     * x1, x2 value is not matter
     * @param x1 item
     * @param x2 item
     * @return
     */
    public static int get(String x1, String x2){
        if (!data.contains(x1, x2)){
            return 0;
        }
        int value1 = data.get(x1, x2);
        int value2 = data.get(x1, x2);
        assert value1 == value2;
        return value1;
    }

    /**
     * increase value of EUCS(x1, x2) by value
     * init at 0
     * @param x1
     * @param x2
     * @param value
     */
    public static void increment(String x1, String x2, int value){
        int val;
        if (data.contains(x1, x2)){
            val = data.get(x1, x2);
        } else {
            val = 0;
        }
        data.put(x1, x2, val + value);
    }
}
