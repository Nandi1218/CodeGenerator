import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {

        String input = """
        entity User {
            email: String unique email
            id: Long
        }
        entity Person {
            name: String
            id: Long generated
        }""";


        EntityDSLLexer lexer = new EntityDSLLexer(CharStreams.fromString(input));


        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EntityDSLParser parser = new EntityDSLParser(tokens);


        ParseTree tree = parser.model();

        System.out.println("Felépített fa:");
        System.out.println(tree.toStringTree(parser));
    }
}