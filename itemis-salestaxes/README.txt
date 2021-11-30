This project was built using Maven, including junit jupiter dependencies (version 5.7.1).
The code was written with a JavaSE-15 JRE using jdk-17.0.1.

The assumption was that an input String, such as "1 book at 14.99", would be given by some external program,
which is why the input was hardcoded into the Demo class as opposed to taken from a Scanner object.
However, the code could easily be configured to take user input directly from the console instead.

I added the simple functionality of buying multiple items in one go, as it seemed useful.

I created a small product database file to allow only known products to be purchased and to check the product category.
A relational database would make sense for the products, but for simplicity's sake, I chose a CSV file.

The error handling by the ShoppingBasket class would depend on the context of the application.
One option could be to simply shut down the program if the database was unavailable, for example.