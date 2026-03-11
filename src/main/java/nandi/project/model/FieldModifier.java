package nandi.project.model;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a typed field modifier with optional key/value attributes.
 */
public final class FieldModifier {

    public static final String ATTR_MIN = "min";
    public static final String ATTR_MAX = "max";

    /** Attribute key used for inverse relation mapping. */
    public static final String ATTR_MAPPED_BY = "mappedBy";

    private final ModifierKind kind;
    private final Map<String, String> attributes;

    private FieldModifier(ModifierKind kind, Map<String, String> attributes) {
        this.kind = Objects.requireNonNull(kind, "kind");
        this.attributes = Map.copyOf(attributes);
    }

    /** Creates an identifier modifier. */
    public static FieldModifier id() {
        return new FieldModifier(ModifierKind.ID, Map.of());
    }

    /** Creates a generated-value modifier. */
    public static FieldModifier generated() {
        return new FieldModifier(ModifierKind.GENERATED, Map.of());
    }

    /** Creates a required-field modifier. */
    public static FieldModifier required() {
        return new FieldModifier(ModifierKind.REQUIRED, Map.of());
    }

    /** Creates a unique-field modifier. */
    public static FieldModifier unique() {
        return new FieldModifier(ModifierKind.UNIQUE, Map.of());
    }

    /** Creates an optional-field modifier. */
    public static FieldModifier optional() {
        return new FieldModifier(ModifierKind.OPTIONAL, Map.of());
    }

    /** Creates a minimum-value modifier. */
    public static FieldModifier min(int min) {
        return new FieldModifier(ModifierKind.MIN, Map.of(ATTR_MIN, String.valueOf(min)));
    }

    /** Creates a maximum-value modifier. */
    public static FieldModifier max(int max) {
        return new FieldModifier(ModifierKind.MAX, Map.of(ATTR_MAX, String.valueOf(max)));
    }

    /** Creates a size modifier with both minimum and maximum bounds. */
    public static FieldModifier sizeMinMax(int min, int max) {
        return new FieldModifier(ModifierKind.SIZE, Map.of(
                ATTR_MIN, String.valueOf(min),
                ATTR_MAX, String.valueOf(max)
        ));
    }

    /** Creates a size modifier with a minimum bound. */
    public static FieldModifier sizeMin(int min) {
        return new FieldModifier(ModifierKind.SIZE, Map.of(ATTR_MIN, String.valueOf(min)));
    }

    /** Creates a size modifier with a maximum bound. */
    public static FieldModifier sizeMax(int max) {
        return new FieldModifier(ModifierKind.SIZE, Map.of(ATTR_MAX, String.valueOf(max)));
    }

    /** Creates an email-validation modifier. */
    public static FieldModifier email() {
        return new FieldModifier(ModifierKind.EMAIL, Map.of());
    }

    /** Creates a many-to-one relation modifier. */
    public static FieldModifier manyToOne() {
        return new FieldModifier(ModifierKind.MANY_TO_ONE, Map.of());
    }

    /** Creates a one-to-many relation modifier. */
    public static FieldModifier oneToMany(String mappedBy) {
        return new FieldModifier(ModifierKind.ONE_TO_MANY, Map.of(ATTR_MAPPED_BY, mappedBy));
    }

    /** Creates an owning one-to-one relation modifier. */
    public static FieldModifier oneToOneOwner() {
        return new FieldModifier(ModifierKind.ONE_TO_ONE_OWNER, Map.of());
    }

    /** Creates an inverse one-to-one relation modifier. */
    public static FieldModifier oneToOne(String mappedBy) {
        return new FieldModifier(ModifierKind.ONE_TO_ONE, Map.of(ATTR_MAPPED_BY, mappedBy));
    }

    /** Creates a many-to-many relation modifier. */
    public static FieldModifier manyToMany() {
        return new FieldModifier(ModifierKind.MANY_TO_MANY, Map.of());
    }

    /** Returns the modifier kind. */
    public ModifierKind getKind() {
        return kind;
    }

    /** Returns the modifier attributes. */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     * Returns the required attribute value for the given key.
     *
     * @throws IllegalStateException if the attribute is missing
     */
    public String requireAttribute(String key) {
        String value = attributes.get(key);
        if (value == null) {
            throw new IllegalStateException("Modifier " + kind + " is missing required attribute: " + key);
        }
        return value;
    }
}
