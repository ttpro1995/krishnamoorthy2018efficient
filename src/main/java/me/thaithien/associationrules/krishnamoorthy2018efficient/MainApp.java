package me.thaithien.associationrules.krishnamoorthy2018efficient;

import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.ItemsetUtilityList;
import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.SimpleDatabase;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // require 2 args
        // transaction_data path
        // item_profit_and_minimum_thresholds path
        String transactionDataPath = "testdata/transaction_data.txt";
        String itemProfitAndMinimumThresholdsPath = "testdata/item_profit_and_minimum_thresholds.txt";
        if (args.length >= 2){
            transactionDataPath = args[0];
            itemProfitAndMinimumThresholdsPath = args[1];
        }

        SimpleDatabase db = new SimpleDatabase();
        db.loadTransactionFromFile(transactionDataPath);
        db.loadItemInfosFromFile(itemProfitAndMinimumThresholdsPath);
        db.calTUforAllTransaction();
        db.calTWUforAllTransaction();

        List<ItemsetUtilityList> hui = Algorithm.mainMHUI(db);

        for (ItemsetUtilityList ul : hui){
            System.out.println(ul);
            System.out.println("\n");
        }
    }
}
