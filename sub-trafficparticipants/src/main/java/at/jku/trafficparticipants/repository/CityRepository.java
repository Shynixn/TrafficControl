package at.jku.trafficparticipants.repository;

import at.jku.trafficparticipants.contract.Street;

public class CityRepository {
    private static CityRepository instance = null;

    public static CityRepository getInstance(){
        if(instance == null){
            instance = new CityRepository();
        }

        return instance;
    }

    private Street street;

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }
}
