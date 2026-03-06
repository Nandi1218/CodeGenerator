package nandi.project.controller;

        import nandi.project.controller.command.Command;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.function.Consumer;

        /**
         * Handles user input by dispatching known commands or forwarding DSL text.
         */
        public class InputController {
            private final Map<String, Command> commands = new HashMap<>();
            private final Consumer<String> dslProcessor;

            /**
             * Creates an input controller with a DSL processor callback.
             *
             * @param dslProcessor callback used when input is not a registered command
             */
            public InputController(Consumer<String> dslProcessor) {
                this.dslProcessor = dslProcessor;
            }

            /**
             * Registers a command by its command name.
             *
             * @param cmd command to register
             */
            public void registerCommand(Command cmd) {
                commands.put(cmd.getCommandName(), cmd);
            }

            /**
             * Processes a line of input.
             * Executes a matching command with its parameter, or forwards the text as DSL.
             *
             * @param input raw user input
             */
            public void handleInput(String input) {
                String trimmed = input.trim();
                if (trimmed.isEmpty()) return;

                String[] parts = trimmed.split(" ", 2);
                String firstWord = parts[0].toLowerCase();
                String param = parts.length > 1 ? parts[1] : "";

                if (commands.containsKey(firstWord)) {
                    commands.get(firstWord).execute(param);
                } else {

                    dslProcessor.accept(trimmed);
                }
            }
        }