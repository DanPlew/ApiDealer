package daniel.plewinski.apidealer.chucknorisjokes.logic.mappers.interfaces;

import java.util.List;

public interface MappingInterface<Entity, Dto> {
    Dto fromEntityToDto(Entity entity);
    List<Dto> fromEntityListToDtoList(List<Entity> entityList);
    Entity fromDtoToEntity(Dto dto);
    List<Entity> fromDtoListToEntityList(List<Dto> dtoList);
}
