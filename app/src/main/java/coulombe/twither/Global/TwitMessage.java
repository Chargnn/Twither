package coulombe.twither.Global;

import java.util.Date;
import java.util.Random;

public class TwitMessage {

    public String author;
    public String message;
    public Date date;

    private Random rand = new Random();

    public TwitMessage(String author, String message) {
        this.author = author;
        this.message = message;
        date = new Date();
        date.setSeconds(rand.nextInt(100000 - 1) + 1);
    }
}
