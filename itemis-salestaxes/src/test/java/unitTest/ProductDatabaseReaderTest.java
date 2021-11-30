package unitTest;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import database.ProductDatabaseReader;

public class ProductDatabaseReaderTest {

	@ParameterizedTest
	@ValueSource(strings = {"box of chocolates", "chocolate bar", "book", "packet of headache pills",
			"BOX OF CHOCOLATES", "Box Of Chocolates"})
	void testProductName(String input) throws FileNotFoundException, IOException {
		assertTrue(ProductDatabaseReader.checkProductName(input));
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {"box of chocolate", "books", "bottle of headache pills"})
	void testInvalidProductName(String input) throws FileNotFoundException, IOException {
		assertFalse(ProductDatabaseReader.checkProductName(input));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"box of chocolates", "book", "packet of headache pills"})
	void testProductWithSalesTaxExemption(String input) throws FileNotFoundException, IOException {
		assertTrue(ProductDatabaseReader.checkProductName(input));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"bottle of perfume", "music CD"})
	void testProductWithoutSalesTaxExemption(String input) throws FileNotFoundException, IOException {
		assertTrue(ProductDatabaseReader.checkProductName(input));
	}
	
}
