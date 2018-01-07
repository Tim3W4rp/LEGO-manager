package cz.fi.muni.legomanager.dto;

/**
 * @author Štěpán Granát
 */
public class TokenDTO {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenDTO(String token) {
        this.token = token;
    }

}
