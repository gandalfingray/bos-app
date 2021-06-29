package com.mintit.bos.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatorFilterNode extends FilterNode{
    public OperatorFilterNode(String operator){
        super("OperatorNode");
        this.operator = operator;
    }

    private String operator;

    @Override
    public String toString(){
        return "(operator = " + operator+ ")";
    }

    public String assembleSearchCondition(){
        return " "+ operator + " ";
    }


}
