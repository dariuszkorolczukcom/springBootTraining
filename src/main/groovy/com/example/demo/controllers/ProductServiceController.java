package com.example.demo.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exceptions.ProductNotfoundException;
import com.example.demo.pojo.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductServiceController {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ProductService productService;

	@Value("${server.port}")
	private String port;

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

	@RequestMapping(value = "/service/products")
	public ResponseEntity<Object> getProduct() {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	@RequestMapping(value = "/service/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {

		productService.updateProduct(id, product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/service/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/service/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	// ##########################################

	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProducts() {
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(productRepo.get(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProductNoService(@RequestBody Product product) {
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProductNoService(@PathVariable("id") Integer id, @RequestBody Product product) {
		if (!productRepo.containsKey(id))
			throw new ProductNotfoundException();
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteNoService(@PathVariable("id") Integer id) {
		productRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	// ##########################################

	@RequestMapping(value = "/template/products")
	public String getProductList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:" + port + "/products", HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@RequestMapping(value = "/template/products", method = RequestMethod.POST)
	public String createProducts(@RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

		return restTemplate.exchange("http://localhost:" + port + "/products", HttpMethod.POST, entity, String.class)
				.getBody();
	}

	@RequestMapping(value = "/template/products/{id}", method = RequestMethod.PUT)
	public String updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

		return restTemplate
				.exchange("http://localhost:" + port + "/products/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

	@RequestMapping(value = "/template/products/{id}", method = RequestMethod.DELETE)
	public String deleteProduct(@PathVariable("id") String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(headers);

		return restTemplate
				.exchange("http://localhost:" + port + "/products/" + id, HttpMethod.DELETE, entity, String.class)
				.getBody();
	}

}
