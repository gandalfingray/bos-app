package com.mintit.bos.demo.model;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


/*
    DevExtreme Grid Component 와 통신을 위한 데이터 설정용 VO 클래스
*/

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class PagedGrid<T> {

    // Pagination Information

    private Integer pageNo = 1;
    private Integer itemsPerPage = 10;
    private Integer totalItems = 0;
    private Integer offset = 0;
    private Integer limit = 10;

    private List filter;
    private List<FilterNode> filterNodes;

    // Grid data
    private List<T> items;

    public void setPageNo(Integer pageNo){
        this.pageNo = pageNo;
        this.offset = (this.pageNo - 1)*this.itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage){
        this.itemsPerPage = itemsPerPage;
        this.limit = itemsPerPage;
        this.offset = (this.pageNo - 1)*this.itemsPerPage;
    }

    public void setPageInfo(Map map){
        this.pageNo = map.containsKey("pageNo")? Integer.parseInt((String)map.get("pageNo")) : 1;
        this.itemsPerPage = map.containsKey("itemsPerPage")? Integer.parseInt((String)map.get("itemsPerPage")) : 10;

        this.offset = map.containsKey("skip") ? Integer.parseInt((String)map.get("skip")) : (this.pageNo-1) * this.itemsPerPage;
        this.limit = map.containsKey("take") ? Integer.parseInt((String)map.get("take")) : this.itemsPerPage;

        map.put("offset", this.offset);
        map.put("limit", this.limit);
    }

    public void setItems(List items){
        this.items = (items == null)? new ArrayList() : items;
    }

    public void setTotalItems(Integer totalItems){
        this.totalItems = (totalItems == null) ? 0 : totalItems;
    }

    public void setSkip(Integer skip){
        this.offset = skip;
    }

    public void setTake(Integer take){
        this.limit = take;
    }

    public Map getPageInfo(){
        Map map = new HashMap();
        map.put("limit", this.limit);
        map.put("offset", this.offset);

        return map;
    }

    public void setFilter(List filter) {

        this.filter = filter;
        int size = filter.size();
        Predicate predicate = (m) -> {
            return m instanceof String;
        };

        filterNodes = new ArrayList<FilterNode>();

        /*
         filter가 ["fieldname", "=", "fieldvalue"] 이런 형상이라면,
         List이지만 1개의 filter node로 구성된 filter이므로 1개짜리 filterNode를 만들고 끝낸다.
        */
        if(predicate.test(filter.get(0))){
            filterNodes.add(new FilterNode(filter));
            return;
         }

        /*
         filter 형상이 [List, String, List, String, List] 같은 경우,
         List 는 condition type의 FilterNode이고, String은 operator type의 FilterNode
        */
        for (int i = 0 ; i < size ; i++){
            filterNodes.add(predicate.test(filter.get(i))?
                    new FilterNode((String)filter.get(i)):new FilterNode((List)filter.get(i)));
        }

        filterNodes.forEach(item -> System.out.println(item.getNodeType()));
    }

}
