package com.example.CodeCrafter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Group> groups;

    public CustomExpandableListAdapter(Context context, List<Group> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.chapter_list, null);
        }

        Group group = (Group) getGroup(groupPosition);

        TextView groupName = convertView.findViewById(R.id.chapter_tv);
        ImageView groupIcon = convertView.findViewById(R.id.listImage);
//        groupName.setText(groups.get(groupPosition).getName());
//        groupIcon.setImageResource(groups.get(groupPosition).getIconResourceId());
        groupName.setText(group.getName());
        groupIcon.setImageResource(group.getIconResourceId());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.topics_list, null);
        }
        TextView childName = convertView.findViewById(R.id.topics_tv);
        childName.setText(groups.get(groupPosition).getChildren().get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    private int getLighterGrayColor() {
        int grayColor = Color.GRAY;
        return Color.argb(Math.min(Color.alpha(grayColor) + 50, 330),
                Color.red(grayColor), Color.green(grayColor), Color.blue(grayColor));
    }
}
