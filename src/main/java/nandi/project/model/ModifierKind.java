package nandi.project.model;

/**
 * Supported DSL field modifier kinds.
 */
public enum ModifierKind {
    ID,
    GENERATED,
    REQUIRED,
    UNIQUE,
    OPTIONAL,
    MIN,
    MAX,
    SIZE,
    EMAIL,
    MANY_TO_ONE,
    ONE_TO_MANY,
    ONE_TO_ONE_OWNER,
    ONE_TO_ONE,
    MANY_TO_MANY;

    public boolean isValidationConstraint() {
        return this == EMAIL || this == MIN || this == MAX || this == SIZE;
    }
}
