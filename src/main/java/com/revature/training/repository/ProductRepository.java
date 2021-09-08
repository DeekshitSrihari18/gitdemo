package com.revature.training.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.training.pms.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	//select * from products where productName = ?
	public List<Product> findByProductName(String productName);
	//select * from products where quantity = ?
	public List<Product> findByQuantityOnHand(int quantityOnHand);
	//select * from products where quantity > ?
	public List<Product> findByQuantityOnHandGreaterThan(int quantityOnHand);
	//select * from products where quantity > ?
	public List<Product> findByQuantityOnHandLessThan(int quantityOnHand);
	
	public List<Product> findByPriceBetween(int lowerPrice,int upperPrice);

}
