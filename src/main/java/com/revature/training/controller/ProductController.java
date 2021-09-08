package com.revature.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.training.pms.model.Product;
import com.revature.training.service.ProductService;
import com.revature.training.service.ProductServiceImpl;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody Product product) {

		ResponseEntity<String> responseEntity = null;
		int productId = product.getProductId();
		String message = null;
		if (productService.isProductExists(productId)) {
			message = "Product with product id : " + productId + " already exists";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		} else {
			productService.addProduct(product);
			message = "Product with product id : " + productId + " saved successfully";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}

	
	@GetMapping("filterProductByPrice/{lowerPrice}/{upperPrice}")
	public List<Product> filterProductByPrice(@PathVariable("lowerPrice") int lowerPrice,
			@PathVariable("upperPrice") int upperPrice) {
		return productService.filterByPrice(lowerPrice, upperPrice);
	}

	@GetMapping()
	public ResponseEntity<List<Product>>  getProducts(@RequestParam(required = false) String productName) {
		//System.out.println("All products called");
		ResponseEntity<List<Product>> responseEntity=null;
		List<Product> products;
		if(productName == null){
			products = productService.getAllProducts();
		}else {
			products = productService.getProductByName(productName);
		}
		if(products.size()==0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		else {
			responseEntity= new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		}
		return responseEntity;
	}

	@GetMapping("{productId}")
	public ResponseEntity<Product>  getProduct(@PathVariable("productId") int productId) {
		System.out.println("getting a  single called : " + productId);
		// hit the service layer to get the product with product id 987
		ResponseEntity<Product> responseEntity=null;
		Product product = new Product();
		String message = null;
		if(productService.isProductExists(productId)) {
		 product = productService.getProductById(productId);
		 responseEntity = new ResponseEntity<Product>(product,HttpStatus.OK);
		}else {
			responseEntity = new ResponseEntity<Product>(product,HttpStatus.OK);
		}
		
		return responseEntity;
	}

	@PutMapping
	  public ResponseEntity<String> updateProduct(@RequestBody Product product) {
	    ResponseEntity<String> responseEntity = null;
	    int productId = product.getProductId();
	    String message = null;
	    if (productService.isProductExists(productId)) {
	      productService.updateProduct(product);
	      message = "Product with product id : " + productId + " updated successfully";
	      responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
	    } else {
	      message = "Product with product id : " + productId + " does not exists";
	      responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
	    }
	    return responseEntity;
	  }
	// Putmapping

	@DeleteMapping("{productId}")
	public String deleteProduct(@PathVariable("productId") int pId) {
		System.out.println("deleteing product called by id : " + pId);
		productService.deleteProduct(pId);
		return "product deleted successfully";
	}
	// Delete mapping

	@GetMapping("/searchByProductName/{productName}")
	public List<Product> getProductByName(@PathVariable("productName") String productName) {
		System.out.println("getting a  single product by product name : " + productName);
		// hit the service layer to get the product with product id 987
		return productService.getProductByName(productName);
	}
}
