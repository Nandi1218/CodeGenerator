package nandi.project.controller.command;

import nandi.project.service.Configuration;

/**
 * Command that updates the target package used for code generation.
 */
public class SetPackageCommand implements Command {
    private final Configuration config;

    /**
     * Creates the command with shared generator configuration.
     *
     * @param config shared application configuration
     */
    public SetPackageCommand(Configuration config) {
        this.config = config;
    }

    /**
     * Sets the target package name.
     *
     * @param pkg package name provided by the user
     */
    @Override
    public void execute(String pkg) {
        config.setTargetPackage(pkg.replace("/", "."));
        System.out.println("Target package set to: " + pkg);
    }

    /**
     * Returns the command keyword.
     *
     * @return command name: {@code "package"}
     */
    @Override
    public String getCommandName() {
        return "package";
    }
}