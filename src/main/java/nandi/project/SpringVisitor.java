package nandi.project;

import nandi.project.generated.antlr4.EntityDSLBaseVisitor;
import nandi.project.generated.antlr4.EntityDSLParser.*;
import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;


import java.util.ArrayList;
import java.util.List;

public class SpringVisitor extends EntityDSLBaseVisitor<Object> {
    @Override
    public Object visitModel(ModelContext ctx) {
        List<EntityModel> entities = new ArrayList<>();
        for(EntityContext entityCtx : ctx.entity()) {
            entities.add((EntityModel) visitEntity(entityCtx));
        }
        return entities;
    }

    @Override
    public Object visitEntity(EntityContext ctx) {
        EntityModel entity = new EntityModel();
        entity.setName(ctx.ID().getText());
        for(PropertyContext propCtx : ctx.property()) {
            entity.getFields().add((FieldModel) visitProperty(propCtx));
        }
        return entity;
    }
    @Override
    public Object visitProperty(PropertyContext ctx) {
        FieldModel field = (FieldModel) visit(ctx.type());
        field.setName(ctx.ID().getText());
        if (ctx.modifier() != null) {
            for (ModifierContext modCtx : ctx.modifier()) {
                String modifier = (String) visit(modCtx);
                field.getModifiers().add(modifier);
            }
        }
        return field;
    }
    @Override
    public Object visitSimpleType(SimpleTypeContext ctx) {
        FieldModel field = new FieldModel();
        field.setType(ctx.ID().getText());
        field.setArray(false);
        return field;
    }
    @Override
    public Object visitListType(ListTypeContext ctx) {
        FieldModel field = new FieldModel();
        field.setType("List<"+ctx.ID().getText()+">");
        field.setArray(true);
        return field;
    }

    @Override
    public Object visitPRIMARY(PRIMARYContext ctx) {
        return "@Id";
    }

    @Override
    public Object visitGENERATED(GENERATEDContext ctx) {
        return "@GeneratedValue(strategy = GenerationType.IDENTITY)";
    }
    @Override
    public Object visitREQUIRED(REQUIREDContext ctx) {
        return "@Column(nullable = false)";
    }

    @Override
    public Object visitUNIQUE(UNIQUEContext ctx) {
        return "@Column(unique = true)";
    }
    @Override
    public Object visitOPTIONAL(OPTIONALContext ctx) {
        return "@Column(nullable = true)";
    }

    @Override
    public Object visitMIN(MINContext ctx) {
        String value = ctx.INT().getText();
        return "@Min(" + value + ")";
    }
    public Object visitMAX(MAXContext ctx) {
        String value = ctx.INT().getText();
        return "@Max(" + value + ")";
    }

    @Override
    public Object visitLENGTH(LENGTHContext ctx) {
        String min = ctx.INT(0).getText();
        String max = ctx.INT(1).getText();
        return "@Size(min = " + min + ", max = " + max + ")";
    }
    @Override
    public Object visitMIN_LENGTH(MIN_LENGTHContext ctx) {
        String min = ctx.INT().getText();
        return "@Size(min = " + min + ")";
    }
    @Override
    public Object visitMAX_LENGTH(MAX_LENGTHContext ctx) {
        String max = ctx.INT().getText();
        return "@Size(max = " + max + ")";
    }

}
