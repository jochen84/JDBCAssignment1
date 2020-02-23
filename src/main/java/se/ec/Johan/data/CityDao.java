package se.ec.Johan.data;

import se.ec.Johan.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {

    Optional<City> findById(int id);
    List<City> findByCode(String cityCode);
    List<City> findByName(String cityName);
    List<City> findAll();
    City add (City city);
    City update (City city);
    int delete (City city);

}
