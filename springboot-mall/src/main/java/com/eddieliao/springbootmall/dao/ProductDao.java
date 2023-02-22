package com.eddieliao.springbootmall.dao;

import com.eddieliao.springbootmall.dto.ProductRequest;
import com.eddieliao.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
