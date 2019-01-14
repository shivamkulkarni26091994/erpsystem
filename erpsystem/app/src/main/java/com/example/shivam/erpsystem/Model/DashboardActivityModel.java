package com.example.shivam.erpsystem.Model;

public class DashboardActivityModel {

    public String menuName;
    public boolean hasChildren, isGroup;

    public DashboardActivityModel(String menuName, boolean isGroup, boolean hasChildren) {

        this.menuName = menuName;

        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
    }
}
