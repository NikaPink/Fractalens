package fr.univartois.butinfo.fractalens.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.univartois.butinfo.fractalens.image.IFractalImage;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The FractalGeneratorCompositeTest tests the Composite pattern implementation
 * for delegating fractal generation tasks.
 *
 * @author TonNom
 *
 * @version 0.1.0
 */
class FractalGeneratorCompositeTest {

    private FractalGeneratorComposite composite;
    private FractalGeneratorSpy child1;
    private FractalGeneratorSpy child2;

    @BeforeEach
    void setUp() {
        composite = new FractalGeneratorComposite(100,"test_animation.gif");
        child1 = new FractalGeneratorSpy();
        child2 = new FractalGeneratorSpy();
    }

    @Test
    void testAddGenerator() {
        composite.addGenerator(child1);
        composite.addGenerator(child2);

        assertEquals(2, composite.getGenerators().size());
    }

    @Test
    void testGenerateImageDelegatesToAllChildren() {
        composite.addGenerator(child1);
        composite.addGenerator(child2);

        composite.generateImage();

        assertTrue(child1.isGenerateImageCalled());
        assertTrue(child2.isGenerateImageCalled());
    }


    /**
     * A spy class to track method calls on IFractalGenerator without executing real logic.
     */
    private static class FractalGeneratorSpy implements IFractalGenerator {

        private boolean generateImageCalled = false;
        private boolean saveImageCalled = false;
        private boolean computePixelColorCalled = false;

        @Override
        public IFractalImage generateImage() {
            this.generateImageCalled = true;
            return null;
        }

        @Override
        public Color computePixelColor(int row, int column) {
            this.computePixelColorCalled = true;
            return Color.BLACK;
        }

        @Override
        public void saveImage() {
            this.saveImageCalled = true;
        }

        public boolean isGenerateImageCalled() {
            return generateImageCalled;
        }

        public boolean isSaveImageCalled() {
            return saveImageCalled;
        }

        public boolean isComputePixelColorCalled() {
            return computePixelColorCalled;
        }
    }
}
