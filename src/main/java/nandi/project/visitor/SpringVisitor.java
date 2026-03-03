package nandi.project.visitor;

import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;
import nandi.project.EntityDSLBaseVisitor;
import nandi.project.EntityDSLParser.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Visits parsed DSL nodes and maps them to entity and field models.
 */
public class SpringVisitor extends EntityDSLBaseVisitor<Object> {

    /**
     * Builds a list of entity models from the root model node.
     *
     * @param ctx model parse context
     * @return list of parsed entities
     */
    @Override
    public Object visitModel(ModelContext ctx) {
        List<EntityModel> entities = new ArrayList<>();
        for (EntityContext entityCtx : ctx.entity()) {
            entities.add((EntityModel) visitEntity(entityCtx));
        }
        return entities;
    }

    /**
     * Builds one entity model from an entity node.
     *
     * @param ctx entity parse context
     * @return parsed entity model
     */
    @Override
    public Object visitEntity(EntityContext ctx) {
        EntityModel entity = new EntityModel();
        entity.setName(ctx.ID().getText());
        for (PropertyContext propCtx : ctx.property()) {
            entity.getFields().add((FieldModel) visitProperty(propCtx));
        }
        return entity;
    }

    /**
     * Builds one field model from a property node.
     *
     * @param ctx property parse context
     * @return parsed field model
     */
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

    /**
     * Maps a simple type declaration to a field model.
     *
     * @param ctx simple type parse context
     * @return field model with scalar type
     */
    @Override
    public Object visitSimpleType(SimpleTypeContext ctx) {
        FieldModel field = new FieldModel();
        field.setType(ctx.ID().getText());
        field.setArray(false);
        return field;
    }

    /**
     * Maps a list type declaration to a field model.
     *
     * @param ctx list type parse context
     * @return field model with list type
     */
    @Override
    public Object visitListType(ListTypeContext ctx) {
        FieldModel field = new FieldModel();
        field.setType("List<" + ctx.ID().getText() + ">");
        field.setArray(true);
        return field;
    }

    /**
     * Maps PRIMARY modifier to JPA annotation.
     *
     * @param ctx PRIMARY parse context
     * @return annotation string
     */
    @Override
    public Object visitPRIMARY(PRIMARYContext ctx) {
        return "@Id";
    }

    /**
     * Maps GENERATED modifier to JPA annotation.
     *
     * @param ctx GENERATED parse context
     * @return annotation string
     */
    @Override
    public Object visitGENERATED(GENERATEDContext ctx) {
        return "@GeneratedValue(strategy = GenerationType.IDENTITY)";
    }

    /**
     * Maps REQUIRED modifier to column annotation.
     *
     * @param ctx REQUIRED parse context
     * @return annotation string
     */
    @Override
    public Object visitREQUIRED(REQUIREDContext ctx) {
        return "@Column(nullable = false)";
    }

    /**
     * Maps UNIQUE modifier to column annotation.
     *
     * @param ctx UNIQUE parse context
     * @return annotation string
     */
    @Override
    public Object visitUNIQUE(UNIQUEContext ctx) {
        return "@Column(unique = true)";
    }

    /**
     * Maps OPTIONAL modifier to column annotation.
     *
     * @param ctx OPTIONAL parse context
     * @return annotation string
     */
    @Override
    public Object visitOPTIONAL(OPTIONALContext ctx) {
        return "@Column(nullable = true)";
    }

    /**
     * Maps MIN modifier to validation annotation.
     *
     * @param ctx MIN parse context
     * @return annotation string
     */
    @Override
    public Object visitMIN(MINContext ctx) {
        String value = ctx.INT().getText();
        return "@Min(" + value + ")";
    }

    /**
     * Maps MAX modifier to validation annotation.
     *
     * @param ctx MAX parse context
     * @return annotation string
     */
    @Override
    public Object visitMAX(MAXContext ctx) {
        String value = ctx.INT().getText();
        return "@Max(" + value + ")";
    }

    /**
     * Maps LENGTH modifier to size annotation.
     *
     * @param ctx LENGTH parse context
     * @return annotation string
     */
    @Override
    public Object visitLENGTH(LENGTHContext ctx) {
        String min = ctx.INT(0).getText();
        String max = ctx.INT(1).getText();
        return "@Size(min = " + min + ", max = " + max + ")";
    }

    /**
     * Maps MIN_LENGTH modifier to size annotation.
     *
     * @param ctx MIN_LENGTH parse context
     * @return annotation string
     */
    @Override
    public Object visitMIN_LENGTH(MIN_LENGTHContext ctx) {
        String min = ctx.INT().getText();
        return "@Size(min = " + min + ")";
    }

    /**
     * Maps MAX_LENGTH modifier to size annotation.
     *
     * @param ctx MAX_LENGTH parse context
     * @return annotation string
     */
    @Override
    public Object visitMAX_LENGTH(MAX_LENGTHContext ctx) {
        String max = ctx.INT().getText();
        return "@Size(max = " + max + ")";
    }

    /**
     * Maps EMAIL modifier to validation annotation.
     *
     * @param ctx EMAIL parse context
     * @return annotation string
     */
    @Override
    public Object visitEMAIL(EMAILContext ctx) {
        return "@Email";
    }
}