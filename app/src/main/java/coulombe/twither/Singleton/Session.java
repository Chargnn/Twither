package coulombe.twither.Singleton;

import org.coulombe.User;

public class Session {

    private static User user;

    private Session(){

    }

    public static User getInstance(){
        return user;
    }

    public static void setInstance(User u){
        user = u;
    }

}
