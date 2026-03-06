package nandi.project.controller.command;

import nandi.project.service.Configuration;

public class ToggleCommand implements Command {
    private final Configuration config;

    public ToggleCommand(Configuration config) {
        this.config = config;
    }

    @Override
    public void execute(String parameter) {
        if (parameter == null || parameter.isBlank()) {
            System.err.println("[ERROR] Please specify what to toggle: Service or Repository. (Example: toggle Service)");
            return;
        }
        String paramLower = parameter.toLowerCase();
        if (paramLower.equals("service")) {
            config.setGenerateService(!config.isGenerateService());
            System.out.println("Service generation toggled to: " + config.isGenerateService());
        }
        else if (paramLower.equals("repository")) {
            config.setGenerateRepository(!config.isGenerateRepository());
            System.out.println("Repository generation toggled to: " + config.isGenerateRepository());
        } else {
            System.err.println("[ERROR] Unknown toggle parameter: " + parameter + ". Use 'Service' or 'Repository'.");
        }
    }

    @Override
    public String getCommandName() {
        return "toggle";
    }
}
