package com.mintit.bos.comcode.persistence;

import org.apache.ibatis.annotations.Mapper;
import com.mintit.bos.comcode.model.CommCode;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommCodeMapper {
    List<CommCode> selectComCodeByCodeGrp(String commCdGrp);
    List<CommCode> selectAll();
    List<CommCode> selectCommCodesWithinAPage(Map map);
    Integer countAll();
    void insertCommCode(CommCode commCode);
    void updateCommCode(CommCode commCode);
}
