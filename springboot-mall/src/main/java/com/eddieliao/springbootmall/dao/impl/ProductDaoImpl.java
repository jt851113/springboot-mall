package com.eddieliao.springbootmall.dao.impl;

import com.eddieliao.springbootmall.dao.ProductDao;
import com.eddieliao.springbootmall.dto.ProductQueryParams;
import com.eddieliao.springbootmall.dto.ProductRequest;
import com.eddieliao.springbootmall.model.Product;
import com.eddieliao.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT product_id,product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date " +
                "FROM product WHERE 1=1";
        // WHERE 1=1 是為了能夠順利拼接下面的category部分
        Map<String, Object> map = new HashMap<>();

        if(productQueryParams.getCategory() != null){
            sql = sql + " AND category = :category";
            // AND前記得空白鍵
            map.put("category",productQueryParams.getCategory().name());
            // 因為是enum 所以要用.name()轉換成字串
        }
        if(productQueryParams.getSearch() != null){
            sql = sql + " AND product_name LIKE :search";
            map.put("search","%"+ productQueryParams.getSearch() +"%");
        }

        // 我們有給定default value 不用check null
        // 排序
        sql = sql + " ORDER BY " + productQueryParams.getOrderBy()+" "+
                productQueryParams.getSort();

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",productQueryParams.getLimit());
        map.put("offset",productQueryParams.getOffset());



        List<Product> productList = namedParameterJdbcTemplate.query(sql,map, new ProductRowMapper());

        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id,product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size() > 0){
            return productList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, " +
                ":createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl," +
                                    "price = :price, stock = :stock, description = :description," +
                                    "last_modified_date = :lastModifiedDate WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productName",productRequest.getProductName());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("category",productRequest.getCategory().toString());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        map.put("lastModifiedDate",new Date());

        namedParameterJdbcTemplate.update(sql, map);


    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);

        namedParameterJdbcTemplate.update(sql, map);

    }
}
