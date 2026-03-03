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

public class GeneratorService {
    private final Configuration config;
    private final STGroup templateGroup;

    public GeneratorService(Configuration config) {
        this.config = config;
        // Betöltjük a sablonfájlt (.stg)
        this.templateGroup = new STGroupFile("spring_entity.stg");
    }

    public void generate(List<EntityModel> entities) {
        for (EntityModel entity : entities) {
            String code = fillTemplate(entity);
            saveToFile(entity.getName(), code);
        }
    }

    private String fillTemplate(EntityModel entity) {
        ST st = templateGroup.getInstanceOf("entityTemplate");
        st.add("entity", entity);
        st.add("pkg", config.getTargetPackage());
        return st.render();
    }

    private void saveToFile(String entityName, String content) {
        try {
            // 1. Kiszámoljuk a mappaszerkezetet a package név alapján
            // pl. "hu.nandi.test" -> "hu/nandi/test"
            String packagePath = config.getTargetPackage().replace('.', '/');
            Path directoryPath = Paths.get(config.getOutputDirectory(), packagePath);

            // 2. Mappák létrehozása, ha nem léteznek
            Files.createDirectories(directoryPath);

            // 3. Fájl mentése
            Path filePath = directoryPath.resolve(entityName + ".java");
            Files.writeString(filePath, content);

            System.out.println("[INFO] Generálva: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("[HIBA] Nem sikerült menteni a fájlt: " + e.getMessage());
        }
    }
}