package nandi.project.processor;

import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;

import java.util.Set;

/**
 * Processes entity fields and determines required imports.
 * Analyzes field modifiers and types to collect necessary import statements.
 */
public class ImportProcessor implements EntityProcessor {

    /**
     * Processes an entity and populates its import set based on field modifiers and types.
     *
     * @param entity the entity model to process
     */
    public void process(EntityModel entity) {
        Set<String> imports = entity.getImports();

        for (FieldModel field : entity.getFields()) {
            processFieldModifiers(field, imports);
            processFieldType(field, imports);
        }
    }

    /**
     * Processes field modifiers to determine validation-related imports.
     *
     * @param field the field to process
     * @param imports the import set to populate
     */
    private void processFieldModifiers(FieldModel field, Set<String> imports) {
        for (String modifier : field.getModifiers()) {
            if (isValidationAnnotation(modifier)) {
                imports.add("jakarta.validation.constraints.*");
                break;
            }
        }
    }

    /**
     * Processes field type to determine collection-related imports.
     *
     * @param field the field to process
     * @param imports the import set to populate
     */
    private void processFieldType(FieldModel field, Set<String> imports) {
        if (field.getIsArray()) {
            imports.add("java.util.List");
        }
    }

    /**
     * Checks if a modifier string represents a validation annotation.
     *
     * @param modifier the modifier to check
     * @return true if it's a validation annotation
     */
    private boolean isValidationAnnotation(String modifier) {
        return modifier.contains("Email") ||
               modifier.contains("Size") ||
               modifier.contains("NotNull") ||
               modifier.contains("Max") ||
               modifier.contains("Min");
    }
}

