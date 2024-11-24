package components;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class NavigationBean {

    public String goToMainPage() {
        return "main";
    }
}
