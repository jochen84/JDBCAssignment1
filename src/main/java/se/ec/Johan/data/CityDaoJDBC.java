package se.ec.Johan.data;

import se.ec.Johan.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static se.ec.Johan.data.DatabaseConnection.getConnection;

public class CityDaoJDBC implements CityDao {

    private static final String FIND_BY_ID = "SELECT * FROM city WHERE ID = ?";
    private static final String FIND_BY_CITYCODE = "SELECT * FROM city WHERE CountryCode = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM city WHERE Name = ?";
    private static final String FIND_ALL = "SELECT * FROM city";
    private static final String ADD_CITY = "INSERT INTO city(Name,CountryCode,District,Population)VALUES(?,?,?,?)";
    private static final String UPDATE_CITY = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";
    private static final String DELETE_CITY = "DELETE FROM city WHERE ID = ? AND ID IS NOT NULL";

    private City createCityFromResultSet(ResultSet resultSet) throws SQLException {
        return new City(
        resultSet.getInt("ID"),
        resultSet.getString("Name"),
        resultSet.getString("CountryCode"),
        resultSet.getLong("Population"),
        resultSet.getString("District"));
    }

    public PreparedStatement create_findById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
        statement.setInt(1,id);
        return statement;
    }
    @Override
    public Optional<City> findById(int id) {
        //Creates an empty optional to recieve to City from the Resultset
        Optional<City> optional = Optional.empty();

        try(Connection connection = getConnection();
            //Get the statement from the Method "create_findById"
            PreparedStatement statement = create_findById(connection,id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            //While there is something in the Reslutset, add it to the optional
            while (resultSet.next()) {
                optional = Optional.of(createCityFromResultSet(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return optional;
    }

    public PreparedStatement create_findByCode(Connection connection, String cityCode) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(FIND_BY_CITYCODE);
        statement.setString(1,cityCode);
        return statement;
    }
    @Override
    public List<City> findByCode(String cityCode) {
        List<City> cityList = new ArrayList<>();

        try(Connection connection = getConnection();
        PreparedStatement statement = create_findByCode(connection,cityCode);
        ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()){
                cityList.add(createCityFromResultSet(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cityList;
    }

    public PreparedStatement create_findByName(Connection connection, String cityName) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
        statement.setString(1,cityName);
        return statement;
    }
    @Override
    public List<City> findByName(String cityName) {
        List<City> cityList = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement statement = create_findByName(connection,cityName);
            ResultSet resultSet = statement.executeQuery();
        ){
            while (resultSet.next()){
                cityList.add(createCityFromResultSet(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cityList;
    }

    @Override
    public List<City> findAll() {
        List<City> cityList = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
        ){
            while (resultSet.next()){
                cityList.add(createCityFromResultSet(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cityList;
    }

    @Override
    public City add(City city) {
        ResultSet returnedKeys = null;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_CITY, Statement.RETURN_GENERATED_KEYS);
        ){
            statement.setString(1,city.getName());
            statement.setString(2,city.getCountryCode());
            statement.setString(3,city.getDistrict());
            statement.setLong(4,city.getPopulation());
            statement.execute();
            returnedKeys = statement.getGeneratedKeys();

            while (returnedKeys.next()){
                city = new City(returnedKeys.getInt(1),city.getName(),city.getCountryCode(),city.getPopulation(),city.getDistrict());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public City update(City city) {
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_CITY);
        ){
            statement.setString(1,city.getName());
            statement.setString(2,city.getCountryCode());
            statement.setString(3,city.getDistrict());
            statement.setLong(4,city.getPopulation());
            statement.setInt(5,city.getCityId());
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public int delete(City city) {
        int deletedCitys = 0;

        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_CITY);
        ){
            statement.setInt(1,city.getCityId());
            deletedCitys = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return deletedCitys;
    }
}
