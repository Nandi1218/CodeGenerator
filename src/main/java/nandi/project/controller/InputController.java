package nandi.project.controller;

import nandi.project.controller.command.Command;

import java.util.*;
import java.util.function.Consumer;

public class InputController {
    private final Map<String, Command> commands = new HashMap<>();
    private final Consumer<String> dslProcessor; // Ez kezeli, ha nem parancsot írnak be

    public InputController(Consumer<String> dslProcessor) {
        this.dslProcessor = dslProcessor;
    }

    public void registerCommand(Command cmd) {
        commands.put(cmd.getCommandName(), cmd);
    }

    public void handleInput(String input) {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) return;

        // Megnézzük, parancs-e (pl. "load path/to/file")
        String[] parts = trimmed.split(" ", 2);
        String firstWord = parts[0].toLowerCase();
        String param = parts.length > 1 ? parts[1] : "";

        if (commands.containsKey(firstWord)) {
            commands.get(firstWord).execute(param);
        } else {
            // Ha nem ismert parancs, akkor ez tiszta DSL kód
            dslProcessor.accept(trimmed);
        }
    }
}