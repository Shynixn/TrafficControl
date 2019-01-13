package at.jku.trafficparticipants.controller;


import at.jku.trafficparticipants.contract.Street;
import at.jku.trafficparticipants.parser.CityParser;
import at.jku.trafficparticipants.repository.CityRepository;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/city")
public class CityController {
    private CityRepository cityRepository = CityRepository.getInstance();

    @POST
    public void receiveCity(JsonObject request){
        Street street = new CityParser().fromJson(request);
        cityRepository.setStreet(street);
    }

    @GET
    public Response sendCity(){
        return Response.ok(
                new CityParser().toJson(
                        cityRepository.getStreet()),
                MediaType.APPLICATION_JSON)
                .build();
    }
}
