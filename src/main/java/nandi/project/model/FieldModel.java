package nandi.project.model;

import nandi.project.render.FieldModifierRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a field parsed from the DSL, including type metadata and modifiers.
 */
public class FieldModel {

    private String name;
    private String type;
    private boolean isArray;
    private final List<FieldModifier> modifiers = new ArrayList<>();

    /**
     * Returns the field name.
     *
     * @return field name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the field name.
     *
     * @param name field name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the Java type of the field.
     *
     * @return field type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the Java type of the field.
     *
     * @param type field type
     */
    public void setType(String type) {
        switch (type) {
            case "boolean" -> this.type = "Boolean";
            case "int" -> this.type = "Integer";
            case "long" -> this.type = "Long";
            case "double" -> this.type = "Double";
            default -> this.type = type;
        }
    }

    /**
     * Indicates whether the field is represented as a list/array type.
     *
     * @return true if the field is an array/list type, otherwise false
     */
    public boolean getIsArray() {
        return isArray;
    }

    /**
     * Sets whether the field is represented as a list/array type.
     *
     * @param array true for array/list type, otherwise false
     */
    public void setArray(boolean array) {
        isArray = array;
    }

    /**
     * Returns typed modifier specifications for this field.
     *
     * @return typed modifier list
     */
    public List<FieldModifier> getModifierSpecs() {
        return modifiers;
    }

    /**
     * Adds a typed modifier specification.
     *
     * @param modifier modifier to add
     */
    public void addModifier(FieldModifier modifier) {
        modifiers.add(modifier);
    }

    /**
     * Checks whether this field contains a modifier kind.
     *
     * @param kind modifier kind to check
     * @return true if present
     */
    public boolean hasModifier(ModifierKind kind) {
        return modifiers.stream().anyMatch(modifier -> modifier.getKind() == kind);
    }

    /**
     * Returns rendered annotations/modifiers for template consumption.
     *
     * @return rendered modifier strings
     */
    public List<String> getModifiers() {
        return modifiers.stream().map(FieldModifierRenderer::render).toList();
    }

    /**
     * Returns the field name with an uppercase first letter.
     *
     * @return capitalized field name
     */
    public String getCapitalName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}