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
                    String x1str = t.transactionContent.get(i).name;
                    String x2str = t.transactionContent.get(j).name;
                    if (x1str.equals(x2str)){
                        continue;
                    }
                    Itemset x1 = new Itemset(Arrays.asList(x1str));
                    Itemset x2 = new Itemset(Arrays.asList(x2str));
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

        System.out.println("============================");
        // TODO: explore search tree
        List<ItemsetUtilityList> hui = exploreSearchTree(new ItemsetUtilityList(), uls, db.mapItemMU, db.mapTWU);
        for (ItemsetUtilityList ul : hui){
            System.out.println(ul);
            System.out.println("\n\n");
        }
    }

    public static List<ItemsetUtilityList> exploreSearchTree(ItemsetUtilityList prefixP,
                                                  List<ItemsetUtilityList> uls,
                                                  Map<String, Integer> mapItemMU,
                                                  Map<String, Integer> mapTWU){
        List<ItemsetUtilityList> hui = new ArrayList<>();
        for (int i = 0; i < uls.size(); i++){
            ItemsetUtilityList x = uls.get(i);
            if (x.getU() >= x.getMiu()){
                hui.add(x);
            }
            List<ItemsetUtilityList> exULs = new ArrayList<>();
            //  U-M-Prune
            if ((x.getU() + x.getRu()) >= Common.calSMU(x.getItemset(), mapItemMU, mapTWU)){
                // for each utility list y after x in ULs do
                for (int j = i + 1; j < uls.size(); j++){
                    ItemsetUtilityList y = uls.get(j);

                    Itemset x_p = x.getItemset().substract(prefixP.getItemset());
                    Itemset y_p = y.getItemset().substract(prefixP.getItemset());

                    // eucs m prune
                    if (EUCS.get(x_p, y_p) >= Common.calSMU(x.getItemset(), mapItemMU, mapTWU)){
                        ItemsetUtilityList ul_xy = Algorithm.constructUtilityList(prefixP, x, y, mapItemMU, mapTWU);
                        if (ul_xy != null){
                            exULs.add(ul_xy);
                        }
                    }

                }
            }
            List<ItemsetUtilityList> childHui = Algorithm.exploreSearchTree(x, exULs, mapItemMU, mapTWU);
            hui.addAll(childHui);
        }
        return hui;
    }

    public static ItemsetUtilityList constructUtilityList(ItemsetUtilityList p,
                                                          ItemsetUtilityList px,
                                                          ItemsetUtilityList py,
                                                          Map<String, Integer> mapItemMU,
                                                          Map<String, Integer> mapTWU){
        ItemsetUtilityList pxy = new ItemsetUtilityList();
        pxy.setItemset(px.getItemset().add(py.getItemset()));
        int tutil = px.getU() + px.getRu(); // LA-M-Prune

        for (UtilityInfo ex: px.getUtilityList()){
            UtilityInfo ey = py.findTid(ex.tid);
            if (ey != null) { // if exist ey in Py and ex.tid = ey.tid
                UtilityInfo exy = new UtilityInfo();
                if (!p.isEmpty()){
                    UtilityInfo e = p.findTid(ex.tid); // find e in P such that e.tid == ex.tid
                    exy.tid = ex.tid;
                    exy.u = ex.u + ey.u - e.u;
                    exy.ru = ey.ru;
                } else { // p is empty
                    exy.tid = ex.tid;
                    exy.u = ex.u + ey.u;
                    exy.ru = ey.ru;
                }
                pxy.addOrUpdateUtilityList(exy);
            } else { // recompute tutil and apply LA-M-Prune
                tutil = tutil - ex.u - ex.ru;
                if (tutil < Common.calSMU(px.getItemset(), mapItemMU, mapTWU)){
                    return null;
                }
            }
        }
        return pxy;
    }
}