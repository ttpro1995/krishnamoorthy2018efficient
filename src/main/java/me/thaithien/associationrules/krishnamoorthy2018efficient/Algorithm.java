package me.thaithien.associationrules.krishnamoorthy2018efficient;

import me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure.*;

import java.util.*;

public class Algorithm {
    public static void mainMHUI(SimpleDatabase db) {
        // 1-itemset utility list
        Map<String, ItemsetUtilityList> oneULs = new HashMap<>();

        // scan D and compute TWU for db
        db.calTUforAllTransaction();
        db.calTWUforAllTransaction();

        // sort x in T as per TWU ascending order
        db.orderItemAllTransaction();
        List<Integer> tuList = new ArrayList<>();
        for (Transaction t : db.transactionList) {
            int tu = 0;
            for (TransactionItem item : t.transactionContent) {
                int twu = Common.calTWU(item.name, db);
                int smu = Common.calSMU(new Itemset(Arrays.asList(item.name)), db);
                if (twu > smu) { // TWU M Prune
                    tu = tu + Common.calU(item.name, t, db);
                }
            }
            tuList.add(tu);
            t.TU = tu; // update new TU
            for (int i = 0; i< t.transactionContent.size(); i++){
                for (int j = 0 ; j< t.transactionContent.size(); j++){
                    String x1 = t.transactionContent.get(i).name;
                    String x2 = t.transactionContent.get(j).name;
                    if (x1.equals(x2)){
                        continue;
                    }
                    EUCS.increment(x1, x2, t.TU);
                }
            }

            for (TransactionItem item : t.transactionContent){
                ItemsetUtilityList ul;
                if (oneULs.containsKey(item.name)){
                    ul = oneULs.get(item.name);
                } else {
                    ul = new ItemsetUtilityList();
                    ul.setItemset(new Itemset(Arrays.asList(item.name)));
                }
                UtilityInfo utilityInfo = new UtilityInfo();
                utilityInfo.tid = t.TID;
                utilityInfo.u = Common.calU(new Itemset(Arrays.asList(item.name)), t, db);
                utilityInfo.ru = Common.calRU(new Itemset(Arrays.asList(item.name)), t, db);
                ul.addUtilityList(utilityInfo);
                oneULs.put(item.name, ul);
            }
        }



        // Iteratively construct 1-itemset ULs
//        List<List> listOneItemset = new ArrayList<>();
//        for (String item : db.orderItemTWU){ // 1-item already sorted by TWU
//            List<String> a = new ArrayList<>();
//            a.add(item);
//            listOneItemset.add(a);
//        }
        List<ItemsetUtilityList> uls = new ArrayList<>();

        for (ItemsetUtilityList ul : oneULs.values()){
            ul.setMiu(Common.calMIU(ul.getItemset(), db));
            uls.add(ul);
        }

        //sort uls by twu
        Collections.sort(uls, new Comparator<ItemsetUtilityList>() {
            @Override
            public int compare(ItemsetUtilityList o1, ItemsetUtilityList o2) {
                return db.mapTWU.get(o1.getItemset().get(0)).compareTo(db.mapTWU.get(o2.getItemset().get(0)));
            }
        });

        // debug
        for (ItemsetUtilityList ul : uls){
            System.out.println(ul);
            System.out.println("\n\n");
        }

    }

    public static List<Itemset> exploreSearchTree(Itemset prefixP, List<ItemsetUtilityList> uls, Map<String, Integer> mapItemMU){
        List<Itemset> hui = new ArrayList<>();
        for (ItemsetUtilityList x: uls){

        }
        return hui;
    }
}