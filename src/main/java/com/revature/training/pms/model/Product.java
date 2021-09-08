package com.revature.training.pms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "newproducts")
public class Product {

	@Id
	private int productId;
	private String productName;
	private int quantityOnHand;
	private int price;
	
}
