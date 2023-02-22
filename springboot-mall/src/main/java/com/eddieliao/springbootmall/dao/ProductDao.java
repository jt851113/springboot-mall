package com.eddieliao.springbootmall.dao;

import com.eddieliao.springbootmall.dto.ProductQueryParams;
import com.eddieliao.springbootmall.dto.ProductRequest;
import com.eddieliao.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
