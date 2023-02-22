package com.eddieliao.springbootmall.dao;

import com.eddieliao.springbootmall.constant.ProductCategory;
import com.eddieliao.springbootmall.dto.ProductRequest;
import com.eddieliao.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category,String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
