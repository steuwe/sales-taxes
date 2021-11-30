import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ProductTest {
	
	private BigDecimal defaultValidPrice = BigDecimal.valueOf(1);
	private String defaultValidName = "box of chocolates";

	
	@Test
	void testProductNameAndPriceShouldNotThrow() {
		new Product(defaultValidName, defaultValidPrice);
	}
	
	@Test
	void testNullProductNameShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product(null, defaultValidPrice));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"box of chocolates", "CHOCOLATE", "chocolate bar", "book", "bottle of headache pills"})
	void testProductExemptionShouldNotThrow(String input) {
		Product product = new Product(input, defaultValidPrice);
		assertTrue(product.isExempt());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"music CD", "bottle of perfume"})
	void testProductNonExemptionShouldNotThrow(String input) {
		Product product = new Product(input, defaultValidPrice);
		assertFalse(product.isExempt());
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"", " "})
	void testEmptyOrBlankProductNameShouldThrowIllegalArgumentException(String name) {
		assertThrows(IllegalArgumentException.class, 
				() -> new Product(name, defaultValidPrice));
	}
	
	@Test
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
