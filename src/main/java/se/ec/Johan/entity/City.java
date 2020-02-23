package se.ec.Johan.entity;

import java.util.Objects;

public class City {

    private int cityId;
    private String name;
    private String countryCode;
    private long population;
    private String district;

    public City(int cityId, String name, String countryCode, long population, String district) {
        this.cityId = cityId;
        this.name = name;
        this.countryCode = countryCode;
        this.population = population;
        this.district = district;
    }

    public City(String name, String countryCode, long population, String district) {
        this(0, name, countryCode, population, district);
    }

    public int getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return cityId == city.cityId &&
                population == city.population &&
                Objects.equals(name, city.name) &&
                Objects.equals(countryCode, city.countryCode) &&
                Objects.equals(district, city.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, name, countryCode, population, district);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("cityId=").append(cityId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", population=").append(population);
        sb.append(", district='").append(district).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
