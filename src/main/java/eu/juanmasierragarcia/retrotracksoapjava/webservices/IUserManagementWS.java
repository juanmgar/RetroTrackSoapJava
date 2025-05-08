package eu.juanmasierragarcia.retrotracksoapjava.webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IUserManagementWS {
    @WebMethod
    String registerUser(String username, String password);

    @WebMethod
    String login(String username, String password);

    @WebMethod
    List<String> listUsers();
}
