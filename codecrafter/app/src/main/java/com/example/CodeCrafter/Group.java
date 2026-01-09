package com.example.CodeCrafter;

import java.util.List;

public class Group {
    private String name;
    private List<String> children;
    private int iconResourceId; // Resource ID for group icon

    public Group(String name, List<String> children, int iconResourceId) {
        this.name = name;
        this.children = children;
        this.iconResourceId = iconResourceId;
    }

    public String getName() {
        return name;
    }

    public List<String> getChildren() {
        return children;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }
}
