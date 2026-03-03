package nandi.project.controller.command;

public interface Command {
    void execute(String parameter);
    String getCommandName();
}
