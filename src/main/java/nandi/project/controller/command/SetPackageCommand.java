package nandi.project.controller.command;

import nandi.project.service.Configuration;

public class SetPackageCommand implements Command {
    private final Configuration config; // Egy közös állapotkezelő

    public SetPackageCommand(Configuration config) { this.config = config; }

    @Override
    public void execute(String pkg) {
        config.setTargetPackage(pkg);
        System.out.println("Target package beállítva: " + pkg);
    }
    @Override
    public String getCommandName() { return "package"; }
}
