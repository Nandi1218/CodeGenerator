package nandi.project;

import nandi.project.controller.InputController;
import nandi.project.controller.command.LoadCommand;
import nandi.project.controller.command.SetPackageCommand;
import nandi.project.model.EntityModel;
import nandi.project.service.Configuration;
import nandi.project.service.GeneratorService;
import nandi.project.visitor.SpringVisitor;

// ANTLR importok
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Configuration config = new Configuration();
        GeneratorService generatorService = new GeneratorService(config);

        // 2. Az InputController létrehozása
        // A lambda függvény (dslCode -> ...) akkor fut le, ha a bemenet nem parancs
        InputController inputController = new InputController(dslCode -> {
            processAndGenerate(dslCode, config, generatorService);
        });

        // 3. Parancsok regisztrálása
        inputController.registerCommand(new SetPackageCommand(config));
        inputController.registerCommand(new LoadCommand(dsl -> processAndGenerate(dsl, config, generatorService)));
        // Ide jöhet később: inputController.registerCommand(new HelpCommand());

        // 4. Interaktív Shell indítása
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
     * Ez a metódus végzi el a konkrét ANTLR parsolást és hívja meg a generátort.
     */
    private static void processAndGenerate(String dslCode, Configuration config, GeneratorService generator) {
        try {
            // ANTLR folyamat: String -> Lexer -> Tokens -> Parser -> Tree
            CodePointCharStream input = CharStreams.fromString(dslCode);
            nandi.project.EntityDSLLexer lexer = new nandi.project.EntityDSLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            nandi.project.EntityDSLParser parser = new nandi.project.EntityDSLParser(tokens);

            // 'model' a grammarod gyökérszabálya
            ParseTree tree = parser.model();

            // Visitor bejárás
            SpringVisitor visitor = new SpringVisitor();
            List<EntityModel> entities = (List<EntityModel>) visitor.visit(tree);

            // Generálás
            if (entities != null && !entities.isEmpty()) {
                generator.generate(entities);
                System.out.println("[OK] Sikeres generálás!");
            } else {
                System.out.println("[FIGYELEM] Nem találtam feldolgozható entitást.");
            }

        } catch (Exception e) {
            System.err.println("[HIBA] Hiba történt a feldolgozás során: " + e.getMessage());
            e.printStackTrace(); // Fejlesztés közben hasznos
        }
    }
}