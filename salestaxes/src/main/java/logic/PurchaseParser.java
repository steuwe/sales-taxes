package logic;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public class PurchaseParser {
	
	public Purchase parsePurchase(String input) throws FileNotFoundException, IOException {
		try {
			validateInput(input);
			String parsedInput = input.trim();
			boolean isImported = parseImported(parsedInput);
			parsedInput = removeImportedFromInput(parsedInput);
			int quantity = parseQuantity(parsedInput);
			parsedInput = removeQuantityFromInput(parsedInput, quantity);
			String productName = parseProductName(parsedInput);
			BigDecimal productPrice = parseProductPrice(parsedInput);
			return new Purchase(quantity, isImported, productName, productPrice);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Failed to create purchase: " + e.getMessage());
		}
	}
	
	private void validateInput(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Input is null!");
		}
		if (input.isEmpty() || input.trim().isEmpty()) {
			throw new IllegalArgumentException("Input is empty or blank!");
		}
		if (!input.contains(" at ")) {
			throw new IllegalArgumentException("Input must indicate price with 'at'");
		}
	}
	
	private boolean parseImported(String input) {
		if (input.contains("imported")) {
			return true;
		}
		return false;
	}
	
	private String removeImportedFromInput(String input) {
		if (input.contains("imported ")) {
			return input.replace("imported ", "");
		}
		return input;
	}
	
	private int parseQuantity(String input) {
		String first_token = input.split(" ")[0];
		try { 
			int parsed_quantity = Integer.parseInt(first_token);
			if (parsed_quantity < 1) {
				throw new IllegalArgumentException("Product quantity is less than 1!");
			}
			return parsed_quantity;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Product quantity is missing or not formatted correctly: " + e.getMessage());
		}
	}
	
	private String removeQuantityFromInput(String input, int quantity) {
		String quantityAsString = Integer.toString(quantity);
		return input.replaceFirst(quantityAsString, "").trim();
	}

	private String parseProductName(String input) {
		String[] productInfo = input.split(" at ");
		if (productInfo.length < 2) {
			throw new IllegalArgumentException("Product name or price is missing!");
		}
		String name = productInfo[0];
		name = name.trim().replaceAll("\\s{2,}", " ");
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Product name is blank!");
		}
		return name.trim();
	}
	
	private BigDecimal parseProductPrice(String input) {
		String[] productInfo = input.split(" at ");
		String priceAsString = productInfo[1];
		if (priceAsString.trim().isEmpty()) {
			throw new IllegalArgumentException("Product price is blank!");
		}
		try {
			BigDecimal productPrice = new BigDecimal(priceAsString.trim());
			return productPrice;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Product price is not formatted correctly: " + e.getMessage());
		}
	}
}
