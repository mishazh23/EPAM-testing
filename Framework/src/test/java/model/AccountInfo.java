package model;

import java.util.Objects;

public class AccountInfo {
    private String firstSurname;
    private String secondSurname;
    private String street;
    private String house;


    public AccountInfo(String firstSurname, String secondSurname, String street, String house) {
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.street = street;
        this.house = house;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInfo that = (AccountInfo) o;
        return Objects.equals(firstSurname, that.firstSurname) &&
                Objects.equals(secondSurname, that.secondSurname) &&
                Objects.equals(street, that.street) &&
                Objects.equals(house, that.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstSurname, secondSurname, street, house);
    }


}
