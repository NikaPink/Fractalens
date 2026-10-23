package fr.univartois.butinfo.fractalens.complex;

import fr.univartois.butinfo.fractalens.image.GreyPalette;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreyPaletteTest {

    @Test
    void TestGreyPalette(){
        GreyPalette p = new GreyPalette(50);
        float n = 0f;
        assertEquals(new Color(n, n, n), p.getColor(50));
        n = 0.5f;
        assertEquals(new Color(n, n, n), p.getColor(25));
        n = 0.4f;
        assertEquals(new Color(n, n, n), p.getColor(30));
    }
}
