package com.mintit.bos.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FilterNode {
    public FilterNode(String operator){
        nodeType = "operator";
        this.operator = operator;
    };
    public FilterNode(List condition){
        nodeType = "condition";
        this.filterCondition = new FilterCondition(condition);
    }

    private String nodeType; // operator or condition
    private String operator;
    private FilterCondition filterCondition;
}
