package com.example.demo.service;

import java.util.Collection;

import com.example.demo.pojo.Product;

public interface ProductService {
	   public abstract void createProduct(Product product);
	   public abstract void updateProduct(Integer id, Product product);
	   public abstract void deleteProduct(Integer id);
	   public abstract Collection<Product> getProducts();
}
