package nandi.project.processor;

import nandi.project.model.EntityModel;
import nandi.project.validation.EntityValidator;

public class TypeProcessor implements EntityProcessor {
    public void process(EntityModel entity) {
        for(var field : entity.getFields()) {
            switch(field.getType()) {
                case "int" -> field.setType("Integer");
                case "long" -> field.setType("Long");
                case "double" -> field.setType("Double");
                default -> field.setType(Character.toUpperCase(field.getType().charAt(0)) + field.getType().substring(1));
            }
        }
    }
}
