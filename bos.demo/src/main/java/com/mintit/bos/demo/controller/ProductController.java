package com.mintit.bos.demo.controller;

import com.mintit.bos.demo.model.PagedGrid;
import com.mintit.bos.demo.model.Product;
import com.mintit.bos.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductService productService;

    @GetMapping("/product/{prodId}")
    public @ResponseBody Product getProduct(@PathVariable("prodId") Long prodId){

        logger.info("[getProduct] with prodId "+prodId);
        Product product = productService.getProductById(prodId);
        logger.info("[getProduct] Product " + product.toString());
        return product;
    }
/*
    curl -X POST -H "Content-Type: application/json" -d  "{ \"prodName\":\"갤럭시워치3\", \"prodPrice\": 1000, \"quantity\":2 }" http://localhost:9091/product/
 */
    @PostMapping("/product")
    public String insertProduct(@RequestBody Product product){

        logger.debug("Insert a product...");
        try {
            productService.addProduct(product);
            return "Success";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/product/all")
    public @ResponseBody List<Product> getProducts(){

        List<Product> products = productService.getAllProducts();
        logger.debug("[getProduct] Products queried are " + products.size());
        return products;
    }

//    @GetMapping("/products")
//    public HashMap getProductsWithinAPage(@RequestParam HashMap map){
//
//        System.out.println("[getProductWithinAPage] parameters are " + map.toString());
//
//        Integer pageNo = Integer.parseInt((String)map.get("pageNo"));
//        Integer itemsPerPage = Integer.parseInt((String)map.get("itemsPerPage"));
//        Integer offset = (pageNo-1)*itemsPerPage;
//        Integer limit = itemsPerPage;
//        Integer totalItems;
//
//        map.put("offset", offset);
//        map.put("limit", limit);
//        System.out.println("[getProductWithinAPage] offset "+ offset.toString());
//
//        map.put("items", productService.getProductsWithinAPage(map));
//
////        System.out.println("[getProductWithinAPage] products are " + products.size());
//
//        return map;
//    }

    @GetMapping("/product")
    public ResponseEntity<PagedGrid> getProductsWithinAPage(@RequestBody HashMap map){

        PagedGrid<Product> pagedGrid = new PagedGrid<Product>();
        pagedGrid.setPageInfo(map);
        pagedGrid.setTotalItems((productService.countAllProducts(map)));

        logger.debug("parameters are " + map.toString());

        pagedGrid.setItems(productService.getProductsWithinAPage(map));

        return ResponseEntity.ok().body(pagedGrid);
    }

    @PostMapping("/product/list")
    public ResponseEntity<PagedGrid> getProductsWithinAPageAndGridOptions(@RequestBody PagedGrid pagedGrid){

        //PagedGrid<Product> pagedGrid = new PagedGrid<Product>();
        Map map = pagedGrid.getPageInfo();

        pagedGrid.setTotalItems((productService.countAllProducts(map)));

        logger.debug("[getProductWithinAPage] parameters are " + map.toString());

        pagedGrid.setItems(productService.getProductsWithinAPage(map));

        return ResponseEntity.ok().body(pagedGrid);
    }

    @PutMapping("/product/{prodId}")
    public ResponseEntity updateProduct(@PathVariable("prodId") Long prodId, @RequestBody Product product){

        product.setProdId(prodId);

        try {
            productService.updateProduct(product);
            return ResponseEntity.ok().body("Success!!");
        }
        catch(Exception e){
            return new ResponseEntity("update failed...", HttpStatus.BAD_REQUEST);
        }
    }
}
