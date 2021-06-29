package com.mintit.bos.demo.persistence;

import com.mintit.bos.demo.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    Product selectProductById(Long prodId);
    List<Product> selectAllProducts();
    List<Product> selectProductsWithinAPage(Map map);
    Integer countAllProducts(Map map);
    void insertProduct(Product product);
    void updateProduct(Product product);

}