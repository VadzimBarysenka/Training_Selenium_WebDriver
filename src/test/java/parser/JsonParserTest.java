package parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Cart;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class JsonParserTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() throws IOException {
        //   Path path = FileSystems.getDefault().getPath("src/main/resources/test-cart.json");
        //       Files.delete(path);
    }

    @Test
    void writeToFileFileCreatingFile() {
        Cart testCart = new Cart("test-cart");

        Parser parser = new JsonParser();
        parser.writeToFile(testCart);

        Assertions.assertTrue(new File("src/main/resources/test-cart.json").exists());
    }

    @Test
    void readFromFile() {

        Parser parser = new JsonParser();
        final Cart expectedCart = parser.readFromFile(new File("src/main/resources/test.json"));

        assertAll("cart",
                () -> assertEquals("test-cart", expectedCart.getCartName()),
                () -> assertEquals(60002.28, expectedCart.getTotalPrice()));
    }
}