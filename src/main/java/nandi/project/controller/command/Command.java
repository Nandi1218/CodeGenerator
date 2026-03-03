package nandi.project.controller.command;

/**
 * Defines a command that can be executed from the input controller.
 */
public interface Command {

    /**
     * Executes the command with an optional parameter.
     *
     * @param parameter command argument, may be empty
     */
    void execute(String parameter);

    /**
     * Returns the command keyword used to invoke this command.
     *
     * @return command name (for example, {@code "load"} or {@code "package"})
     */
    String getCommandName();
}