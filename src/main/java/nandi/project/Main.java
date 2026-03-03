package nandi.project;

import nandi.project.controller.InputController;
import nandi.project.controller.command.LoadCommand;
import nandi.project.controller.command.SetPackageCommand;
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

        InputController inputController = new InputController(dslCode -> {
            processAndGenerate(dslCode, config, generatorService);
        });

        inputController.registerCommand(new SetPackageCommand(config));
        inputController.registerCommand(new LoadCommand(dsl -> processAndGenerate(dsl, config, generatorService)));

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Spring Boot Entity DSL Generator ===");
        System.out.println("Usable commands: package <name>, load <file>, or write DSL code.");
        System.out.println("Exit: CTRL+C or type: exit");

        while (true) {
            System.out.print("DSL > ");
            if (!scanner.hasNextLine()) break;

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            inputController.handleInput(input);
        }
    }

    /**
     * Parses DSL code using ANTLR and generates Spring Boot entity classes.
     *
     * @param dslCode the DSL code to process
     * @param config configuration with package and output settings
     * @param generator service for generating entity files
     */
    private static void processAndGenerate(String dslCode, Configuration config, GeneratorService generator) {
        try {
            CodePointCharStream input = CharStreams.fromString(dslCode);
            nandi.project.EntityDSLLexer lexer = new nandi.project.EntityDSLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            nandi.project.EntityDSLParser parser = new nandi.project.EntityDSLParser(tokens);

            ParseTree tree = parser.model();

            SpringVisitor visitor = new SpringVisitor();
            List<EntityModel> entities = (List<EntityModel>) visitor.visit(tree);

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