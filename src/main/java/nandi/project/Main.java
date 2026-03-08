package nandi.project;

import nandi.project.controller.InputController;
import nandi.project.controller.command.*;
import nandi.project.model.EntityModel;
import nandi.project.service.Configuration;
import nandi.project.service.GeneratorService;
import nandi.project.visitor.SpringVisitor;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


import java.util.List;
import java.util.Scanner;

/**
 * Interactive command-line shell for generating Spring Boot entities from DSL code.
 * Supports custom DSL syntax, package configuration, and file loading.
 *
 * @author nandi.project
 * @version 0.1.1
 */
public class Main {

    /**
     * Starts the interactive DSL shell.
     * Commands: {@code package <name>}, {@code load <file>}, {@code exit}, or direct DSL input.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        Configuration config = new Configuration();
        GeneratorService generatorService = new GeneratorService(config);

        InputController inputController = new InputController(dslCode -> processAndGenerate(dslCode, generatorService));

        setCommands(inputController, config, generatorService);

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                           ================== Spring Boot Entity DSL Generator ==================
                           Commands: list package <name>, load <file>, location <location>, toggle <Service|Repository>
                           Or type DSL directly to generate code. Type 'exit' to quit. Type 'help' for more info.""");


        while (true) {
            System.out.print("DSL > ");
            if (!scanner.hasNextLine()) break;

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            if (input.toLowerCase().startsWith("entity"))
                input = dslInput(input, scanner);


            inputController.handleInput(input);
        }
    }
    /**
     * Registers available commands with the input controller.
     *
     * @param inputController controller to register commands with
     * @param config shared configuration for commands
     * @param generatorService service used by commands that trigger generation
     */
    private static void setCommands(InputController inputController, Configuration config, GeneratorService generatorService) {
        inputController.registerCommand(new SetPackageCommand(config));
        inputController.registerCommand(new LoadCommand(dsl -> processAndGenerate(dsl, generatorService)));
        inputController.registerCommand(new ToggleCommand(config));
        inputController.registerCommand(new HelpCommand());
        inputController.registerCommand(new SetLocationCommand(config));
        inputController.registerCommand(new ListCommand(config));
    }

    /**
     * Reads multi-line DSL input until an empty line or closing brace is encountered.
     *
     * @param input initial line of DSL input
     * @param scanner scanner to read additional lines
     * @return complete DSL code as a single string
     */
    private static String dslInput(String input, Scanner scanner) {
        StringBuilder sb = new StringBuilder(input);
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            sb.append("\n").append(line);
            if (line.trim().endsWith("}")) break;
        }
        input = sb.toString();
        return input;
    }

    /**
     * Parses DSL code using ANTLR and generates Spring Boot entity classes.
     *
     * @param dslCode the DSL code to process
     * @param generator service for generating entity files
     */
    private static void processAndGenerate(String dslCode, GeneratorService generator) {
        try {
            CodePointCharStream input = CharStreams.fromString(dslCode);
            nandi.project.EntityDSLLexer lexer = new nandi.project.EntityDSLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            nandi.project.EntityDSLParser parser = new nandi.project.EntityDSLParser(tokens);

            ParseTree tree = parser.model();

            SpringVisitor visitor = new SpringVisitor();
            @SuppressWarnings("unchecked") List<EntityModel> entities = (List<EntityModel>) visitor.visit(tree);

            if (entities != null && !entities.isEmpty()) {
                generator.generate(entities);
                System.out.println("[OK] Successful generation!");
            } else {
                System.out.println("[WARNING] Processable entity not found.");
            }

        } catch (Exception e) {
            System.err.println("[ERROR] Error encountered during the processing: " + e.getMessage());
        }
    }
}