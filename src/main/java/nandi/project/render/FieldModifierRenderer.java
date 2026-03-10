package nandi.project.render;

import nandi.project.model.FieldModifier;
import nandi.project.model.ModifierKind;

/**
 * Renders typed field modifiers into generated annotation strings.
 */
public final class FieldModifierRenderer {
    private FieldModifierRenderer() {
    }

    public static String render(FieldModifier modifier) {
        return switch (modifier.getKind()) {
            case ID -> "@Id";
            case GENERATED -> "@GeneratedValue(strategy = GenerationType.IDENTITY)";
            case REQUIRED -> "@Column(nullable = false)";
            case UNIQUE -> "@Column(unique = true)";
            case OPTIONAL -> "@Column(nullable = true)";
            case MIN -> "@Min(" + modifier.requireAttribute(FieldModifier.ATTR_MIN) + ")";
            case MAX -> "@Max(" + modifier.requireAttribute(FieldModifier.ATTR_MAX) + ")";
            case SIZE -> renderSize(modifier);
            case EMAIL -> "@Email";
            case MANY_TO_ONE -> "@ManyToOne";
            case ONE_TO_MANY -> "@OneToMany(mappedBy = \"" + modifier.requireAttribute(FieldModifier.ATTR_MAPPED_BY) + "\", cascade = CascadeType.ALL)";
            case ONE_TO_ONE_OWNER -> "@OneToOne";
            case ONE_TO_ONE -> "@OneToOne(mappedBy = \"" + modifier.requireAttribute(FieldModifier.ATTR_MAPPED_BY) + "\", cascade = CascadeType.ALL)";
            case MANY_TO_MANY -> "@ManyToMany";
        };
    }

    private static String renderSize(FieldModifier modifier) {
        String min = modifier.getAttributes().get(FieldModifier.ATTR_MIN);
        String max = modifier.getAttributes().get(FieldModifier.ATTR_MAX);

        if (min != null && max != null) {
            return "@Size(min = " + min + ", max = " + max + ")";
        }
        if (min != null) {
            return "@Size(min = " + min + ")";
        }
        return "@Size(max = " + max + ")";
    }

    public static boolean isValidationModifier(FieldModifier modifier) {
        ModifierKind kind = modifier.getKind();
        return kind.isValidationConstraint();
    }
}

