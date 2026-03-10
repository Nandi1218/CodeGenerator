package nandi.project.processor;

import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;
import nandi.project.validation.EntityValidator;

/**
 * Processes primary key fields in an entity model.
 * Ensures primary key is positioned first and creates default ID if none exists.
 */
public class PrimaryKeyProcessor implements EntityProcessor {

    /**
     * Processes the primary key for an entity.
     * Moves existing primary key to first position or creates a default one.
     *
     * @param entity the entity model to process
     */
    public void process(EntityModel entity) {
        FieldModel primaryKeyField = findPrimaryKey(entity);

        if (primaryKeyField != null) {
            movePrimaryKeyToFirst(entity, primaryKeyField);
        } else {
            createDefaultPrimaryKey(entity);
        }
    }

    /**
     * Finds the primary key field in an entity.
     *
     * @param entity the entity to search
     * @return the primary key field, or null if not found
     */
    private FieldModel findPrimaryKey(EntityModel entity) {
        for (FieldModel field : entity.getFields()) {
            if (field.getModifiers().contains("@Id")) {
                return field;
            }
        }
        return null;
    }

    /**
     * Moves the primary key field to the first position in the field list.
     *
     * @param entity the entity model
     * @param primaryKeyField the primary key field to move
     */
    private void movePrimaryKeyToFirst(EntityModel entity, FieldModel primaryKeyField) {
        entity.getFields().remove(primaryKeyField);
        entity.getFields().addFirst(primaryKeyField);
    }

    /**
     * Creates a default primary key field with name "id" and type Integer.
     * Adds @Id and @GeneratedValue annotations.
     *
     * @param entity the entity model to add the default primary key to
     */
    private void createDefaultPrimaryKey(EntityModel entity) {
        FieldModel defaultIdField = new FieldModel();
        defaultIdField.setName("id");
        defaultIdField.setType("Integer");
        defaultIdField.setArray(false);
        defaultIdField.getModifiers().add("@Id");
        defaultIdField.getModifiers().add("@GeneratedValue(strategy = GenerationType.IDENTITY)");

        entity.getFields().addFirst(defaultIdField);
    }
}

