import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ProductTest {

	
	@Test
	void testProductNameShouldNotThrow() {
		Product product = new Product("box of chocolates", BigDecimal.valueOf(1));
	}
	
	void testNullProductNameShouldThrowIllegalArgumentException() {
		Product product = new Product(null, BigDecimal.valueOf(1));
	}
	
	@Test
	void testEmptyProductNameShouldThrowIllegalArgumentException() {
		Product product = new Product("", BigDecimal.valueOf(1));
	}
	
	@Test
	void testProductPriceShouldNotThrow() {
		Product product = new Product("box of chocolates", BigDecimal.valueOf(1.0));
	}
	
	void testNullProductPriceShouldThrowIllegalArgumentException() {
		Product product = new Product("box of chocolates", null);
	}
	
	@Test
	void testNegativeProductPriceShouldThrowIllegalArgumentException() {
		Product product = new Product("", BigDecimal.valueOf(-1.0));
	}
	
}
