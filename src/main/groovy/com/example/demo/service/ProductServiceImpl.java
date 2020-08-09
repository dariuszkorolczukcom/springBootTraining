package com.example.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Product;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	private static Map<Integer, Product> productRepo = new HashMap<>();
	
	   static {
	      Product honey = new Product();
	      honey.setId(1);
	      honey.setName("Honey");
	      productRepo.put(honey.getId(), honey);

	      Product almond = new Product();
	      almond.setId(2);
	      almond.setName("Almond");
	      productRepo.put(almond.getId(), almond);
	   }
	   @Override
	   public void createProduct(Product product) {
			logger.info("this is a createProduct message");
	      productRepo.put(product.getId(), product);
	   }
	   @Override
	   public void updateProduct(Integer id, Product product) {
			logger.info("this is a updateProduct message");
	      productRepo.remove(id);
	      product.setId(id);
	      productRepo.put(id, product);
	   }
	   @Override
	   public void deleteProduct(Integer id) {
			logger.info("this is a deleteProduct message");
	      productRepo.remove(id);

	   }
	   @Override
	   public Collection<Product> getProducts() {
			logger.info("this is a getProducts message");
	      return productRepo.values();
	   }

}
