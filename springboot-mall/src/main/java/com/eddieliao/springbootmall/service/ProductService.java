package com.eddieliao.springbootmall.service;

import com.eddieliao.springbootmall.dto.ProductRequest;
import com.eddieliao.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
