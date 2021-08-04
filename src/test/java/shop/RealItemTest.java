package shop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RealItemTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testToString() {
        RealItem car = new RealItem();
        car.setName("REAL_ITEM_NAME");
        car.setPrice(3);
        car.setWeight(5);

        System.out.print(car);
        assertEquals("Class: class shop.RealItem; Name: " + car.getName() + "; " + "Price: " + car.getPrice() + "; " + "Weight: " + car.getWeight(), outContent.toString());
    }

}