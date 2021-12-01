package integrationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import logic.ShoppingBasket;

public class ShoppingBasketTest {
	
	private ShoppingBasket basket;
	
	@BeforeEach
	void createBasket() {
		basket = new ShoppingBasket();
	}
	
	@ParameterizedTest
	@ValueSource(strings = {
			"1 at 12.49",
			"box of chocolates at 12.49",
			"box of chocolates",
			"1 book at .x"
	})
	void addInvalidPurchase() {
		int sizeBefore = basket.getPurchases().size();
		basket.addPurchase("1 at 12.49");
		int sizeAfter = basket.getPurchases().size();
		assertEquals(sizeBefore, sizeAfter);
	}
	
	@Test
	void testGetTotalPrice() {
		basket.addPurchase("1 book at 12.49");
		basket.addPurchase("1 music CD at 14.99");
		basket.addPurchase("1 chocolate bar at 0.85");
		BigDecimal totalPrice = basket.getTotalPrice();
		BigDecimal expectedPrice = BigDecimal.valueOf(29.83);
		assertTrue(totalPrice.compareTo(expectedPrice) == 0);
		}
	
	@Test
	void testGetTotalTax() {
		basket.addPurchase("1 book at 12.49");
		basket.addPurchase("1 music CD at 14.99");
		basket.addPurchase("1 chocolate bar at 0.85");
		BigDecimal totalTax= basket.getTotalSalesTax();
		BigDecimal expectedTax = BigDecimal.valueOf(1.5);
		assertTrue(totalTax.compareTo(expectedTax) == 0);
		}
	
	@Test
	void getReceiptDetails() {
		basket.addPurchase("1 book at 12.49");
		basket.addPurchase("1 music CD at 14.99");
		basket.addPurchase("1 chocolate bar at 0.85");
		String receipt = basket.getReceipt();
		String expected = 
				"""
				1 book: 12.49
				1 music CD: 16.49
				1 chocolate bar: 0.85
				Sales Taxes: 1.50
				Total: 29.83""";
		assertEquals(expected, receipt);
	}
	
	@Test
	void getReceiptDetails2() {
		basket.addPurchase("1 imported box of chocolates at 10.00");
		basket.addPurchase("1 imported bottle of perfume at 47.50");
		String receipt = basket.getReceipt();
		String expected = 
				"""
				1 imported box of chocolates: 10.50
				1 imported bottle of perfume: 54.65
				Sales Taxes: 7.65
				Total: 65.15""";
		assertEquals(expected, receipt);
	}
	
	@Test
	void getReceiptDetails3() {
		basket.addPurchase("1 imported bottle of perfume at 27.99");
		basket.addPurchase("1 bottle of perfume at 18.99");
		basket.addPurchase("1 packet of headache pills at 9.75");
		basket.addPurchase("1 box of imported chocolates at 11.25");
		String receipt = basket.getReceipt();
		String expected = 
				"""
				1 imported bottle of perfume: 32.19
				1 bottle of perfume: 20.89
				1 packet of headache pills: 9.75
				1 imported box of chocolates: 11.85
				Sales Taxes: 6.70
				Total: 74.68""";
		assertEquals(expected, receipt);
	}
	
}
