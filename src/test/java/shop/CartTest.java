package shop;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {
    private static final String CART_NAME = RandomStringUtils.randomAlphabetic(10);
    private static final String REAL_ITEM_NAME = "KASHKAI";
    private static final double REAL_ITEM_PRICE = 1000.0;
    private static final double REAL_ITEM_WEIGHT = 1420;
    private static final String VIRTUAL_ITEM_NAME = "OpenSUSE";
    private static final double VIRTUAL_ITEM_PRICE = 0.1;
    private static final double VIRTUAL_ITEM_SIZE_ON_DISK = 40000;
    Cart testCart = null;

    @BeforeEach
    void setUp() {
        testCart = new Cart(CART_NAME);

        RealItem car = new RealItem();
        car.setName(REAL_ITEM_NAME);
        car.setPrice(REAL_ITEM_PRICE);
        car.setWeight(REAL_ITEM_WEIGHT);

        testCart.addRealItem(car);
    }

    @Test
    void deleteRealItem() {
        RealItem car = new RealItem();

        testCart.deleteRealItem(car);

        assertEquals(testCart.getTotalPrice(), 0.0);
    }

    @Test
    void getTotalPrice() {
        Cart testCartTwo = new Cart(CART_NAME);
        RealItem car = new RealItem();
        car.setPrice(REAL_ITEM_PRICE);

        VirtualItem disk = new VirtualItem();
        disk.setPrice(VIRTUAL_ITEM_PRICE);

        testCartTwo.addRealItem(car);

        assertEquals(testCart.getTotalPrice(), testCartTwo.getTotalPrice());
    }
}