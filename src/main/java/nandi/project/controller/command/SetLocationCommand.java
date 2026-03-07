package nandi.project.controller.command;

import nandi.project.service.Configuration;

public class SetLocationCommand implements Command {
    private final Configuration config;

    public SetLocationCommand(Configuration config) {
        this.config = config;
    }

    @Override
    public void execute(String location) {
        config.setOutputDirectory(location);
        System.out.println("Target location set to: " + location);
    }

    @Override
    public String getCommandName() {
        return "location";
    }
}
