package test;


import src.Tile;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileTest {

    private Tile tile;

    @BeforeEach
    public void setUp() {
        tile = new Tile();
    }

    @Test
    public void testDefaultValue() {
        assertEquals(0, tile.getValue());
        assertFalse(tile.isCombination());
        assertEquals("tile-empty", tile.getStyleClass().get(0));
    }

    @Test
    public void testSetValue() {
        tile.setValue(2);
        assertEquals(2, tile.getValue());
    }

    @Test
    public void testIsCombination() {
        assertFalse(tile.isCombination());
        tile.wasCombinated();
        assertTrue(tile.isCombination());
        tile.resetIsCombination();
        assertFalse(tile.isCombination());
    }

    @Test
    public void testUpdateValueLabel() {
        tile.setValue(4);
        tile.updateValueLabel();
        assertEquals("tile-4", tile.getStyleClass().get(1));
        assertEquals("tile-label-black", tile.getStyleClass().get(2));
        assertEquals("tile-label-two", tile.getStyleClass().get(3));
        assertEquals("4", tile.getValueLabel().getText());
    }

    @Test
    public void testNewValue() {
        tile.newValue();
        int value = tile.getValue();
        assertTrue(value == 2 || value == 4);
        assertFalse(tile.getStyleClass().contains("tile-empty"));
        assertTrue(tile.getStyleClass().contains("tile-" + value));
        assertEquals(Integer.toString(value), tile.getValueLabel().getText());
    }

    @Test
    public void testSetAndGetTransition() {
        tile.setTransition(5);
        assertEquals(5, tile.getTransition());
    }
}
