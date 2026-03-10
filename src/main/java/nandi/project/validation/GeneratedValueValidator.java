package nandi.project.validation;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;
import nandi.project.model.ModifierKind;

/**
 * Validates that fields marked with @GeneratedValue have appropriate numeric types.
 * Only Integer and Long types are allowed for generated values.
 */
public class GeneratedValueValidator implements EntityValidator {

    @Override
    public void validate(EntityModel entity) throws IllegalDSLInputException {
        for (FieldModel field : entity.getFields()) {
            if (field.hasModifier(ModifierKind.GENERATED)) {
                if (!isNumericType(field.getType())) {
                    throw new IllegalDSLInputException(
                        "Field '" + field.getName() + "' in entity '" + entity.getName() +
                        "' is marked as GENERATED but is not of type number."
                    );
                }
            }
        }
    }

    /**
     * Checks if the given type is a valid numeric type for generated values.
     *
     * @param type the field type to check
     * @return true if type is Integer or Long
     */
    private boolean isNumericType(String type) {
        return "Integer".equals(type) || "Long".equals(type);
    }
}
