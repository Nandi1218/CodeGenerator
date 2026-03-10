package nandi.project.model;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.processor.CompositeEntityProcessor;
import nandi.project.processor.ImportProcessor;
import nandi.project.processor.PrimaryKeyProcessor;
import nandi.project.processor.TypeProcessor;
import nandi.project.validation.CompositeEntityValidator;
import nandi.project.validation.GeneratedValueValidator;
import nandi.project.validation.PrimaryKeyValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents an entity parsed from the DSL, including its name and fields.
 */
public class EntityModel {
    private String name;
    private final List<FieldModel> fields = new ArrayList<>();
    private final Set<String> imports = new HashSet<>();

    private final CompositeEntityProcessor entityProcessor;
    private final CompositeEntityValidator entityValidator;


    /**
     * Creates a new EntityModel with default validators and processors.
     */
    public EntityModel() {
        this(
            new CompositeEntityValidator()
                .addValidator(new GeneratedValueValidator())
                .addValidator(new PrimaryKeyValidator()),
            new CompositeEntityProcessor()
                .addProcessor(new ImportProcessor())
                .addProcessor(new PrimaryKeyProcessor())
                .addProcessor(new TypeProcessor())
            );
    }

    /**
     * Creates a new EntityModel with custom validators and processors.
     *
     * @param entityValidator the validator to use for entity validation
     * @param entityProcessor the processor to use for entity processing
     */
    public EntityModel(CompositeEntityValidator entityValidator,
                       CompositeEntityProcessor entityProcessor) {
        this.entityValidator = entityValidator;
        this.entityProcessor = entityProcessor;
    }

    /**
     * Creates a new builder for constructing a validated EntityModel.
     *
     * <p>The builder provides a fluent API for constructing entities with automatic validation
     * at build time, ensuring no invalid entities can be created.
     *
     * @return a new Builder instance
     */
    public static EntityBuilder builder() {
        return new EntityBuilder();
    }

    /**
     * Builder for constructing validated EntityModel instances.
     *
     * <p>This builder enforces a fluent interface for entity construction and ensures
     * validation is automatically applied during the build process. It provides optional
     * dependency injection points for custom validators and processors, falling back to
     * defaults if not specified.
     *
     * <p>Example usage:
     * <pre>
     *     EntityModel entity = EntityModel.builder()
     *         .name("User")
     *         .addField(fieldModel)
     *         .build();
     * </pre>
     *
     * @see EntityModel
     */
    public static final class EntityBuilder {
        private String name;
        private final List<FieldModel> fields = new ArrayList<>();
        private CompositeEntityValidator validator;
        private CompositeEntityProcessor processor;

        /**
         * Sets the entity name.
         *
         * <p>The name will be normalized (first character capitalized) during build.
         *
         * @param name the entity name
         * @return this builder for method chaining
         */
        public EntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Adds a field to the entity.
         *
         * @param field the field model to add
         * @return this builder for method chaining
         */
        public EntityBuilder addField(FieldModel field) {
            this.fields.add(field);
            return this;
        }

        /**
         * Provides a custom validator for the entity.
         *
         * @param validator the composite validator to use
         * @return this builder for method chaining
         */
        public EntityBuilder validator(CompositeEntityValidator validator) {
            this.validator = validator;
            return this;
        }

        public EntityBuilder processor(CompositeEntityProcessor processor) {
            this.processor = processor;
            return this;
        }

        /**
         * Builds and validates the EntityModel.
         *
         * <p>This method constructs an EntityModel with the accumulated configuration,
         * applies defaults for any unspecified validators or processors, sets the entity name,
         * adds all fields, and invokes validation to ensure the entity is valid.
         *
         * <p>Validation steps performed during build:
         * <ul>
         *   <li>Validates generated value field types</li>
         *   <li>Validates primary key constraints</li>
         *   <li>Processes primary key field and moves it to first position</li>
         *   <li>Processes and collects required imports</li>
         * </ul>
         *
         * @return a validated EntityModel instance
         * @throws IllegalDSLInputException if validation fails during entity construction
         */
        public EntityModel build() {
            CompositeEntityValidator effectiveValidator = validator != null
                    ? validator
                    : new CompositeEntityValidator()
                    .addValidator(new GeneratedValueValidator())
                    .addValidator(new PrimaryKeyValidator());
            CompositeEntityProcessor effectiveEntityProcessor = processor != null
                    ? processor
                    : new CompositeEntityProcessor()
                    .addProcessor(new ImportProcessor())
                    .addProcessor(new PrimaryKeyProcessor())
                    .addProcessor(new TypeProcessor());


            EntityModel entity = new EntityModel(effectiveValidator, effectiveEntityProcessor);
            entity.setName(name);
            entity.getFields().addAll(fields);
            entity.validate();
            return entity;
        }
    }

    /**
     * Returns the set of imports required by this entity.
     *
     * <p>Imports are collected during validation and include annotations, utilities,
     * and custom type imports needed for the generated entity class.
     *
     * @return the set of import statements
     */
    public Set<String> getImports() {
        return imports;
    }

    /**
     * Returns the entity name.
     *
     * @return entity name (capitalized)
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the entity name.
     *
     * <p>The provided name is normalized by capitalizing the first character.
     *
     * @param name entity name
     */
    public void setName(String name) {
        this.name = Character.toUpperCase(name.charAt(0))+  name.substring(1);
    }

    /**
     * Returns the list of entity fields.
     *
     * <p>The primary key field, if present, is guaranteed to be at index 0.
     * If no primary key exists, a default `id: Integer` field is added during validation.
     *
     * @return field models in order (primary key first if present)
     */
    public List<FieldModel> getFields() {
        return fields;
    }

    /**
     * Returns the type of the primary key field.
     *
     * <p>The primary key is guaranteed to be the first field in the entity.
     *
     * @return the primary key field type (e.g., "Integer", "Long")
     */
    public String getKeyType() {
        return fields.getFirst().getType();
    }

    /**
     * Validates the entity using the configured validators and processors.
     *
     * <p>Validation steps:
     * <ul>
     *   <li>Validates generated value field types</li>
     *   <li>Validates primary key constraints</li>
     *   <li>Processes and organizes primary key field</li>
     *   <li>Processes required imports</li>
     * </ul>
     *
     * @throws IllegalDSLInputException if validation fails
     */
    public void validate() throws IllegalDSLInputException {
        entityProcessor.process(this);
        entityValidator.validate(this);
    }
}
