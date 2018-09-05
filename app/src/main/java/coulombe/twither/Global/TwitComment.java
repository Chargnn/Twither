package coulombe.twither.Global;

import java.util.Date;
import java.util.Random;

public class TwitComment {

    public int messageID;
    public String author;
    public String comment;
    public Date date;

    public TwitComment(String author, String comment, int messageID) {
        this.author = author;
        this.comment = comment;
        this.messageID = messageID;
        date = new Date();
    }

    //TODO: initiate comment from db
    /**
     * Initiate comment from db
     */
    public static void getFromDb(){}

    //TODO: upload comment to db
    /**
     * upload comment to db
     * @return
     */
    public boolean uploadToDb(){
        return false;
    }

    //TODO: delete comment from message
    /**
     *  delete comment from message
     * @param message
     * @return
     */
    public boolean removeFromDb(TwitMessage message){
        return false;
    }
}
