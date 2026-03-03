package nandi.project.service;

public class Configuration {
    private String targetPackage = "generated"; // Alapértelmezett érték
    private String outputDirectory = "./output";

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }
}