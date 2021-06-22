package com.mintit.bos.comcode.model;

import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CommCode {
    private String commCdGrp;
    private String commCd;
    private String commNm;
    private String refCd1;
    private String refCd2;
    private String refCd3;
    private String defltYn = "Y";
    private String rmrk;
    private Integer sortNo;
    private String useYn = "Y";
    private String sysUseYn = "Y";
    private String inptMenuId;
    private String regrId;
    private Date regDate;
    private String chgrId;
    private Date chgDate;
    private Boolean hasChild;
    private Integer children;

    public void setChildren(Integer children){
        this.children = children;
        hasChild = (this.children > 0 )? true : false;
    }
}
