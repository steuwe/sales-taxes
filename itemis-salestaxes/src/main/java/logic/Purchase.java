package logic;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Purchase {

	private final BigDecimal SALES_TAX_PERCENTAGE = BigDecimal.valueOf(10);
	private final BigDecimal IMPORT_TAX_PERCENTAGE = BigDecimal.valueOf(5);
	private int quantity;
	private boolean isImported;
	private Product product;

	public Purchase(int quantity, boolean isImported, String productName, BigDecimal productPrice) throws FileNotFoundException, IOException {
		this.quantity = quantity;
		this.isImported = isImported;
		this.product = new Product(productName, productPrice);
	}
	
	public BigDecimal getPriceWithoutTaxes() {
		return product.getPrice().multiply(BigDecimal.valueOf(quantity));
	}

	public BigDecimal getPriceWithTaxes() {
		BigDecimal price = product.getPrice();
		BigDecimal tax = BigDecimal.valueOf(0);
		if (!product.isExempt()) {
			tax = tax.add(calculateSalesTax(price));
		}
		if (isImported) {
			tax = tax.add(calculateImportTax(price));
		}
		price = price.add(roundTaxedValue(tax));
		return roundTwoDecimalPlaces(price).multiply(BigDecimal.valueOf(quantity));
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
		value = value.setScale(2, RoundingMode.HALF_UP);
		value = value.multiply(BigDecimal.valueOf(20));
		value = value.setScale(0, RoundingMode.CEILING);
		value = value.divide(BigDecimal.valueOf(20));
		return value;
	}
	
	private BigDecimal roundTwoDecimalPlaces(BigDecimal price) {
		return price.setScale(2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getAmountTaxedBySalesTax() {
		BigDecimal price = product.getPrice();
		BigDecimal salesTax = BigDecimal.valueOf(0);
		BigDecimal importTax = BigDecimal.valueOf(0);
		if (!product.isExempt()) {
			salesTax = calculateSalesTax(price);
		}
		if (isImported) {
			importTax = calculateImportTax(price);
		}
		BigDecimal newPrice = applyTax(price, salesTax.add(importTax));
		return roundTaxedValue(newPrice.subtract(price));
	}
	
	public String getProductName() {
		return product.getName();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public boolean isImported() {
		return isImported;
	}
}
