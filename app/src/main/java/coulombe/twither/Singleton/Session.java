package coulombe.twither.Singleton;

import coulombe.twither.Global.TwitUser;

public class Session {

    private static TwitUser user;

    private Session(){

    }

    public static TwitUser getInstance(){
        return user;
    }

    public static void setInstance(TwitUser u){
        user = u;
    }

}
