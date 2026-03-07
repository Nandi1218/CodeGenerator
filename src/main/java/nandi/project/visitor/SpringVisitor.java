package nandi.project.visitor;

import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;
import nandi.project.EntityDSLBaseVisitor;
import nandi.project.EntityDSLParser.*;
import org.antlr.v4.runtime.tree.TerminalNode;

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
     * Builds and validates an `EntityModel` from an entity parse node.
     *
     * Validation rules:
     * \- `@GeneratedValue(strategy = GenerationType.IDENTITY)` is only valid for `Integer` or `Long` fields.
     * \- At most one primary key field is allowed, and it must be of type `Integer`.
     * \- If no primary key is defined, a default `id: Integer` field is added with `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)`.
     *
     * @param ctx entity parse context
     * @return validated entity model
     * @throws IllegalArgumentException if primary key or generated field constraints are violated
     */
    @Override
    public Object visitEntity(EntityContext ctx) {
        EntityModel entity = new EntityModel();
        entity.setName(ctx.ID().getText());
        for (PropertyContext propCtx : ctx.property()) {
            entity.getFields().add((FieldModel) visitProperty(propCtx));
        }
        entity.validate();
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
        String type = format(ctx.ID());
        Result result = new Result(field, type);

        result.field().setType(result.type());
        result.field().setArray(false);
        return result.field();
    }

    private record Result(FieldModel field, String type) {
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
        String type = format(ctx.ID());

        field.setType("List<" +type + ">");
        field.setArray(true);
        return field;
    }
    /**
    * Normalizes a parsed type token into a Java\-friendly type name.
     *
     * <p>Rules applied:
     * <ul>
     * <li>Maps primitive aliases: `int` to `Integer`, `long` to `Long`.</li>
     * <li>Capitalizes the first character when it is lowercase.</li>
     * </ul>
     *
     * @param ctx terminal node containing the raw type token from the DSL * @return normalized type name used by generated field models
     */
    private static String format(TerminalNode ctx) {
        String type = ctx.getText();
        if (type.equals("int")) type = "Integer";
        else if (type.equals("long")) type = "Long";
        if (Character.isLowerCase(type.charAt(0)))
            type = Character.toUpperCase(type.charAt(0)) + type.substring(1);
        return type;
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