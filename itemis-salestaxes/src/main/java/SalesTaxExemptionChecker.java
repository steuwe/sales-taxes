
public class SalesTaxExemptionChecker {

	
	public static boolean checkExemption(String productName) {
		if (isFood(productName)) {
			return true;
		}
		if (isMedicalProduct(productName)) {
			return true;
		}
		if (isBook(productName)) {
			return true;
		}
		return false;
	}
	
	private static boolean isFood(String name) {
		if (name.toLowerCase().contains("chocolate")) {
			return true;
		}
		return false;
	}
	
	private static boolean isMedicalProduct(String name) {
		if (name.toLowerCase().contains("pills")) {
			return true;
		}
		return false;
	}
	
	private static boolean isBook(String name) {
		if (name.toLowerCase().contains("book")) {
			return true;
		}
		return false;
	}
	
}
