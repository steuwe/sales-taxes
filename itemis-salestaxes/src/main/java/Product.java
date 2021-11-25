import java.math.BigDecimal;

public class Product {

	private String name;
	private BigDecimal price;
	
	public Product(String name, BigDecimal price) {
		try {
			setName(name);
			setPrice(price);
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
}
