package database;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDatabaseReader {

	private final static String filePath = "src/main/resources/products.csv";
	private static final List<String> exemptCategories = new ArrayList<String>(Arrays.asList("food", "medical", "book"));

	public static boolean checkProductForExemption(String name) throws FileNotFoundException, IOException {
		String row;
		List<Integer> exemptIndices = new ArrayList<Integer>();
		try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath));) {

			if ((row = csvReader.readLine()) != null) {
				String[] categories = row.split(",");
				for (int i = 0; i < categories.length; i++) {
					if (ProductDatabaseReader.exemptCategories.contains(categories[i])) {
						exemptIndices.add(i);
					}
				}
			}
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (data[0].equals(name)) {
					for (int index : exemptIndices) {
						if (data[index].toLowerCase().equals("true")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean checkProductName(String name) throws FileNotFoundException, IOException {
		String row;
		try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath));) {
			if ((row = csvReader.readLine()) != null) { // skip first line
				while ((row = csvReader.readLine()) != null) {
					String[] data = row.split(",");
					if (data[0].toLowerCase().equals(name.toLowerCase())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static List<String> getExemptCategories() {
		return exemptCategories;
	}
	
}
