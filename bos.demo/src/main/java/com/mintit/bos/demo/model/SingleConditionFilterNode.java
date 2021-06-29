package com.mintit.bos.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/*
    ConditionFilterNode 는 하나의 condition 인 경우가 있고,
    여러 Condition 을 하위에 가질 수 있다.
    In case a condition, variables below is used
        fields
        comparator
        value
 */

@Getter
@Setter
public class SingleConditionFilterNode extends FilterNode {
    private String field;
    private String comparator;
    private Object value;

    public SingleConditionFilterNode(List condition){
        super("SingleConditionNode");
        if(condition.size()==3 && condition.get(0) instanceof String){
            field = (String)condition.get(0);
            comparator = (String)condition.get(1);
            value = condition.get(2);
        }
    }
    public SingleConditionFilterNode(String field, String comparator, Object value){
        super("SingleConditionNode");
        this.field = field;
        this.comparator = comparator;
        this.value = value;
    }

    @Override
    public String toString(){
        return "(field = " + field +", comparator = "+ comparator +", value = "+ value +")";
    }

    public String assembleSearchCondition(){
        String condition="";
        String comparativeOperator="";
        String fieldName="";

        // Search condition 값 설정
        if(this.value instanceof Integer){
            condition = ((Integer)value).toString();
        }
        else{
            condition = " \'"+ value +"\'";
        }

        // 비교 연산자 conversion
        switch(comparator){
            case "contains":
                comparativeOperator = "like";
                condition = "\'%"+value+"%\'";
                break;
            case "notcontains":
                comparativeOperator = "not like";
                condition = "\'%"+value+"%\'";
                break;
            case "startswith":
                comparativeOperator = "like";
                condition = "\'"+value+"%\'";
                break;
            case "endswith":
                comparativeOperator = "like";
                condition = "\'%"+value+"\'";
                break;
            default:
                comparativeOperator = comparator;
        }

        // Parameter 명을 DB 컬럼명으로 변환... camelcase to under bar expression...
        fieldName = this.convertCamelCaseToFieldName(field);

        // SQL 형태의 조건식 반환
        return "(" + fieldName +" "+ comparativeOperator +" " + condition +")";
    }

    // Source Code referenced to
    // https://stackoverflow.com/questions/10310321/regex-for-converting-camelcase-to-camel-case-in-java
    private String convertCamelCaseToFieldName(String camelCase){

        return camelCase.replaceAll("([A-Z]+)","\\_$1").toLowerCase();
    }
}
