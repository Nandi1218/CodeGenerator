package nandi.project.service;

            import nandi.project.model.EntityModel;
            import org.stringtemplate.v4.ST;
            import org.stringtemplate.v4.STGroup;
            import org.stringtemplate.v4.STGroupFile;

            import java.io.IOException;
            import java.nio.file.Files;
            import java.nio.file.Path;
            import java.nio.file.Paths;
            import java.util.List;

            /**
             * Generates Java entity source files from DSL entity models using StringTemplate templates.
             */
            public class GeneratorService {
                private final Configuration config;
                private final STGroup templateGroup;

                /**
                 * Creates a generator service with shared configuration and loads template definitions.
                 *
                 * @param config runtime configuration for package and output settings
                 */
                public GeneratorService(Configuration config) {
                    this.config = config;
                    this.templateGroup = new STGroupFile("spring_entity.stg");
                }

                /**
                 * Generates source files for all provided entities.
                 *
                 * @param entities entity models to generate
                 */
                public void generate(List<EntityModel> entities) {
                    for (EntityModel entity : entities) {
                        String entityCode = render("entityTemplate",entity);
                        String repoCode = render("repositoryTemplate",entity);

                        saveToFile(entity.getName(), "model",  entityCode);
                        saveToFile(entity.getName()+"Repository", "repository",  repoCode);
                    }
                }

                /**
                 * Renders a single entity model using the configured template.
                 *
                 * @param entity entity model to render
                 * @return rendered Java source code
                 */
                private String render(String template,EntityModel entity) {
                    ST st = templateGroup.getInstanceOf(template);
                    st.add("entity", entity);
                    st.add("pkg", config.getTargetPackage());
                    return st.render();

                }

                /**
                 * Saves generated source code to the configured output directory and package path.
                 *
                 * @param entityName entity class name
                 * @param content rendered Java source code
                 */
                private void saveToFile(String entityName, String subPkg, String content) {
                    try {
                        String packagePath = (config.getTargetPackage() +"." + subPkg).replace('.', '/');
                        Path directoryPath = Paths.get(config.getOutputDirectory(), packagePath);

                        Files.createDirectories(directoryPath);

                        Path filePath = directoryPath.resolve(entityName + ".java");
                        Files.writeString(filePath, content);

                        System.out.println("[INFO] Generated: " + filePath.toAbsolutePath());
                    } catch (IOException e) {
                        System.err.println("[ERROR] Failed to save file: " + e.getMessage());
                    }
                }
            }