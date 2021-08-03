package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.JsonParser;
import parser.Parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {
    private static final String CART_NAME = "test-cart";
    private static final String REAL_ITEM_NAME = "KASHKAI";
    private static final double REAL_ITEM_PRICE = 1000.0;
    private static final double REAL_ITEM_WEIGHT = 1420;
    private static final String VIRTUAL_ITEM_NAME = "OpenSUSE";
    private static final double VIRTUAL_ITEM_PRICE = 0.1;
    private static final double VIRTUAL_ITEM_SIZE_ON_DISK = 40000;
    private static final double EXPECTED_TOTAL_PRICE = 1200.12;

    Cart testCart = null;

    @BeforeEach
    void setUp() {
        testCart = new Cart(CART_NAME);

        RealItem car = new RealItem();
        car.setName(REAL_ITEM_NAME);
        car.setPrice(REAL_ITEM_PRICE);
        car.setWeight(REAL_ITEM_WEIGHT);

        testCart.addRealItem(car);

        Parser parser = new JsonParser();
        parser.writeToFile(testCart);

    }

    @Test
    void deleteRealItem() {
        testCart = new Cart(CART_NAME);
        Cart testCartTwo = new Cart(CART_NAME);

        RealItem car = new RealItem();
        car.setName(REAL_ITEM_NAME);
        car.setPrice(REAL_ITEM_PRICE);
        car.setWeight(REAL_ITEM_WEIGHT);
        testCart.addRealItem(car);

        testCart.deleteRealItem(car);

        assertEquals(testCart, testCartTwo);

    }

    @Test
    void getTotalPrice() {
        Cart testCart = new Cart(CART_NAME);

        RealItem car = new RealItem();
        car.setPrice(REAL_ITEM_PRICE);

        VirtualItem disk = new VirtualItem();
        disk.setPrice(VIRTUAL_ITEM_PRICE);

        testCart.addRealItem(car);
        testCart.addVirtualItem(disk);
        double actual = testCart.getTotalPrice();

        assertEquals(EXPECTED_TOTAL_PRICE, actual);
    }

    @Test
    void getCartName() {
        String name = "test";
        Cart testOne = new Cart(name);
        Cart testTwo = new Cart(CART_NAME);

        assertAll(
                () -> assertEquals(testOne.getCartName(), name),
                () -> assertEquals(testTwo.getCartName(), CART_NAME)
        );
    }
}