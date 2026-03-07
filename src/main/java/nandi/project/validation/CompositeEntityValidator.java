package nandi.project.validation;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.model.EntityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite validator that executes multiple validation strategies.
 * Follows the Composite pattern to allow flexible combination of validators.
 */
public class CompositeEntityValidator implements EntityValidator {

    private final List<EntityValidator> validators = new ArrayList<>();

    /**
     * Adds a validator to the composite.
     *
     * @param validator the validator to add
     * @return this composite validator for method chaining
     */
    public CompositeEntityValidator addValidator(EntityValidator validator) {
        validators.add(validator);
        return this;
    }

    /**
     * Validates an entity using all registered validators.
     * Stops at the first validation failure.
     *
     * @param entity the entity model to validate
     * @throws IllegalDSLInputException if any validation fails
     */
    @Override
    public void validate(EntityModel entity) throws IllegalDSLInputException {
        for (EntityValidator validator : validators) {
            validator.validate(entity);
        }
    }
}

