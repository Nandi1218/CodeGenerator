package nandi.project.controller.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class LoadCommand implements Command {
    // A Consumer egy olyan funkcionális interfész, ami kap egy Stringet (a fájl tartalmát)
    // és lefuttat rajta egy műveletet (a parsolást/generálást).
    private final Consumer<String> dslProcessor;

    public LoadCommand(Consumer<String> dslProcessor) {
        this.dslProcessor = dslProcessor;
    }

    @Override
    public void execute(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            System.err.println("[HIBA] Kérlek, add meg a fájl elérési útját! (Pl: load teszt.edsl)");
            return;
        }

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                System.err.println("[HIBA] A fájl nem található: " + path.toAbsolutePath());
                return;
            }

            // Fájl tartalmának beolvasása
            String content = Files.readString(path);
            System.out.println("[OK] Fájl sikeresen beolvasva: " + filePath);

            // Átadjuk a tartalmát a processornak (ami a Main-ben van definiálva)
            dslProcessor.accept(content);

        } catch (IOException e) {
            System.err.println("[HIBA] Hiba történt a fájl olvasása közben: " + e.getMessage());
        }
    }

    @Override
    public String getCommandName() {
        return "load";
    }
}