import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import logic.PurchaseParser;

public class PurchaseParserTest {

	private PurchaseParser parser;
	
	@BeforeEach
	void createParser() {
		parser = new PurchaseParser();
	}
	
	@ParameterizedTest
	@ValueSource(strings = { 
			"1 book at 12.49", 
			"1 imported box of chocolates at 10.00",
			"1  imported  box  of  chocolates  at  10.00", 
			"1 music cd at 12", 
			"2 chocolate bar at 15.99",
			"1 packet of headache pills at 9.75" })
	void parseValidPurchaseShouldNotThrow(String input) throws FileNotFoundException, IOException {
		parser.parsePurchase(input);
	}

	@ParameterizedTest
	@ValueSource(strings = { 
			"1  imported  box  of  chocolates  at  10.00", 
			"1 music cd at 12",
			"2 Chocolate bar at 15.99", 
			"2 Chocolate bar at 05.09" })
	void parsePurchaseFringeCasesShouldNotThrow(String input) throws FileNotFoundException, IOException {
		parser.parsePurchase(input);
	}

	
	@ParameterizedTest
	@ValueSource(strings = {
			"", " "
	})
	void parseBlankPurchaseInput(String input) {
		assertThrows(IllegalArgumentException.class, () -> parser.parsePurchase(input));
	}
	
	@Test
	void parseNullPurchaseInput() {
		assertThrows(IllegalArgumentException.class, () -> parser.parsePurchase(null));
	}
	
	@Test
	void parseMissingAt() {
		assertThrows(IllegalArgumentException.class, () -> parser.parsePurchase("1 box 12.00"));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { 
			"0 imported box of chocolates at 10.00", 
			"-1 music cd at 12.00",
			"1.1 music cd at 12.00"})
	void parseInvalidPurchaseQuantityShouldThrowIllegalArgumentException(String input) {
		assertThrows(IllegalArgumentException.class, () -> parser.parsePurchase(input));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { 
			"1 imported box of chocolates at -10.00", 
			"1 music cd at -12", 
			"1 music cd at 12.1.1",
			"1 chocolate bars at ",
			"1 chocolate bars at  "})
	void parseInvalidPurchasePriceShouldThrowIllegalArgumentException(String input) {
		assertThrows(IllegalArgumentException.class, () -> parser.parsePurchase(input));
	}

	@ParameterizedTest
	@ValueSource(strings = { 
			"1  imported at 10.00", 
			"1 at 12.00", })
	void parseInvalidPurchaseProductShouldThrowIllegalArgumentException(String input) {
		assertThrows(IllegalArgumentException.class, () -> parser.parsePurchase(input));
	}
	
}
