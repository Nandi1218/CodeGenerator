package nandi.project.controller.command;

public class HelpCommand implements Command{
    @Override
    public void execute(String parameter) {
        System.out.println("""
            Available commands:
            - list: Display current configuration settings.
            - load <filePath>: Load DSL content from a file. Example: load test.edsl
            - package <packageName>: Set the target package for generated code. Example: package com.example.generated
            - location <directoryPath>: Set the output directory for generated code. Example: location ./output
            - toggle Service: Toggle generation of service classes.
            - toggle Repository: Toggle generation of repository classes.
                DSL Input:
                entity [Name] {
                    [pName]: [pType] [[modifier] [modifier] …]
                    ...
                }"""
        );
    }

    @Override
    public String getCommandName() {
        return "help";
    }
}
