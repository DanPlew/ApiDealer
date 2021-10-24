package daniel.plewinski.apidealer.chucknorisjokes.logic.services.interfaces;

public interface CrudInterface<Entity> {
    Entity create(Entity entity);
    Entity read(Long id);
    Entity update(Long id, Entity entity);
    void delete(Long id);
}
