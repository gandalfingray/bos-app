package com.mintit.bos.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class TreeListRequestData {


    private List filters;

    /*
     * Computed variables form filters
     */
    private List<FilterNode> filterNodes;

    /*
     * Parameter in the Request Body from Tree List
     * parentIds : Ids are key fields of the expanded node in TreeList
     * */
    private List<String> parentIds;

    public void setFilters(List filters) {
//
//        this.filters = filters;
//        int size = filters.size();
//        Predicate predicate = (m) -> {
//            return m instanceof String;
//        };
//
//        filterNodes = new ArrayList<FilterNode>();
//        for (int i = 0 ; i < size ; i++){
//            filterNodes.add(predicate.test(filters.get(i))?
//                    new FilterNode((String)filters.get(i)):new FilterNode((List)filters.get(i)));
//        }
//
//        System.out.println("filtreNodes are " + filterNodes.size());
    }

}
