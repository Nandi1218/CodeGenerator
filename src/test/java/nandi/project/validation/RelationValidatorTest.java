package nandi.project.validation;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;
import nandi.project.model.FieldModifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RelationValidatorTest {

    @Test
    void testOneToManyRequiresArray() {
        EntityModel entity = createEntityWithField("orders", false, FieldModifier.oneToMany("customer"));
        RelationValidator validator = new RelationValidator();

        assertThrows(IllegalDSLInputException.class, () -> validator.validate(entity));
    }

    @Test
    void testManyToManyRequiresArray() {
        EntityModel entity = createEntityWithField("roles", false, FieldModifier.manyToMany());
        RelationValidator validator = new RelationValidator();

        assertThrows(IllegalDSLInputException.class, () -> validator.validate(entity));
    }

    @Test
    void testManyToOneMustBeScalar() {
        EntityModel entity = createEntityWithField("customer", true, FieldModifier.manyToOne());
        RelationValidator validator = new RelationValidator();

        assertThrows(IllegalDSLInputException.class, () -> validator.validate(entity));
    }

    @Test
    void testOneToOneMustBeScalar() {
        EntityModel entity = createEntityWithField("profile", true, FieldModifier.oneToOne("user"));
        RelationValidator validator = new RelationValidator();

        assertThrows(IllegalDSLInputException.class, () -> validator.validate(entity));
    }

    @Test
    void testValidRelationsPass() {
        EntityModel entity = new EntityModel();
        entity.setName("User");

        FieldModel oneToManyField = new FieldModel();
        oneToManyField.setName("orders");
        oneToManyField.setType("List<Order>");
        oneToManyField.setArray(true);
        oneToManyField.addModifier(FieldModifier.oneToMany("user"));
        entity.getFields().add(oneToManyField);

        FieldModel manyToOneField = new FieldModel();
        manyToOneField.setName("company");
        manyToOneField.setType("Company");
        manyToOneField.setArray(false);
        manyToOneField.addModifier(FieldModifier.manyToOne());
        entity.getFields().add(manyToOneField);

        FieldModel oneToOneField = new FieldModel();
        oneToOneField.setName("profile");
        oneToOneField.setType("Profile");
        oneToOneField.setArray(false);
        oneToOneField.addModifier(FieldModifier.oneToOne("user"));
        entity.getFields().add(oneToOneField);

        RelationValidator validator = new RelationValidator();
        assertDoesNotThrow(() -> validator.validate(entity));
    }

    private static EntityModel createEntityWithField(String name, boolean isArray, FieldModifier modifier) {
        EntityModel entity = new EntityModel();
        entity.setName("TestEntity");

        FieldModel field = new FieldModel();
        field.setName(name);
        field.setType(isArray ? "List<String>" : "String");
        field.setArray(isArray);
        field.addModifier(modifier);
        entity.getFields().add(field);
        return entity;
    }
}

