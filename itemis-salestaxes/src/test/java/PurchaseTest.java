import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class PurchaseTest {
	
	@Test
	void getPriceForMultipleItems() {
		Purchase purchase = new Purchase(2, false, "books", BigDecimal.valueOf(12.49));
		BigDecimal price = purchase.getPriceWithoutTaxes();
		BigDecimal expected = BigDecimal.valueOf(24.98);
		assertTrue(price.compareTo(expected) == 0);
	}

	@Test
	void getPriceForBookShouldNotThrow() {
		Purchase purchase = new Purchase(1, false, "book", BigDecimal.valueOf(12.49));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(12.49);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}

	@Test
	void getPriceForFoodShouldNotThrow() {
		Purchase purchase = new Purchase(1, false, "chocolate bar", BigDecimal.valueOf(0.85));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(0.85);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void getPriceForMedicalProductShouldNotThrow() {
		Purchase purchase = new Purchase(1, false, "packet of headache pills", BigDecimal.valueOf(9.75));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(9.75);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}

	@Test
	void calculateSalesTaxForNonExemptProductShouldNotThrow() {
		Purchase purchase = new Purchase(1, false, "music cd", BigDecimal.valueOf(14.99));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(16.49);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void calculateTaxForImportedNonExemptProductShouldNotThrow() {
		Purchase purchase = new Purchase(1, true, "bottle of perfume", BigDecimal.valueOf(27.99));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(32.19);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void calculateTaxForImportedExemptProductShouldNotThrow() {
		Purchase purchase = new Purchase(1, true, "box of chocolates", BigDecimal.valueOf(10.00));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(10.50);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}

	@Test
	void calculateSalesTaxForNonExemptImportedProductShouldNotThrow() {
		Purchase purchase = new Purchase(1, true, "bottle of perfume", BigDecimal.valueOf(47.50));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(54.65);
		System.out.println(actual_price);
		System.out.println(purchase.getAmountTaxedBySalesTax());
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void calculateSalesTaxForExemptImportedProductShouldNotThrow() {
		Purchase purchase = new Purchase(1, true, "box of chocolates", BigDecimal.valueOf(10.00));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(10.50);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	

}
