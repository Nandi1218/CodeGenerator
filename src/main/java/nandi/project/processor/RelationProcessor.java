package nandi.project.processor;

import nandi.project.model.EntityModel;

/**
 * Placeholder for relation-specific enrichment steps.
 *
 * <p>Relations are currently rendered directly from typed field modifiers,
 * so this processor intentionally does not mutate the model.</p>
 */
public class RelationProcessor implements EntityProcessor {
    @Override
    public void process(EntityModel model) {
        // Intentionally empty.
    }
}
