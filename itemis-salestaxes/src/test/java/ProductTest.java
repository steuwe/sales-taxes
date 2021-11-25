import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ProductTest {

	
	@Test
	void testProductNameShouldNotThrow() {
		Product product = new Product("box of chocolates", BigDecimal.valueOf(1));
	}
	
	void testNullProductNameShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product(null, BigDecimal.valueOf(1)));
	}
	
	@Test
	void testEmptyProductNameShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product("", BigDecimal.valueOf(1)));
	}
	
	@Test
	void testProductPriceShouldNotThrow() {
		Product product = new Product("box of chocolates", BigDecimal.valueOf(1.0));
	}
	
	void testNullProductPriceShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product("box of chocolates", null));
	}
	
	@Test
	void testNegativeProductPriceShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product("", BigDecimal.valueOf(-1.0)));
	}
	
}
