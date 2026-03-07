package nandi.project.model;

import nandi.project.exception.IllegalDSLInputException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an entity parsed from the DSL, including its name and fields.
 */
public class EntityModel {
    private String name;
    private final List<FieldModel> fields = new ArrayList<>();


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
        this.name = Character.toUpperCase(name.charAt(0))+  name.substring(1);
    }

    /**
     * Returns the list of entity fields.
     *
     * @return field models
     */
    public List<FieldModel> getFields() {
        return fields;
    }
    public String getKeyType() {
        return fields.getFirst().getType();
    }

    public void validate() throws IllegalDSLInputException {
        int primaryKeyCount = 0;
        FieldModel idField = null;
        for (var field : fields) {
            if(field.getModifiers().contains("@GeneratedValue(strategy = GenerationType.IDENTITY)") && !(field.getType().equals("Integer") || field.getType().equals("Long")))
                throw new IllegalDSLInputException("Field '" + field.getName() + "' in entity '" + name + "' is marked as GENERATED but is not of type number.");
            else if(field.getModifiers().contains("@Id")){
                idField = field;
                primaryKeyCount++;
            }
        }
        if(primaryKeyCount == 1) {
            fields.remove(idField);
            fields.addFirst(idField);
        }
        if(primaryKeyCount > 1) {
            throw new IllegalDSLInputException("Entity '" + name + "' has multiple fields marked as PRIMARY. Only one primary key is allowed.");
        }
        if(primaryKeyCount == 0) {
            getFields().addFirst(new FieldModel(){
                {
                    setName("id");
                    setType("Integer");
                    setArray(false);
                    getModifiers().add("@Id");
                    getModifiers().add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
                }
            });
        }


    }
}
