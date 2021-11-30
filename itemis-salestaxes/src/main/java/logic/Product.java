package logic;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import database.ProductDatabaseReader;

public class Product {

	private String name;
	private BigDecimal price;
	private boolean isExempt;
	
	public Product(String name, BigDecimal price) throws FileNotFoundException, IOException {
		try {
			setName(name);
			setPrice(price);
			setExemption(name);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Product could not be created: " + e.getMessage());
		}
	}
	
	private void validateName(String name) throws FileNotFoundException, IOException {
		if (name == null) {
			throw new IllegalArgumentException("Product name is null!");
		}
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name is blank!");
		}
		if (!ProductDatabaseReader.checkProductName(name)) {
			throw new IllegalArgumentException("Product name does not exist!");
		}
	}
	
	private void validatePrice(BigDecimal price) {
		if (price == null) {
			throw new IllegalArgumentException("Product price is null");
		}
		if (price.compareTo(BigDecimal.valueOf(0)) < 0) {
			throw new IllegalArgumentException("Product price is negative!");
		}
	}
	
	private void setExemption(String name) throws FileNotFoundException, IOException {
		if (ProductDatabaseReader.checkProductForExemption(name)) {
			isExempt = true;
		} else {
			isExempt = false;
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) throws FileNotFoundException, IOException {
		try {
			validateName(name);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("New product name is invalid: " + e.getMessage());
		}
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		try {
			validatePrice(price);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("New product price is invalid: " + e.getMessage());
		}
		this.price = price;
	}
	
	public boolean isExempt() {
		return isExempt;
	}
}
