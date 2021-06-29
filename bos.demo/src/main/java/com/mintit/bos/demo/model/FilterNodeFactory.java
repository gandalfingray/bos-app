package com.mintit.bos.demo.model;

import java.util.ArrayList;
import java.util.List;

/*
    DevExtreme DataGrid 에서 multi-filter request 를 처리하기 위한 filter 생성자
    Parameter 는
        1. String 객체이거나
        2. 3 element String을 가진 Array 객체 이거나
        3. Array, string, Array 등을 복합적으로 가진 Array 객체 중 하나이다.
    Parameter 가
        1. String 객체이면, OperatorNode 를 생성해서 반환하고,
        2. 3 element String Array 객체이면, SingleFilterNode 를 생성해서 반환,
        3. 복합적이면 MultiFilterNode 를 생성해서 반환한다.
    각 FilterNode 하위 클래스의 생성자는 obj 를 parameter 로 받는다.
 */
public class FilterNodeFactory {
    public static FilterNode createFilterNode(Object obj){

        // obj 가 String 인 경우는 operator 밖에 없음...
        if(obj instanceof String) {
            return new OperatorFilterNode((String) obj);
        }
        // ["fieldname", "=", "fieldvalue"] 형식의 list인 경우
        else if (obj instanceof ArrayList && ((ArrayList)obj).get(0) instanceof String){
            return new SingleConditionFilterNode((List)obj);
        }
        // [["fieldname", "=", "fieldvalue"], "and", ["fieldname", "=", "fieldvalue"]] 형식의 list인 경우
        // 재귀적으로 생성된다. 즉, MultiConditionFilterNode 생성자 내에서 Factory를 또 호출해서 Node 들을 생성
        else{
            return new MultiConditionFilterNode((List)obj);
        }
    }
}
