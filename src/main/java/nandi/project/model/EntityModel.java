package nandi.project.model;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.processor.ImportProcessor;
import nandi.project.processor.PrimaryKeyProcessor;
import nandi.project.validation.CompositeEntityValidator;
import nandi.project.validation.GeneratedValueValidator;
import nandi.project.validation.PrimaryKeyValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents an entity parsed from the DSL, including its name and fields.
 * Provides validation and processing capabilities through SOLID-compliant components.
 */
public class EntityModel {
    private String name;
    private final List<FieldModel> fields = new ArrayList<>();
    private Set<String> imports = new HashSet<>();

    private final CompositeEntityValidator validator;
    private final ImportProcessor importProcessor;
    private final PrimaryKeyProcessor primaryKeyProcessor;

    /**
     * Creates a new EntityModel with default validators and processors.
     */
    public EntityModel() {
        this.validator = new CompositeEntityValidator()
            .addValidator(new GeneratedValueValidator())
            .addValidator(new PrimaryKeyValidator());
        this.importProcessor = new ImportProcessor();
        this.primaryKeyProcessor = new PrimaryKeyProcessor();
    }

    /**
     * Creates a new EntityModel with custom validators and processors.
     * Allows for dependency injection and testing.
     *
     * @param validator the validator to use for entity validation
     * @param importProcessor the processor for managing imports
     * @param primaryKeyProcessor the processor for primary key handling
     */
    public EntityModel(CompositeEntityValidator validator,
                       ImportProcessor importProcessor,
                       PrimaryKeyProcessor primaryKeyProcessor) {
        this.validator = validator;
        this.importProcessor = importProcessor;
        this.primaryKeyProcessor = primaryKeyProcessor;
    }

    public Set<String> getImports() {
        return imports;
    }

    /**
     * Returns the entity name.
     *
     * @return entity name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the entity name.
     *
     * @param name entity name
     */
    public void setName(String name) {
        this.name = Character.toUpperCase(name.charAt(0))+  name.substring(1);
    }

    /**
     * Returns the list of entity fields.
     *
     * @return field models
     */
    public List<FieldModel> getFields() {
        return fields;
    }
    public String getKeyType() {
        return fields.getFirst().getType();
    }

    /**
     * Validates the entity model and performs necessary processing.
     * Delegates to specialized validators and processors following SOLID principles.
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
        validator.validate(this);
        primaryKeyProcessor.processPrimaryKey(this);
        importProcessor.processImports(this);
    }
}
