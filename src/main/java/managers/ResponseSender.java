package managers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class ResponseSender implements Serializable {

    public void sendBadRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response =
                (HttpServletResponse) facesContext.getExternalContext().getResponse();
        try {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            facesContext.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
