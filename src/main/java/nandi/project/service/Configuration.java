package nandi.project.service;

/**
 * Stores runtime settings used by the code generator.
 */
public class Configuration {
    private String targetPackage = "generated";
    private String outputDirectory = "./output";

    /**
     * Returns the target Java package for generated classes.
     *
     * @return target package name
     */
    public String getTargetPackage() {
        return targetPackage;
    }

    /**
     * Sets the target Java package for generated classes.
     *
     * @param targetPackage package name to use
     */
    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    /**
     * Returns the output directory for generated files.
     *
     * @return output directory path
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * Sets the output directory for generated files.
     *
     * @param outputDirectory output directory path
     */
    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }
}