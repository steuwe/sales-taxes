import java.math.BigDecimal;

public class Product {

	private String name;
	private BigDecimal price;
	
	public Product(String name, BigDecimal price) {
		try {
			validateName(name);
			validatePrice(price);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Product could not be created: " + e.getMessage());
		}
		this.name = name;
		this.price = price;
		
	}
	
	private void validateName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Product name is null!");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Procut name is empty!");
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
	
}
