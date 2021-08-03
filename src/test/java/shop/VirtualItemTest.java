package shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VirtualItemTest {
    private static final double SIZE = 20000.01;

    @Test
    void setSizeOnDisk() {
        VirtualItem disk = new VirtualItem();
        disk.setSizeOnDisk(SIZE);

        assertEquals(disk.getSizeOnDisk(), SIZE);
    }
}