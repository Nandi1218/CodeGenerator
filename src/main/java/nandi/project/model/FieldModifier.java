package nandi.project.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Typed field modifier with optional key/value attributes.
 */
public final class FieldModifier {
    public static final String ATTR_MIN = "min";
    public static final String ATTR_MAX = "max";
    public static final String ATTR_MAPPED_BY = "mappedBy";

    private final ModifierKind kind;
    private final Map<String, String> attributes;

    private FieldModifier(ModifierKind kind, Map<String, String> attributes) {
        this.kind = Objects.requireNonNull(kind, "kind");
        this.attributes = Collections.unmodifiableMap(new HashMap<>(attributes));
    }

    public static FieldModifier id() {
        return new FieldModifier(ModifierKind.ID, Map.of());
    }

    public static FieldModifier generated() {
        return new FieldModifier(ModifierKind.GENERATED, Map.of());
    }

    public static FieldModifier required() {
        return new FieldModifier(ModifierKind.REQUIRED, Map.of());
    }

    public static FieldModifier unique() {
        return new FieldModifier(ModifierKind.UNIQUE, Map.of());
    }

    public static FieldModifier optional() {
        return new FieldModifier(ModifierKind.OPTIONAL, Map.of());
    }

    public static FieldModifier min(int min) {
        return new FieldModifier(ModifierKind.MIN, Map.of(ATTR_MIN, String.valueOf(min)));
    }

    public static FieldModifier max(int max) {
        return new FieldModifier(ModifierKind.MAX, Map.of(ATTR_MAX, String.valueOf(max)));
    }

    public static FieldModifier sizeMinMax(int min, int max) {
        return new FieldModifier(ModifierKind.SIZE, Map.of(
                ATTR_MIN, String.valueOf(min),
                ATTR_MAX, String.valueOf(max)
        ));
    }

    public static FieldModifier sizeMin(int min) {
        return new FieldModifier(ModifierKind.SIZE, Map.of(ATTR_MIN, String.valueOf(min)));
    }

    public static FieldModifier sizeMax(int max) {
        return new FieldModifier(ModifierKind.SIZE, Map.of(ATTR_MAX, String.valueOf(max)));
    }

    public static FieldModifier email() {
        return new FieldModifier(ModifierKind.EMAIL, Map.of());
    }

    public static FieldModifier manyToOne() {
        return new FieldModifier(ModifierKind.MANY_TO_ONE, Map.of());
    }

    public static FieldModifier oneToMany(String mappedBy) {
        return new FieldModifier(ModifierKind.ONE_TO_MANY, Map.of(ATTR_MAPPED_BY, mappedBy));
    }

    public static FieldModifier oneToOneOwner() {
        return new FieldModifier(ModifierKind.ONE_TO_ONE_OWNER, Map.of());
    }

    public static FieldModifier oneToOne(String mappedBy) {
        return new FieldModifier(ModifierKind.ONE_TO_ONE, Map.of(ATTR_MAPPED_BY, mappedBy));
    }

    public static FieldModifier manyToMany() {
        return new FieldModifier(ModifierKind.MANY_TO_MANY, Map.of());
    }

    public ModifierKind getKind() {
        return kind;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String requireAttribute(String key) {
        String value = attributes.get(key);
        if (value == null) {
            throw new IllegalStateException("Modifier " + kind + " is missing required attribute: " + key);
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldModifier that)) {
            return false;
        }
        return kind == that.kind && attributes.equals(that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, attributes);
    }
}
