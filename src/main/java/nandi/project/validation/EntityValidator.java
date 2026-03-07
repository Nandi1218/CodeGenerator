package nandi.project.validation;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.model.EntityModel;

/**
 * Interface for entity validation strategies.
 * Implementations define specific validation rules for entity models.
 */
public interface EntityValidator {

    /**
     * Validates an entity model according to specific rules.
     *
     * @param entity the entity model to validate
     * @throws IllegalDSLInputException if validation fails
     */
    void validate(EntityModel entity) throws IllegalDSLInputException;
}

