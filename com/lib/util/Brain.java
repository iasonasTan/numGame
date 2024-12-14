package com.lib.util;

public class Brain {
    private boolean allMore;
    private boolean allLess;

    public int getNearest (int[] vals, int x) {
        updateIndicators(vals, x);

        for (int v : vals) {
            if (v == x) {
                return v;
            }
        }

        int minDistance = Integer.MAX_VALUE;
        int minValIndex = 0;

        for (int i = 0; i < vals.length; i++) {
            int val = vals[i];
            int distance = Math.abs(x-val);

            if (distance<minDistance) {
                minDistance = distance;
                minValIndex = i;
            }
        }

        return vals[minValIndex];
    }

    public void updateIndicators (int[] vals, int x) {
        allLess = true;
        allMore = true;
        for (int v : vals) {
            if (v > x)
                allLess = false;
            if (v < x)
                allMore = false;
        }
    }

    public boolean isAllMore () {
        return allMore;
    }

    public boolean isAllLess () {
        return allLess;
    }

    public int getValue (int a, int b, int x) {
        if (a==b)
            return 0;
        if (a==x)
            return a;
        if (b==x)
            return b;
        if (a>x&&b>x)
            return Math.min(a,b);
        if (a<x&&b<x)
            return Math.max(a,b);
        else {
            int diffA = Math.abs(x-a);
            int diffB = Math.abs(x-b);
            return (diffA>diffB)?b:a;
        }
    }


}
