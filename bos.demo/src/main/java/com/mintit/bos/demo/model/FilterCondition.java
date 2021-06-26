package com.mintit.bos.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterCondition {
    private String field;
    private String comparator;
    private String value;

    public FilterCondition(List<String> condition){
        field = condition.get(0);
        comparator = condition.get(1);
        value = condition.get(2);
    }
    public FilterCondition(String field, String comparator, String value){
        this.field = field;
        this.comparator = comparator;
        this.value = value;
    }
}
