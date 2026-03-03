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
 * Main entry point for the Spring Boot Entity DSL Generator application.
 * <p>
 * This class provides an interactive command-line shell that allows users to:
 * <ul>
 *   <li>Define entities using a custom DSL syntax</li>
 *   <li>Configure package names for generated code</li>
 *   <li>Load DSL definitions from external files</li>
 *   <li>Generate Spring Boot entity classes with JPA annotations</li>
 * </ul>
 * </p>
 *
 * @author nandi.project
 * @version 1.0
 * @since 2026-03-03
 */
public class Main {

    /**
     * Main method that initializes the DSL generator and starts the interactive shell.
     * <p>
     * The shell supports the following commands:
     * <ul>
     *   <li>{@code package <name>} - Sets the target package for generated entities</li>
     *   <li>{@code load <file>} - Loads and processes DSL from a file</li>
     *   <li>{@code exit} - Exits the application</li>
     *   <li>Direct DSL input - Any other input is treated as DSL code to be parsed</li>
     * </ul>
     * </p>
     *
     * @param args command-line arguments (currently not used)
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
        System.out.println("=== Spring Boot Entity DSL Generátor ===");
        System.out.println("Használható parancsok: package <név>, load <fájl>, vagy írj be DSL kódot.");
        System.out.println("Kilépés: CTRL+C vagy írd be: exit");

        while (true) {
            System.out.print("DSL > ");
            if (!scanner.hasNextLine()) break;

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            inputController.handleInput(input);
        }
    }

    /**
     * Processes DSL code using ANTLR and generates Spring Boot entity classes.
     * <p>
     * This method performs the following steps:
     * <ol>
     *   <li>Creates a lexer from the input DSL string</li>
     *   <li>Tokenizes the input using {@link CommonTokenStream}</li>
     *   <li>Parses the tokens using {@link EntityDSLParser}</li>
     *   <li>Traverses the parse tree with {@link SpringVisitor}</li>
     *   <li>Generates Java source files using {@link GeneratorService}</li>
     * </ol>
     * </p>
     *
     * @param dslCode the DSL code string to process
     * @param config the configuration containing package and output settings
     * @param generator the service responsible for generating entity files
     * @throws RuntimeException if parsing or generation fails
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
                System.out.println("[OK] Sikeres generálás!");
            } else {
                System.out.println("[FIGYELEM] Nem találtam feldolgozható entitást.");
            }

        } catch (Exception e) {
            System.err.println("[HIBA] Hiba történt a feldolgozás során: " + e.getMessage());
            e.printStackTrace();
        }
    }
}