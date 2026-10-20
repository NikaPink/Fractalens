package fr.univartois.butinfo.fractalens.complex;

import fr.univartois.butinfo.fractalens.image.GreyPalette;
import fr.univartois.butinfo.fractalens.image.GreyPalette;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreyPaletteTest {

    @Test
    void TestGreyPalette(){
        GreyPalette p = new GreyPalette();
        float n=0;
        assertEquals(new Color(n,n,n), p.getColor(50,50));
        n= (float)1/2;
        assertEquals(new Color(n,n,n), p.getColor(25,50));
        n=(float)0.4;
        assertEquals(new Color(n,n,n), p.getColor(6,10));
    }
}
