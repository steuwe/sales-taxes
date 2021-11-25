import java.math.BigDecimal;
import java.math.RoundingMode;

public class Purchase {

	private final BigDecimal SALES_TAX_PERCENTAGE = BigDecimal.valueOf(10);
	private final BigDecimal IMPORT_TAX_PERCENTAGE = BigDecimal.valueOf(5);
	private int quantity;
	private boolean isImported;
	private Product product;


	public Purchase(String input) {
		parseInputString(input);
	}

	private void parseInputString(String input) {
		try {
			validateInput(input);
			String parsedInput = input.trim();
			System.out.println(parsedInput);
			isImported = parseImported(parsedInput);
			parsedInput = removeImportedFromInput(parsedInput);
			System.out.println(parsedInput);
			quantity = parseQuantity(parsedInput);
			parsedInput = removeQuantityFromInput(parsedInput);
			System.out.println(parsedInput);
			product = parseProduct(parsedInput);
			System.out.println("product: " + product.getName() + " " + product.getPrice());
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
			return true;
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
			int parsed_quantity = Integer.parseInt(first_token);
			if (parsed_quantity < 1) {
				throw new IllegalArgumentException("Product quantity is less than 1!");
			}
			return parsed_quantity;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Product quantity is missing or not formatted correctly: " + e.getMessage());
		}
	}
	
	private String removeQuantityFromInput(String input) {
		String quantityAsString = Integer.toString(quantity);
		return input.replaceFirst(quantityAsString, "").trim();
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
		BigDecimal salesTax = BigDecimal.valueOf(0);
		BigDecimal importTax = BigDecimal.valueOf(0);
		if (!product.isExempt()) {
			salesTax = calculateSalesTax(price);
			salesTax = roundTaxedValue(salesTax);
		}
		if (isImported) {
			importTax = calculateImportTax(price);
		}
		price = applyTax(price, salesTax);
		price = applyTax(price, importTax);
		
		return roundFinalPrice(price);
	}
	
	private BigDecimal calculateSalesTax(BigDecimal price) {
		return price.multiply(SALES_TAX_PERCENTAGE).divide(BigDecimal.valueOf(100));
	}
	
	private BigDecimal calculateImportTax(BigDecimal price) {
		return price.multiply(IMPORT_TAX_PERCENTAGE).divide(BigDecimal.valueOf(100));
	}
	
	private BigDecimal applyTax(BigDecimal price, BigDecimal tax) {
		return price.add(tax);
	}

	private BigDecimal roundTaxedValue(BigDecimal value) {
		value = value.multiply(BigDecimal.valueOf(20));
		value = value.setScale(2, RoundingMode.HALF_UP);
		value = value.divide(BigDecimal.valueOf(20));
		return value;
	}
	
	private BigDecimal roundFinalPrice(BigDecimal price) {
		return price.setScale(2, RoundingMode.HALF_UP);
	}
}
