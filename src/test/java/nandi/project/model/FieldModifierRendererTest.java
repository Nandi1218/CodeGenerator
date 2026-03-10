package nandi.project.model;

import nandi.project.render.FieldModifierRenderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldModifierRendererTest {

    @Test
    void testRenderSizeMinMax() {
        String rendered = FieldModifierRenderer.render(FieldModifier.sizeMinMax(2, 10));
        assertEquals("@Size(min = 2, max = 10)", rendered);
    }

    @Test
    void testRenderOneToOneInverse() {
        String rendered = FieldModifierRenderer.render(FieldModifier.oneToOne("profile"));
        assertEquals("@OneToOne(mappedBy = \"profile\", cascade = CascadeType.ALL)", rendered);
    }

    @Test
    void testRenderManyToMany() {
        String rendered = FieldModifierRenderer.render(FieldModifier.manyToMany());
        assertEquals("@ManyToMany", rendered);
    }
}

