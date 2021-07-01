package dev.goette.daotests;
import dev.goette.daos.UnitDao;
import dev.goette.daos.UnitDaoPostgres;
import dev.goette.entities.Unit;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UnitDaoTests {
    static UnitDao unitDao = new UnitDaoPostgres();
    @Test(priority = 1)
    void getUnitById(){
        Unit unit = unitDao.getUnitById(2);
        Assert.assertEquals(unit.getApartmentNumber(), 101);
    }
    @Test(priority = 2)
    void getAllUnits(){
        List<Unit> units = unitDao.getAllUnits();
        Assert.assertTrue(units.size()>=3);
    }

}
