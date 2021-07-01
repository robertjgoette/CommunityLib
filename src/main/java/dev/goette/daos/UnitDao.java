package dev.goette.daos;

import dev.goette.entities.Unit;

import java.util.List;

public interface UnitDao {
    //Read
    Unit getUnitById(int id);
    List<Unit> getAllUnits();
}
