package coulombe.twither.Global;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TwitMessage {

    public String author;
    public String message;
    public Date date;
    public List<Comment> comments = new ArrayList<Comment>();

    private Random rand = new Random();

    public TwitMessage(String author, String message) {
        this.author = author;
        this.message = message;
        date = new Date();
        date.setSeconds(rand.nextInt(100000 - 1) + 1);
    }

    //TODO: initiate message from db
    /**
     * Initiate message from db
     */
    public static void getFromDb(){}

    //TODO: upload message to db
    /**
     * upload message to db
     * @return
     */
    public boolean uploadToDb(){
        return false;
    }

    //TODO: remove message from db
    /**
     * remove message from db
     * @return
     */
    public boolean removeFromDb(){
        return false;
    }
}
