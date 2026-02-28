package model;

import java.util.ArrayList;
import java.util.List;

public class EntityModel {
    private String name;
    private List<FieldModel> fields = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldModel> getFields() {
        return fields;
    }

    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }
}
