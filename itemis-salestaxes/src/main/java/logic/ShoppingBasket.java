package logic;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class ShoppingBasket {

	private List<Purchase> purchases;
	private PurchaseParser parser;

	public ShoppingBasket() {
		purchases = new LinkedList<Purchase>();
		parser = new PurchaseParser();
	}
	
	public void addPurchase(String item) {
		try {
			Purchase purchase = parser.parsePurchase(item);
			purchases.add(purchase);
		} catch (IllegalArgumentException e) {
			System.out.println("Could not add purchase: " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("Database not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something went wrong while reading the database:");
			e.printStackTrace();
		}
	}
	
	public String getReceipt() {
		DecimalFormat df = new DecimalFormat("0.00");
		StringBuilder receipt = new StringBuilder("");
		BigDecimal totalSalesTax = BigDecimal.valueOf(0);
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		for (Purchase purchase : purchases) {
			totalSalesTax = totalSalesTax.add(purchase.getAmountTaxedBySalesTax());
			StringBuffer purchaseDetails = new StringBuffer("");
			purchaseDetails.append(purchase.getQuantity() + " ");
			if (purchase.isImported()) {
				purchaseDetails.append("imported ");
			}
			purchaseDetails.append(purchase.getProductName());
			purchaseDetails.append(": ");
			purchaseDetails.append(df.format(purchase.getPriceWithTaxes()));
			totalPrice = totalPrice.add(purchase.getPriceWithTaxes());
			purchaseDetails.append("\n");
			receipt.append(purchaseDetails);
		}
		receipt.append("Sales Taxes: " + df.format(totalSalesTax));
		receipt.append("\n");
		receipt.append("Total: " + df.format(totalPrice));
		return receipt.toString();
	}
	
	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		for (Purchase purchase : purchases) {
			totalPrice = totalPrice.add(purchase.getPriceWithTaxes());
		}
		return totalPrice;
	}
	
	public BigDecimal getTotalSalesTax() {
		BigDecimal totalTax = BigDecimal.valueOf(0);
		for (Purchase purchase : purchases) {
			totalTax = totalTax.add(purchase.getAmountTaxedBySalesTax());
		}
		return totalTax;
	}
	
	public List<Purchase> getPurchases() {
		return purchases;
	}
	
}
