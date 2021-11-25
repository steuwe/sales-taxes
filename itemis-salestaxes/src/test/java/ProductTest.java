import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
	
	private BigDecimal defaultValidPrice = BigDecimal.valueOf(1);
	private String defaultValidName = "box of chocolates";

	
	@Test
	void testProductNameAndPriceShouldNotThrow() {
		Product product = new Product(defaultValidName, defaultValidPrice);
	}
	
	void testNullProductNameShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product(null, defaultValidPrice));
	}
	
	@Test
	void testEmptyProductNameShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product("", defaultValidPrice));
	}
	
	void testNullProductPriceShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product(defaultValidName, null));
	}
	
	@Test
	void testNegativeProductPriceShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product(defaultValidName, BigDecimal.valueOf(-1.0)));
	}
	
}
