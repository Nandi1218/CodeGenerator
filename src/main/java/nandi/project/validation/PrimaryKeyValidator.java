package nandi.project.validation;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;
import nandi.project.model.ModifierKind;

/**
 * Validates that an entity has exactly one primary key field.
 * Throws an exception if multiple primary keys are found.
 */
public class PrimaryKeyValidator implements EntityValidator {

    @Override
    public void validate(EntityModel entity) throws IllegalDSLInputException {
        int primaryKeyCount = 0;

        for (FieldModel field : entity.getFields()) {
            if (field.hasModifier(ModifierKind.ID)) {
                primaryKeyCount++;
            }
        }

        if (primaryKeyCount > 1) {
            throw new IllegalDSLInputException(
                "Entity '" + entity.getName() + "' has multiple fields marked as PRIMARY. Only one primary key is allowed."
            );
        }
    }
}
