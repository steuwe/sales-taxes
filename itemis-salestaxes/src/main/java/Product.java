import java.math.BigDecimal;

public class Product {

	private String name;
	private BigDecimal price;
	private boolean isExempt;
	
	public Product(String name, BigDecimal price) {
		try {
			setName(name);
			setPrice(price);
			setExemption(name);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Product could not be created: " + e.getMessage());
		}
	}
	
	private void validateName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Product name is null!");
		}
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name is blank!");
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
	
	private void setExemption(String name) {
		// depending on how products are organised (classes, database, ...) this could be done in a separate class
		if (isFood() || isMedicalProduct() || isBook()) {
			isExempt = true;
		} else {
			isExempt = false;
		}
	}
	
	private boolean isFood() {
		if (name.toLowerCase().contains("chocolate")) {
			return true;
		}
		return false;
	}
	
	private boolean isMedicalProduct() {
		if (name.toLowerCase().contains("pills")) {
			return true;
		}
		return false;
	}
	
	private boolean isBook() {
		if (name.toLowerCase().contains("book")) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
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
