package nandi.project.validation;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.model.EntityModel;
import nandi.project.model.ModifierKind;

public class RelationValidator implements EntityValidator {
    @Override
    public void validate(EntityModel entity) throws IllegalDSLInputException {
        entity.getFields().forEach(field -> {
            if (field.hasModifier(ModifierKind.ONE_TO_MANY) && !field.getIsArray()) {
                throw new IllegalDSLInputException("Field " + field.getName() + " in entity " + entity.getName() + " is marked as OneToMany but is not an array.");
            }
            if (field.hasModifier(ModifierKind.MANY_TO_MANY) && !field.getIsArray()) {
                throw new IllegalDSLInputException("Field " + field.getName() + " in entity " + entity.getName() + " is marked as ManyToMany but is not an array.");
            }
            if (field.hasModifier(ModifierKind.MANY_TO_ONE) && field.getIsArray()) {
                throw new IllegalDSLInputException("Field " + field.getName() + " in entity " + entity.getName() + " is marked as ManyToOne but is an array.");
            }
            if ((field.hasModifier(ModifierKind.ONE_TO_ONE) || field.hasModifier(ModifierKind.ONE_TO_ONE_OWNER)) && field.getIsArray()) {
                throw new IllegalDSLInputException("Field " + field.getName() + " in entity " + entity.getName() + " is marked as OneToOne but is an array.");
            }
        });
    }
}
