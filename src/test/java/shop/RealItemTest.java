package shop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RealItemTest {
    private static final double WEIGHT = 1560;

    @Test
    void setWeight() {
        RealItem car = new RealItem();
        car.setWeight(WEIGHT);

        assertEquals(car.getWeight(), WEIGHT);
    }
}