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

    // Parameter Name
    private List filter;

    // Parameter filter is conversed into filterNode
    @Setter(AccessLevel.PRIVATE)
    private FilterNode filterNode;

    // filterNode is conversed into Search Condition String for SQL Generation
    // searchCondition 은 computed value 이므로 setter 를 두지 않는다.
    @Setter(AccessLevel.PRIVATE)
    private String searchCondition;

    // Sort
    private List<HashMap> sort;

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

    public void setSort(List<Map> sort){
        if(sort == null)
            return;

        this.sort = new ArrayList<HashMap>();

        int i=0;
        for(Map sortItem : sort){
            HashMap sortCondition = new HashMap();
            sortCondition.put("selector",((String)sortItem.get("selector")).replaceAll("([A-Z]+)","\\_$1").toLowerCase());
            sortCondition.put("desc", sortItem.get("desc"));
            this.sort.add(i, sortCondition);
            i++;
        }
    }

    public Map getPageInfo(){
        Map map = new HashMap();

        map.put("limit", this.limit);
        map.put("offset", this.offset);
        if(this.searchCondition != null)
            map.put("searchCondition", this.searchCondition);

        if(this.sort != null)
            map.put("sort", this.sort);

        return map;
    }

    public void setFilter(List filter) {
        this.filter = filter;
        this.filterNode = FilterNodeFactory.createFilterNode(filter);
        this.searchCondition = this.filterNode.assembleSearchCondition();
        System.out.println(searchCondition);
    }

}
