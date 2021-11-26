This project was built using Maven, including junit jupiter dependencies (version 5.7.1).
The code was written with a JavaSE-15 JRE using jdk-17.0.1.

The assumption was that an input String, such as "1 book at 14.99", would be given by some external program,
which is why the input was hardcoded into the Demo class as opposed to taken from a Scanner object.
However, the code could easily be configured to take user input directly from the console instead.

I added the simple functionality of buying multiple items in one go, as it seemed useful.

If more products had been required, I would probably have written a separate class to match the product name to an existing product.
For this scale, however, the name check in the Product class should be small enough to be kept in the class.