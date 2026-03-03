package nandi.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a field parsed from the DSL, including type metadata and modifiers.
 */
public class FieldModel {

    private String name;
    private String type;
    private boolean isArray;
    private List<String> modifiers = new ArrayList<>();

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
        this.type = type;
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
     * Returns the list of annotations/modifiers for this field.
     *
     * @return modifier strings
     */
    public List<String> getModifiers() {
        return modifiers;
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