package se.ec.Johan;

import se.ec.Johan.data.CityDaoJDBC;
import se.ec.Johan.entity.City;

public class App
{
    public static void main( String[] args ) {

        CityDaoJDBC dao = new CityDaoJDBC();

        //System.out.println(dao.findById(4079));

        City first = new City("LastAddedCity","ZWE",1255345,"Blekinge");
        dao.add(first);
        System.out.println(first);

        //System.out.println(dao.findByCode("nld"));

        //System.out.println(dao.findByName("Kabul"));

        //System.out.println(dao.findAll());

        /*
        City test = dao.findById(1).get();
        System.out.println(test);
        test.setName("Testet");
        dao.update(test);
        System.out.println(dao.findById(1));
         */

        /* ---Rafah is gone...
        City lastCity = dao.findById(4079).get();
        System.out.println(lastCity);
        System.out.println(dao.delete(lastCity));
        */
    }
}
