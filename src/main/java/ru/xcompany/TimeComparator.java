package ru.xcompany;

import java.util.Comparator;

public class TimeComparator implements Comparator<Meet> {

    @Override
    public int compare(Meet o1, Meet o2) {
        if (o1.getStartTime().equals(o2.getStartTime())) {
            return 0;
        }
        if (o1.getStartTime().after(o2.getStartTime())) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
