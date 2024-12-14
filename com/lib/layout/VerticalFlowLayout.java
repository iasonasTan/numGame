package com.lib.layout;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class VerticalFlowLayout implements LayoutManager2 {
    int vGap = 2;
    int hGap = 2;
    ArrayList<Component> components = new ArrayList<>();

    public VerticalFlowLayout () {

    }

    public VerticalFlowLayout (int vg, int hg) {
        this.vGap = vg;
        this.hGap = hg;
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        components.add(comp);
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(10000, 10000);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {

    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        components.add(comp);
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        components.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(100, 100);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(10, 10);
    }

    public int getvGap() {
        return vGap;
    }

    public void setvGap(int vGap) {
        this.vGap = vGap;
    }

    public ArrayList<Component> getComponents () {
        return this.components;
    }

    public int gethGap() {
        return hGap;
    }

    public void sethGap(int hGap) {
        this.hGap = hGap;
    }

    @Override
    public void layoutContainer(Container parent) {
        // stuff
        final Dimension pps = parent.getPreferredSize(); // parent preferred size
        int x = hGap;
        int y = vGap;
        int colWidth = 0;
        for (Component c : components) {
            if (!c.isVisible())
                continue;

            Dimension cps = c.getPreferredSize(); // component preferred size
            colWidth = Math.max(c.getWidth(), colWidth);
            if (y+cps.height > pps.height) {
                //x+=colWidth+hGap; it doesn't work :-/
            }
            c.setBounds(x, y, cps.width, cps.height);
            y+=cps.height+vGap;
        }
    }

}
