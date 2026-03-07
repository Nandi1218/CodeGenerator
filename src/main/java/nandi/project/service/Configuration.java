package nandi.project.service;

/**
 * Stores runtime settings used by the code generator.
 */
public class Configuration {
    private String targetPackage = "nandi.project.generated";
    private String outputDirectory = "./output";
    private boolean generateService = true;
    private boolean generateRepository = true;

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
    /**
     * Returns whether service classes should be generated.
     *
     * @return `true` if service generation is enabled; otherwise `false`
     */
    public boolean isGenerateService() {
        return generateService;
    }

    /**
     * Enables or disables service class generation.
     *
     * @param generateService `true` to enable service generation; otherwise `false`
     */
    public void setGenerateService(boolean generateService) {
        this.generateService = generateService;
    }

    /**
     * Returns whether repository classes should be generated.
     *
     * @return `true` if repository generation is enabled; otherwise `false`
     */
    public boolean isGenerateRepository() {
        return generateRepository;
    }

    /**
     * Enables or disables repository class generation.
     *
     * @param generateRepository `true` to enable repository generation; otherwise `false`
     */
    public void setGenerateRepository(boolean generateRepository) {
        this.generateRepository = generateRepository;
    }
}