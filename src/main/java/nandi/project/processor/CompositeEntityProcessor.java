package nandi.project.processor;

import nandi.project.exception.IllegalDSLInputException;
import nandi.project.model.EntityModel;
import nandi.project.validation.CompositeEntityValidator;
import nandi.project.validation.EntityValidator;

import java.util.ArrayList;
import java.util.List;


public class CompositeEntityProcessor implements EntityProcessor {

    private final List<EntityProcessor> processors = new ArrayList<>();


    public CompositeEntityProcessor addProcessor(EntityProcessor processor) {
        processors.add(processor);
        return this;
    }

    @Override
    public void process(EntityModel entity) throws IllegalDSLInputException {
        for (var processor : processors) {
            processor.process(entity);
        }
    }
}