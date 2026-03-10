package nandi.project.visitor;

import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;
import nandi.project.model.FieldModifier;
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
        for (EntityContext entityCtx : ctx.entity()) entities.add((EntityModel) visitEntity(entityCtx));
        return entities;
    }

    /**
     * Builds an `EntityModel` from an entity parse node.
     * @param ctx entity parse context
     * @return validated entity model
     */
    @Override
    public Object visitEntity(EntityContext ctx) {
        EntityModel.EntityBuilder entityBuilder = EntityModel.builder().name(ctx.ID().getText());
        for (PropertyContext propCtx : ctx.property()) entityBuilder.addField((FieldModel) visitProperty(propCtx));
        return entityBuilder.build();
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
        field.setName(ctx.propertyName().getText());
        if (ctx.modifier() != null) {
            for (ModifierContext modCtx : ctx.modifier()) {
                field.addModifier((FieldModifier) visit(modCtx));
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
        field.setType(type);
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
        return FieldModifier.id();
    }

    /**
     * Maps GENERATED modifier to JPA annotation.
     *
     * @param ctx GENERATED parse context
     * @return annotation string
     */
    @Override
    public Object visitGENERATED(GENERATEDContext ctx) {
        return FieldModifier.generated();
    }

    /**
     * Maps REQUIRED modifier to column annotation.
     *
     * @param ctx REQUIRED parse context
     * @return annotation string
     */
    @Override
    public Object visitREQUIRED(REQUIREDContext ctx) {
        return FieldModifier.required();
    }

    /**
     * Maps UNIQUE modifier to column annotation.
     *
     * @param ctx UNIQUE parse context
     * @return annotation string
     */
    @Override
    public Object visitUNIQUE(UNIQUEContext ctx) {
        return FieldModifier.unique();
    }

    /**
     * Maps OPTIONAL modifier to column annotation.
     *
     * @param ctx OPTIONAL parse context
     * @return annotation string
     */
    @Override
    public Object visitOPTIONAL(OPTIONALContext ctx) {
        return FieldModifier.optional();
    }

    /**
     * Maps MIN modifier to validation annotation.
     *
     * @param ctx MIN parse context
     * @return annotation string
     */
    @Override
    public Object visitMIN(MINContext ctx) {
        return FieldModifier.min(Integer.parseInt(ctx.INT().getText()));
    }

    /**
     * Maps MAX modifier to validation annotation.
     *
     * @param ctx MAX parse context
     * @return annotation string
     */
    @Override
    public Object visitMAX(MAXContext ctx) {
        return FieldModifier.max(Integer.parseInt(ctx.INT().getText()));
    }

    /**
     * Maps LENGTH modifier to size annotation.
     *
     * @param ctx LENGTH parse context
     * @return annotation string
     */
    @Override
    public Object visitLENGTH(LENGTHContext ctx) {
        int min = Integer.parseInt(ctx.INT(0).getText());
        int max = Integer.parseInt(ctx.INT(1).getText());
        return FieldModifier.sizeMinMax(min, max);
    }

    /**
     * Maps MIN_LENGTH modifier to size annotation.
     *
     * @param ctx MIN_LENGTH parse context
     * @return annotation string
     */
    @Override
    public Object visitMIN_LENGTH(MIN_LENGTHContext ctx) {
        return FieldModifier.sizeMin(Integer.parseInt(ctx.INT().getText()));
    }

    /**
     * Maps MAX_LENGTH modifier to size annotation.
     *
     * @param ctx MAX_LENGTH parse context
     * @return annotation string
     */
    @Override
    public Object visitMAX_LENGTH(MAX_LENGTHContext ctx) {
        return FieldModifier.sizeMax(Integer.parseInt(ctx.INT().getText()));
    }

    /**
     * Maps EMAIL modifier to validation annotation.
     *
     * @param ctx EMAIL parse context
     * @return annotation string
     */
    @Override
    public Object visitEMAIL(EMAILContext ctx) {
        return FieldModifier.email();
    }

    @Override
    public Object visitMANY_TO_ONE(MANY_TO_ONEContext ctx) {
        return FieldModifier.manyToOne();
    }

    @Override
    public Object visitONE_TO_MANY(ONE_TO_MANYContext ctx) {
        return FieldModifier.oneToMany(ctx.ID().getText());
    }

    @Override
    public Object visitONE_TO_ONE_OWNER(ONE_TO_ONE_OWNERContext ctx) {
        return FieldModifier.oneToOneOwner();
    }

    @Override
    public Object visitONE_TO_ONE(ONE_TO_ONEContext ctx) {
        return FieldModifier.oneToOne(ctx.ID().getText());
    }

    @Override
    public Object visitMANY_TO_MANY(MANY_TO_MANYContext ctx) {
        return FieldModifier.manyToMany();
    }

}
