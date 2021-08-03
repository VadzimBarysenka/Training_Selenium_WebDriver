package parser;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JsonParserTest {
    private static final String CART_NAME = "test-cart";
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

        VirtualItem os = new VirtualItem();
        os.setName(VIRTUAL_ITEM_NAME);
        os.setPrice(VIRTUAL_ITEM_PRICE);
        os.setSizeOnDisk(VIRTUAL_ITEM_SIZE_ON_DISK);

        testCart.addRealItem(car);
        testCart.addVirtualItem(os);
    }

    @AfterAll
    public static void tearDown() {
        Path path = FileSystems.getDefault().getPath("src/main/resources/" + CART_NAME + ".json");
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void writeToFile() throws IOException {
        Parser parser = new JsonParser();

        parser.writeToFile(testCart);

        // Cart exp = parser.readFromFile(new File("src/main/resources/" + CART_NAME + ".json"));
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/" + CART_NAME + ".json"));
        Cart exp = gson.fromJson(reader, Cart.class);

        assertEquals(testCart, exp);
    }

    @Test
    void readFromFile() {
        Parser parser = new JsonParser();
        Cart expectedCart = parser.readFromFile(new File("src/main/resources/" + CART_NAME + ".json"));

        assertEquals(testCart, expectedCart);
    }


    @ParameterizedTest
    @MethodSource
    void readFromFileException(File file) {

        Parser parser = new JsonParser();

        Exception exception = Assertions.assertThrows(NoSuchFileException.class,
                () -> parser.readFromFile(new File(String.valueOf(file))));
        assertEquals(String.format("File %s.json not found!", file), exception.getMessage());

    }

    static Stream<File> readFromFileException() {
        return Stream.of(new File("src/main/resources/user-cart.txt"),
                new File("src/main/resources/user-cart.xml"),
                new File("src/main/resources/"),
                new File("src/main/resources/user-cart.log"),
                new File("src/main/resources/.json"));
    }

}
