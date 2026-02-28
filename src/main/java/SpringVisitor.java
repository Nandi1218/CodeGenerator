import generated.antlr4.EntityDSLBaseVisitor;
import model.EntityModel;
import model.FieldModel;
import generated.antlr4.EntityDSLParser.*;


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
    public Object visitArrayType(ArrayTypeContext ctx) {
        FieldModel field = new FieldModel();
        field.setType(ctx.ID().getText()+"[]");
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
    public Object visitUNIQUE(UNIQUEContext ctx) {
        return "@Column(unique = true)";
    }

    @Override
    public Object visitMIN(MINContext ctx) {
        String value = ctx.INT().getText();
        return "@Min(" + value + ")";
    }

    @Override
    public Object visitLENGTH(LENGTHContext ctx) {
        String min = ctx.INT(0).getText();
        String max = ctx.INT(1).getText();
        return "@Size(min = " + min + ", max = " + max + ")";
    }
}
