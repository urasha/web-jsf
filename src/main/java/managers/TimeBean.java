package managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named
@SessionScoped
public class TimeBean implements Serializable {

    public String getCurrentTime() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
    }
}
