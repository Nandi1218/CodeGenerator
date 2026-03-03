package nandi.project.controller.command;

                import java.io.IOException;
                import java.nio.file.Files;
                import java.nio.file.Path;
                import java.nio.file.Paths;
                import java.util.function.Consumer;

                /**
                 * Command that loads DSL content from a file and forwards it to a DSL processor.
                 */
                public class LoadCommand implements Command {
                    private final Consumer<String> dslProcessor;

                    /**
                     * Creates a load command with a target DSL processor.
                     *
                     * @param dslProcessor consumer that handles loaded DSL content
                     */
                    public LoadCommand(Consumer<String> dslProcessor) {
                        this.dslProcessor = dslProcessor;
                    }

                    /**
                     * Loads file content from the given path and passes it to the DSL processor.
                     *
                     * @param filePath path to the DSL file
                     */
                    @Override
                    public void execute(String filePath) {
                        if (filePath == null || filePath.isBlank()) {
                            System.err.println("[ERROR] Please provide a file path. (Example: load test.edsl)");
                            return;
                        }

                        try {
                            Path path = Paths.get(filePath);
                            if (!Files.exists(path)) {
                                System.err.println("[ERROR] File not found: " + path.toAbsolutePath());
                                return;
                            }

                            String content = Files.readString(path);
                            System.out.println("[OK] File loaded successfully: " + filePath);

                            dslProcessor.accept(content);

                        } catch (IOException e) {
                            System.err.println("[ERROR] An error occurred while reading the file: " + e.getMessage());
                        }
                    }

                    /**
                     * Returns the command keyword.
                     *
                     * @return the command name: {@code "load"}
                     */
                    @Override
                    public String getCommandName() {
                        return "load";
                    }
                }