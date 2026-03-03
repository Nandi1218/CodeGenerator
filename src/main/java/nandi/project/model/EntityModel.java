package nandi.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an entity parsed from the DSL, including its name and fields.
 */
public class EntityModel {
    private String name;
    private List<FieldModel> fields = new ArrayList<>();

    /**
     * Returns the entity name.
     *
     * @return entity name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the entity name.
     *
     * @param name entity name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the list of entity fields.
     *
     * @return field models
     */
    public List<FieldModel> getFields() {
        return fields;
    }

    /**
     * Replaces the list of entity fields.
     *
     * @param fields field models
     */
    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }
}