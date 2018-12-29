package me.thaithien.associationrules.krishnamoorthy2018efficient.DataStructure;

public class UtilityInfo {

    public int tid;

    public int u;

    public int ru;

    @Override
    public String toString() {
        return "< " + String.valueOf(tid) + " - " + String.valueOf(u) + " - "+ String.valueOf(ru) + " >";
    }

    public UtilityInfo() {
        tid = 0;
        u = 0;
        ru = 0;
    }

    public UtilityInfo(UtilityInfo info) {
        this.tid = info.tid;
        this.u = info.u;
        this.ru = info.ru;
    }
}
