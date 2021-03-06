package unitTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import logic.Purchase;

public class PurchaseTest {
	
	@Test
	void getPriceForMultipleItems() throws FileNotFoundException, IOException {
		Purchase purchase = new Purchase(2, false, "book", BigDecimal.valueOf(12.49));
		BigDecimal price = purchase.getPriceWithoutTaxes();
		BigDecimal expected = BigDecimal.valueOf(24.98);
		assertTrue(price.compareTo(expected) == 0);
	}

	@Test
	void getPriceForBook() throws FileNotFoundException, IOException {
		Purchase purchase = new Purchase(1, false, "book", BigDecimal.valueOf(12.49));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(12.49);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}

	@Test
	void getPriceForFood() throws FileNotFoundException, IOException {
		Purchase purchase = new Purchase(1, false, "chocolate bar", BigDecimal.valueOf(0.85));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(0.85);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void getPriceForMedicalProduct() throws FileNotFoundException, IOException {
		Purchase purchase = new Purchase(1, false, "packet of headache pills", BigDecimal.valueOf(9.75));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(9.75);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}

	@Test
	void calculateSalesTaxForNonExemptProduct() throws FileNotFoundException, IOException {
		Purchase purchase = new Purchase(1, false, "music cd", BigDecimal.valueOf(14.99));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(16.49);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void calculateTaxForImportedNonExemptProduct() throws FileNotFoundException, IOException {
		Purchase purchase = new Purchase(1, true, "bottle of perfume", BigDecimal.valueOf(27.99));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(32.19);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void calculateTaxForImportedExemptProduct() throws FileNotFoundException, IOException {
		Purchase purchase = new Purchase(1, true, "box of chocolates", BigDecimal.valueOf(10.00));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(10.50);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}

	@Test
	void calculateSalesTaxForNonExemptImportedProduct() throws FileNotFoundException, IOException {
		Purchase purchase = new Purchase(1, true, "bottle of perfume", BigDecimal.valueOf(47.50));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(54.65);
		System.out.println(actual_price);
		System.out.println(purchase.getAmountTaxedBySalesTax());
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	
	@Test
	void calculateSalesTaxForExemptImportedProduct() throws FileNotFoundException, IOException {
		Purchase purchase = new Purchase(1, true, "box of chocolates", BigDecimal.valueOf(10.00));
		BigDecimal actual_price = purchase.getPriceWithTaxes();
		BigDecimal expected_price = BigDecimal.valueOf(10.50);
		assertTrue(actual_price.compareTo(expected_price) == 0);
	}
	

}
