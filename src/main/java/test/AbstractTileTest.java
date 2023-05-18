package test;

import org.junit.Test;
import src.AbstractTile;
import static org.junit.Assert.*;

public class AbstractTileTest {
    AbstractTile at = new AbstractTile(){};

    @Test
    public void testHasRectangle() throws Exception {

        assertNotNull(at.square);

    }

}
