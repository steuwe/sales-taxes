import java.math.BigDecimal;
import java.math.MathContext;

public class Purchase {

	private final BigDecimal SALES_TAX_PERCENTAGE = BigDecimal.valueOf(1);
	private final BigDecimal IMPORT_TAX_PERCENTAGE = BigDecimal.valueOf(5);
	private int quantity;
	private boolean isExempt;
	private boolean isImported;
	private Product product;

	public Purchase(String input) {
		parseInputString(input);
	}

	private void parseInputString(String input) {
		try {
			validateInput(input);
			String parsedInput = input.trim();
			isImported = parseImported(parsedInput);
			parsedInput = removeImportedFromInput(parsedInput);
			quantity = parseQuantity(parsedInput);
			parsedInput = removeQuantityFromInput(parsedInput);
			product = parseProduct(parsedInput);
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
		if (!input.contains("at")) {
			throw new IllegalArgumentException("Input must indicate price with 'at'");
		}
	}
	
	private boolean parseImported(String input) {
		if (input.contains("imported")) {
			isImported = true;
		}
		return false;
	}
	
	private String removeImportedFromInput(String input) {
		if (isImported) {
			return input.replace("imported ", "");
		}
		return input;
	}
	
	private int parseQuantity(String input) {
		String first_token = input.split(" ")[0];
		try { 
			int quantity = Integer.parseInt(first_token);
			if (quantity < 1) {
				throw new IllegalArgumentException("Product quantity is less than 1!");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Product quantity is missing or not formatted correctly: " + e.getMessage());
		}
		return quantity;
	}
	
	private String removeQuantityFromInput(String input) {
		String quantityAsString = Integer.toString(quantity);
		return input.replace(quantityAsString, "").trim();
	}

	private Product parseProduct(String input) {
		String[] splitInput = input.split(" at ");
		if (splitInput.length < 2) {
			throw new IllegalArgumentException("Product name or price is missing!");
		}
		String productName = splitInput[0].trim();
		try {
			BigDecimal productPrice = new BigDecimal(splitInput[1].trim());
			return new Product(productName, productPrice);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Product price is not formatted correctly: " + e.getMessage());
		}
	}
	
	public BigDecimal getPriceWithoutTaxes() {
		return product.getPrice();
	}

	public BigDecimal getPriceWithTaxes() {
		BigDecimal price = product.getPrice();
		if (!isExempt) {
			price = applySalesTax(price);
			price = roundTaxedPrice(price);
		}
		price = applyImportTax(price);
		price = roundTaxedPrice(price);
		return price;
	}
	
	private BigDecimal applySalesTax(BigDecimal price) {
		return price.multiply(SALES_TAX_PERCENTAGE).divide(BigDecimal.valueOf(100));
	}
	
	private BigDecimal applyImportTax(BigDecimal price) {
		return price.multiply(IMPORT_TAX_PERCENTAGE).divide(BigDecimal.valueOf(100));
	}
	
	private BigDecimal roundTaxedPrice(BigDecimal price) {
		MathContext context = new MathContext(2);
		price = price.multiply(BigDecimal.valueOf(20));
		price = price.round(context);
		price = price.divide(BigDecimal.valueOf(20));
		return price;
	}
}
