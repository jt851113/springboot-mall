package com.eddieliao.springbootmall.service.impl;

import com.eddieliao.springbootmall.dao.ProductDao;
import com.eddieliao.springbootmall.model.Product;
import com.eddieliao.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
