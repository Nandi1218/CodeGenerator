import generated.antlr4.EntityDSLLexer;
import generated.antlr4.EntityDSLParser;
import model.EntityModel;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stringtemplate.v4.*;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*String input = """
                entity User {
                        id: Long primary generated;
                        email: String unique length(2,50); }
                entity Product { id: Long primary generated; name: String; price: Double optional; }
                entity Order {
                        id: Long primary generated;
                        user: User;
                        products: Product[]; }
                """;
        */
        Scanner sc = new Scanner(System.in);
        while(true){
            StringBuilder sb = new StringBuilder();
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if(line.equalsIgnoreCase("Exit")) System.exit(0);
                if(line.isEmpty()) break;
                sb.append(line).append("\n");
            }
            String input = sb.toString();
            EntityDSLLexer lexer = new EntityDSLLexer(CharStreams.fromString(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EntityDSLParser parser = new EntityDSLParser(tokens);
            ParseTree tree = parser.model();


            SpringVisitor visitor = new SpringVisitor();

            List<EntityModel> entities = (List<EntityModel>) visitor.visit(tree);

            //printToConsole(entities);
            File file = new File("src/main/java/resources/spring_entity.stg");
            if (!file.exists()) {
                System.err.println("Template file not found: " + file.getAbsolutePath());
                return;
            }
            STGroup group = new STGroupFile(file.getAbsolutePath());

            for(var entity : entities){
                ST st = group.getInstanceOf("entityTemplate");
                st.add("entity", entity);
                String generatedCode = st.render();
                System.out.println(generatedCode);
            }
        }


    }

    private static void printToConsole(List<EntityModel> entities) {
        for (EntityModel entity : entities) {
            System.out.println("Entitás neve: " + entity.getName());
            entity.getFields().forEach(f -> {
                System.out.println("\t - Mező: " + f.getName() + ": " + f.getType());
                System.out.println("\t\t   Annotációk: " + f.getModifiers());
            });
        }
    }
}