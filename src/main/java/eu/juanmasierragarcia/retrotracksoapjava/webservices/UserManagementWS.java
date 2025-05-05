package eu.juanmasierragarcia.retrotracksoapjava.webservices;

import eu.juanmasierragarcia.retrotracksoapjava.model.User;
import eu.juanmasierragarcia.retrotracksoapjava.repository.UserManagementRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.jws.WebService;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@WebService(endpointInterface = "eu.juanmasierragarcia.retrotracksoapjava.webservices.IUserManagementWS")
public class UserManagementWS implements IUserManagementWS {
    private final UserManagementRepository userManagementRepository = new UserManagementRepository();
    private static final Key key = Keys.hmacShaKeyFor("ClaveSecretaMuySeguraDe32Caracteres!!".getBytes(StandardCharsets.UTF_8));

    @Override
    public String registerUser(String username, String password) {
        if (userManagementRepository.userExists(username)) {
            return "El usuario ya existe";
        }
        userManagementRepository.saveUser(username, password);
        return "Usuario registrado correctamente";
    }

    @Override
    public String login(String username, String password) {
        User user = userManagementRepository.findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 hora
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        }
        return "Usuario o contraseña incorrectos";
    }
}
