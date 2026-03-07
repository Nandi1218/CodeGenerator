package nandi.project.controller.command;

import nandi.project.service.Configuration;

public class ListCommand implements Command{
    private final Configuration config;

    public ListCommand(Configuration config) {
        this.config = config;
    }

    @Override
    public void execute(String parameter) {
        System.out.println("=".repeat(40));
        System.out.println(listCurrentSettings());
    }
    private String listCurrentSettings() {
        return ("""
                Current package:\t\t.%s
                Current location:\t\t%s
                Repository generation:\t%s
                Service generation\t\t%s""").formatted(config.getTargetPackage(),
                config.getOutputDirectory(),
                config.isGenerateRepository() ? "True" : "False",
                config.isGenerateService() ? "True" : "False");
    }

    @Override
    public String getCommandName() {
        return "list";
    }
}
