package com.mintit.bos.demo.model;

import java.util.ArrayList;
import java.util.List;

public class MultiConditionFilterNode extends FilterNode {
    private List<FilterNode> filterList;

    public MultiConditionFilterNode(List conditions){
        super("MultiConditionNode");

        filterList = new ArrayList<FilterNode>();
        conditions.forEach(condition -> filterList.add(FilterNodeFactory.createFilterNode(condition)));
    }

    @Override
    public String toString(){
        String str = "";
        for(FilterNode filter : filterList)
            str = str + filter.toString();
        return "("+str+")";
    }

    public String assembleSearchCondition(){
        String str = "";
        for(FilterNode filter : filterList)
            str = str + filter.assembleSearchCondition();
        return "("+str+")";
    }
}
