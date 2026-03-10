package nandi.project.validation;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;
import nandi.project.processor.CompositeEntityProcessor;
import nandi.project.processor.ImportProcessor;
import nandi.project.processor.PrimaryKeyProcessor;
import nandi.project.processor.TypeProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the refactored validation system demonstrating SOLID principles.
 */
class EntityValidationTest {

    @Test
    void testGeneratedValueValidator_throwsExceptionForInvalidType() {
        EntityModel entity = new EntityModel();
        entity.setName("TestEntity");

        FieldModel field = new FieldModel();
        field.setName("code");
        field.setType("String");
        field.getModifiers().add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
        entity.getFields().add(field);

        GeneratedValueValidator validator = new GeneratedValueValidator();

        assertThrows(IllegalDSLInputException.class, () -> validator.validate(entity));
    }

    @Test
    void testGeneratedValueValidator_acceptsValidType() {
        EntityModel entity = new EntityModel();
        entity.setName("TestEntity");

        FieldModel field = new FieldModel();
        field.setName("id");
        field.setType("Integer");
        field.getModifiers().add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
        entity.getFields().add(field);

        GeneratedValueValidator validator = new GeneratedValueValidator();

        assertDoesNotThrow(() -> validator.validate(entity));
    }

    @Test
    void testPrimaryKeyValidator_throwsExceptionForMultiplePrimaryKeys() {
        EntityModel entity = new EntityModel();
        entity.setName("TestEntity");

        FieldModel field1 = new FieldModel();
        field1.setName("id1");
        field1.setType("Integer");
        field1.getModifiers().add("@Id");
        entity.getFields().add(field1);

        FieldModel field2 = new FieldModel();
        field2.setName("id2");
        field2.setType("Integer");
        field2.getModifiers().add("@Id");
        entity.getFields().add(field2);

        PrimaryKeyValidator validator = new PrimaryKeyValidator();

        assertThrows(IllegalDSLInputException.class, () -> validator.validate(entity));
    }

    @Test
    void testCompositeValidator_executesAllValidators() {
        EntityModel entity = new EntityModel();
        entity.setName("TestEntity");

        // Create a field that violates both rules
        FieldModel field1 = new FieldModel();
        field1.setName("id1");
        field1.setType("String");  // Invalid for GeneratedValue
        field1.getModifiers().add("@Id");
        field1.getModifiers().add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
        entity.getFields().add(field1);

        CompositeEntityValidator validator = new CompositeEntityValidator()
            .addValidator(new GeneratedValueValidator())
            .addValidator(new PrimaryKeyValidator());

        // Should fail on first validator (GeneratedValueValidator)
        assertThrows(IllegalDSLInputException.class, () -> validator.validate(entity));
    }

    @Test
    void testEntityModel_withCustomValidators() {
        // Create custom validator
        EntityValidator customValidator = entity -> {
            if (entity.getName().equals("InvalidName")) {
                throw new IllegalDSLInputException("Invalid entity name");
            }
        };

        CompositeEntityValidator compositeValidator = new CompositeEntityValidator()
            .addValidator(customValidator);

        EntityModel entity = new EntityModel(
            compositeValidator,
            new CompositeEntityProcessor().addProcessor(new ImportProcessor()).addProcessor(new PrimaryKeyProcessor()).addProcessor(new TypeProcessor())
        );
        entity.setName("InvalidName");

        assertThrows(IllegalDSLInputException.class, entity::validate);
    }

    @Test
    void testPrimaryKeyProcessor_createsDefaultIdWhenNoneExists() {
        EntityModel entity = new EntityModel();
        entity.setName("TestEntity");

        FieldModel field = new FieldModel();
        field.setName("name");
        field.setType("String");
        entity.getFields().add(field);

        CompositeEntityProcessor processor = new CompositeEntityProcessor().addProcessor( new PrimaryKeyProcessor());
        processor.process(entity);

        // Should have 2 fields now: default id + name
        assertEquals(2, entity.getFields().size());
        assertEquals("id", entity.getFields().getFirst().getName());
        assertEquals("Integer", entity.getFields().getFirst().getType());
        assertTrue(entity.getFields().getFirst().getModifiers().contains("@Id"));
    }

    @Test
    void testPrimaryKeyProcessor_movesPrimaryKeyToFirst() {
        EntityModel entity = new EntityModel();
        entity.setName("TestEntity");

        FieldModel field1 = new FieldModel();
        field1.setName("name");
        field1.setType("String");
        entity.getFields().add(field1);

        FieldModel field2 = new FieldModel();
        field2.setName("customId");
        field2.setType("Long");
        field2.getModifiers().add("@Id");
        entity.getFields().add(field2);

        PrimaryKeyProcessor processor = new PrimaryKeyProcessor();
        processor.process(entity);

        // Primary key should be first
        assertEquals("customId", entity.getFields().getFirst().getName());
        assertEquals(2, entity.getFields().size());
    }

    @Test
    void testImportProcessor_addsValidationImports() {
        EntityModel entity = new EntityModel();
        entity.setName("TestEntity");

        FieldModel field = new FieldModel();
        field.setName("email");
        field.setType("String");
        field.getModifiers().add("@Email");
        entity.getFields().add(field);

        ImportProcessor processor = new ImportProcessor();
        processor.process(entity);

        assertTrue(entity.getImports().contains("jakarta.validation.constraints.*"));
    }

    @Test
    void testImportProcessor_addsListImport() {
        EntityModel entity = new EntityModel();
        entity.setName("TestEntity");

        FieldModel field = new FieldModel();
        field.setName("tags");
        field.setType("String");
        field.setArray(true);
        entity.getFields().add(field);

        ImportProcessor processor = new ImportProcessor();
        processor.process(entity);

        assertTrue(entity.getImports().contains("java.util.List"));
    }

    @Test
    void testEntityBuilder_buildValidEntity_appliesDefaultPrimaryKey() {
        FieldModel field = new FieldModel();
        field.setName("name");
        field.setType("String");

        EntityModel entity = EntityModel.builder()
            .name("TestEntity")
            .addField(field)
            .build();

        assertEquals("TestEntity", entity.getName());
        assertEquals("id", entity.getFields().getFirst().getName());
        assertEquals("Integer", entity.getFields().getFirst().getType());
    }

    @Test
    void testEntityBuilder_buildInvalidEntity_throwsException() {
        FieldModel field = new FieldModel();
        field.setName("code");
        field.setType("String");
        field.getModifiers().add("@GeneratedValue(strategy = GenerationType.IDENTITY)");

        assertThrows(IllegalDSLInputException.class, () -> EntityModel.builder()
            .name("TestEntity")
            .addField(field)
            .build());
    }
}
