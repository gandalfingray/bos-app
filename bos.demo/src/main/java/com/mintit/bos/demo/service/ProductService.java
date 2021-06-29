package com.mintit.bos.demo.service;

import com.mintit.bos.demo.model.Product;
import com.mintit.bos.demo.persistence.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product getProductById(Long id) {

        return productMapper.selectProductById(id);
    }

    public List<Product> getAllProducts() {

        return productMapper.selectAllProducts();
    }

    public List<Product> getProductsWithinAPage(Map map){
        return productMapper.selectProductsWithinAPage(map);
    }

    public Integer countAllProducts(Map map){
        return productMapper.countAllProducts(map);
    }

    @Transactional
    public void addProduct(Product product) {
        productMapper.insertProduct(product);
    }

    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }
}