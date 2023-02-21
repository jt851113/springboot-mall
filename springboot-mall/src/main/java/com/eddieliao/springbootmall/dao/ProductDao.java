package com.eddieliao.springbootmall.dao;

import com.eddieliao.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
