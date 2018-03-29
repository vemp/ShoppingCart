package testData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cart.CartItem;
import cart.ShoppingCart;
import main.PrintReceiptMain;
import products.Book;
import products.TaxableProduct;

/**
 *  The TestData class prepares input data for the main function in {@link PrintReceiptMain}
 *  as provided by the exercise text. The same data is also used in provided unit tests.
 * @author Francesco Venturini - 29.03.2018
 *
 */
public class TestData {

	private List<TaxableProduct> products = new ArrayList<TaxableProduct>();
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();

	private TaxableProduct nullProduct = new TaxableProduct(BigDecimal.ZERO, "null product", true, false);
	private	TaxableProduct expensiveProduct = new TaxableProduct(new BigDecimal(100.00), "expensive thing", false, true);

	private CartItem nullItem = new CartItem();
	private ShoppingCart nullCart = new ShoppingCart();
	
	public TestData() {
		
		// data on products
		products.add(new Book(new BigDecimal(12.49), "Guerra e Pace", "Lev Tolstoj", 1500, "8817117382"));
		products.add(new TaxableProduct(new BigDecimal(14.99), "music CD", false, false));
		products.add(new TaxableProduct(new BigDecimal(0.85), "chocolate bar", true, false));
		products.add(new TaxableProduct(new BigDecimal(10.00), "box of chocolates", true, true));
		products.add(new TaxableProduct(new BigDecimal(47.50), "bottle of perfume", false, true));
		products.add(new TaxableProduct(new BigDecimal(27.99), "bottle of perfume", false, true));
		products.add(new TaxableProduct(new BigDecimal(18.99), "bottle of perfume", false, false));
		products.add(new TaxableProduct(new BigDecimal(9.75), "headache pills", true, false));
		products.add(new TaxableProduct(new BigDecimal(11.25), "box of chocolates", true, true));
		
		expensiveProduct.getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).setRate(new BigDecimal(22));
		expensiveProduct.getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).setRate(new BigDecimal(29));

		// data on cartItems
		for (TaxableProduct p: products) {
			cartItems.add(new CartItem(p));
		}
		
		// data on shopping carts
		ShoppingCart s1 = new ShoppingCart();
		s1.getItems().add(cartItems.get(0));
		s1.getItems().add(cartItems.get(1));
		s1.getItems().add(cartItems.get(2));

		ShoppingCart s2 = new ShoppingCart();
		s2.getItems().add(cartItems.get(3));
		s2.getItems().add(cartItems.get(4));
		
		ShoppingCart s3 = new ShoppingCart();
		s3.getItems().add(cartItems.get(5));
		s3.getItems().add(cartItems.get(6));
		s3.getItems().add(cartItems.get(7));
		s3.getItems().add(cartItems.get(8));

		shoppingCarts.add(s1);
		shoppingCarts.add(s2);
		shoppingCarts.add(s3);

	}

	
	// getter functions
	public List<TaxableProduct> getProducts() {
		return products;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public TaxableProduct getNullProduct() {
		return nullProduct;
	}

	public TaxableProduct getExpensiveProduct() {
		return expensiveProduct;
	}
	
	public CartItem getNullItem() {
		return nullItem;
	}

	public ShoppingCart getNullCart() {
		return nullCart;
	}

}
