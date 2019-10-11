package worldline.ssm.rd.ux.wltwitter.bdd;

import androidx.room.Room;

import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;

public class DatabaseHelper {
    private static final DatabaseHelper ourInstance = new DatabaseHelper();
    private final TwiterDatabase db;

    public static DatabaseHelper getInstance() {
        return ourInstance;
    }

    public TwitterDao getTwitterDao(){ return db.twitterDao();}

    private DatabaseHelper() {
        db = Room.databaseBuilder(WLTwitterApplication.getContext(),
                                TwiterDatabase.class,"tweet_database.db").build();
    }
}
