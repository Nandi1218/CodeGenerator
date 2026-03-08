package nandi.project.service;

import nandi.project.model.EntityModel;
import nandi.project.model.FieldModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorServiceTest {

    @TempDir
    Path tempDir;

    private Configuration config;
    private GeneratorService generatorService;

    @BeforeEach
    void setUp() {
        config = new Configuration();
        config.setOutputDirectory(tempDir.toString());
        config.setTargetPackage("com.test");
        generatorService = new GeneratorService(config);
    }

    @Test
    void testGenerate_createsEntityFile() throws IOException {
        EntityModel entity = new EntityModel();
        entity.setName("User");

        generatorService.generate(List.of(entity));

        Path entityFile = tempDir.resolve("com/test/model/User.java");
        assertTrue(Files.exists(entityFile));
    }

    @Test
    void testGenerate_createsRepositoryWhenEnabled() throws IOException {
        EntityModel entity = new EntityModel();
        entity.setName("Product");
        config.setGenerateRepository(true);

        generatorService.generate(List.of(entity));

        Path repoFile = tempDir.resolve("com/test/repository/ProductRepository.java");
        assertTrue(Files.exists(repoFile));
    }

    @Test
    void testGenerate_doesNotCreateRepositoryWhenDisabled() throws IOException {
        EntityModel entity = new EntityModel();
        entity.setName("Product");
        config.setGenerateRepository(false);

        generatorService.generate(List.of(entity));

        Path repoFile = tempDir.resolve("com/test/repository/ProductRepository.java");
        assertFalse(Files.exists(repoFile));
    }

    @Test
    void testGenerate_createsServiceWhenEnabled() throws IOException {
        EntityModel entity = new EntityModel();
        entity.setName("Order");
        config.setGenerateService(true);

        generatorService.generate(List.of(entity));

        Path serviceFile = tempDir.resolve("com/test/service/OrderService.java");
        assertTrue(Files.exists(serviceFile));
    }

    @Test
    void testGenerate_handlesMultipleEntities() throws IOException {
        EntityModel user = new EntityModel();
        user.setName("User");
        EntityModel product = new EntityModel();
        product.setName("Product");

        generatorService.generate(List.of(user, product));

        assertTrue(Files.exists(tempDir.resolve("com/test/model/User.java")));
        assertTrue(Files.exists(tempDir.resolve("com/test/model/User.java")));
        assertTrue(Files.exists(tempDir.resolve("com/test/model/Product.java")));
    }
    @Test
    void testTemplateRendering() {
        EntityModel entity = new EntityModel();
        entity.setName("Customer");
        FieldModel field = new FieldModel();
        field.setName("name");
        field.setType("String");
        entity.getFields().add(field);
        entity.validate();
        String rendered = generatorService.render("entityTemplate", entity);
        assertEquals("""
                package com.test.model;
                
                import jakarta.persistence.*;
                import com.test.model.*;
                
                @Entity
                public class Customer {
                
                    @Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    private Integer id;
                
                    private String name;
                
                    public Integer getId() {
                        return id;
                    }
                
                    public void setId(Integer id) {
                        this.id = id;
                    }
                
                    public String getName() {
                        return name;
                    }
                
                    public void setName(String name) {
                        this.name = name;
                    }
                }""".replace("\n", "\r\n"), rendered);
    }
}
