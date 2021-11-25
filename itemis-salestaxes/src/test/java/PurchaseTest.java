import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseTest {

	@ParameterizedTest
	@ValueSource(strings = { 
			"1 book at 12.49", 
			"1 imported box of chocolates at 10.00",
			"1  imported  box  of  chocolates  at  10.00", 
			"1 music cd at 12", 
			"2 chocolate bars at 15.99",
			"1 packet of headache pills at 9.75" })
	void parseValidPurchaseShouldNotThrow(String input) {
		Purchase purchase = new Purchase(input);
	}

	@ParameterizedTest
	@ValueSource(strings = { 
			"1  imported  box  of  chocolates  at  10.00", 
			"1 music cd at 12",
			"2 Chocolate bars at 15.99", 
			"2 Chocolate bars at 05.09" })
	void parsePurchaseFringeCasesShouldNotThrow(String input) {
		Purchase purchase = new Purchase(input);
	}

	@ParameterizedTest
	@ValueSource(strings = { 
			"1 imported box of chocolates at -10.00", 
			"1 music cd at -12", 
			"2 Chocolate bars at " })
	void parseInvalidPurchasePriceShouldThrowIllegalArgumentException(String input) {
		Purchase purchase = new Purchase(input);
	}

	@ParameterizedTest
	@ValueSource(strings = { 
			"1  imported at 10.00", 
			"1 at 12.00", 
			"1 music at 12.00" })
	void parseInvalidPurchaseProductShouldThrowIllegalArgumentException(String input) {
		Purchase purchase = new Purchase(input);
	}

	@Test
	void getPriceForBookShouldNotThrow() {
		Purchase purchase = new Purchase("1 book at 12.49");
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(12.49);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}

	@Test
	void getPriceForFoodShouldNotThrow() {
		Purchase purchase = new Purchase("1 chocolate bar at 0.85");
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(0.85);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void getPriceForMedicalProductShouldNotThrow() {
		Purchase purchase = new Purchase("1 packet of headache pills at 9.75");
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(9.75);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}

	@Test
	void calculateSalesTaxForNonExemptProductShouldNotThrow() {
		Purchase purchase = new Purchase("1 music cd at 14.99");
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(16.49);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void calculateTaxForImportedNonExemptProductShouldNotThrow() {
		Purchase purchase = new Purchase("1 imported bottle of perfume at 27.99");
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(32.19);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void calculateTaxForImportedExemptProductShouldNotThrow() {
		Purchase purchase = new Purchase("1 imported box of chocolates at 10.00");
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(10.50);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}

	

}
