package eu.juanmasierragarcia.retrotracksoapjava.webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface IUserManagementWS {
    @WebMethod
    String registerUser(String username, String password);

    @WebMethod
    String login(String username, String password);
}
