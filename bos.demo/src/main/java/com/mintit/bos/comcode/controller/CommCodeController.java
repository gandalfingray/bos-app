package com.mintit.bos.comcode.controller;

import com.mintit.bos.comcode.model.CommCode;
import com.mintit.bos.comcode.persistence.CommCodeMapper;
import com.mintit.bos.demo.model.PagedGrid;
import com.mintit.bos.demo.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CommCodeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CommCodeMapper mapper;

    @PostMapping("/commCodes")
    public ResponseEntity<PagedGrid> getCommCodesInAPage(@RequestBody PagedGrid pagedGrid){
        Map map = pagedGrid.getPageInfo();
        pagedGrid.setTotalItems((mapper.countAll()));
        pagedGrid.setItems(mapper.selectCommCodesWithinAPage(map));
        return ResponseEntity.ok().body(pagedGrid);
    }

    @GetMapping("/commCodes")
    public ResponseEntity<List> getAllCommCodes(){
        List<CommCode> commCodes = mapper.selectAll();
        return ResponseEntity.ok().body(commCodes);
    }

    @GetMapping("/commCodes/{commCdGrp}")
    public ResponseEntity<List> getComCodeByCodeGrp(@PathVariable("commCdGrp") String commCdGrp){
        List<CommCode> commCodes = mapper.selectComCodeByCodeGrp(commCdGrp);
        return ResponseEntity.ok().body(commCodes);
    }

    /*
        curl -v -X POST  -H "Content-Type: application/json" --cookie "SESSION=NWFlYzdkM2MtNDlkMS00NDkzLTkwYWQtOTAyZGYyMThmNTFm" -d "{\"commCdGrp\": \"root\", \"commCd\" : \"CD999\", \"commNm\" : \"TEST Code\", \"defltYn\" : \"Y\", \"useYn\" : \"Y\", \"sysUseYn\" : \"Y\"}" http://localhost:9091/commCode
    */
    @PostMapping("/commCode")
    public ResponseEntity<String> insertCommCd(@RequestBody CommCode commCd){

        logger.debug("Insert a Common Code...");
        try {
            mapper.insertCommCode(commCd);
            return ResponseEntity.ok().body("Insert Success");
        }catch(Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/commCode/{commCd}")
    public ResponseEntity<String> updateCommCd(@PathVariable("commCd") String commCd, @RequestBody CommCode commCode){
        logger.debug("Update a Common Code...");

        try {
            commCode.setCommCd(commCd);
            mapper.updateCommCode(commCode);
            return ResponseEntity.ok().body("Update Success");
        }catch(Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
