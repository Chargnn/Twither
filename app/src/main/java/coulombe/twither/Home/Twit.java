package coulombe.twither.Home;

import java.util.Date;
import java.util.Random;

public class Twit {

    public String author;
    public String message;
    public Date date;

    private Random rand = new Random();

    public Twit(String author, String message){
        this.author = author;
        this.message = message;
        date = new Date();
        date.setSeconds(rand.nextInt(10000 - 1) + 1);
    }
}
