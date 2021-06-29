package com.mintit.bos.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public abstract class FilterNode {
    public FilterNode(String nodeType){
        this.nodeType = nodeType;
    };

    public abstract String assembleSearchCondition();

    protected String nodeType; // operator or condition
}
