package com.exhibitionCalendar.model.dao.interfaces;

public interface BaseDAO<Entity, Integer> {

    boolean create(Entity entity);

    Entity retrieve(Integer entityId);

    boolean update(Entity entity);

    boolean delete(Integer entityId);

}
