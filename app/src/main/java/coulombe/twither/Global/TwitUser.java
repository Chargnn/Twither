package coulombe.twither.Global;

import java.util.Date;

public class TwitUser {

    public String nickname;
    public String password;
    public String email;
    public Date signDate;
    public String bio;

    public TwitUser(){
        signDate = new Date();
    }

    //TODO: initiate user from db
    /**
     * Initiate user using db info
     */
    public static void getFromDb(){}

    //TODO: create user in db
    /**
     * create user in db using parameters
     * @param nickname
     * @param password
     * @param email
     * @return
     */
    public boolean createUser(String nickname, String password, String email){
        return false;
    }

    //TODO: get infos from user in db
    /**
     * Get infos from user in db
     */
    public void getInfo(){}

    //TODO: get twitMessages from user in db
    /**
     * Get twits from user in db
     */
    public void getTwitMessages(){}

    // TODO: update info user
    /**
     * Update infos of user from parameters in db
     * @param nickname
     * @param password
     * @param email
     * @param bio
     */
    public void updateInfo(String nickname, String password, String email, String bio){}
    
}
