import java.math.BigDecimal;

public class Product {

	private String name;
	private BigDecimal price;
	
	public Product(String name, BigDecimal price) {
		try {
			validateName(name);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Product could not be created: " + e.getMessage());
		}
		
	}
	
	private boolean validateName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Product name is null!");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Procut name is empty!");
		}
		return true;
	}
	
}
