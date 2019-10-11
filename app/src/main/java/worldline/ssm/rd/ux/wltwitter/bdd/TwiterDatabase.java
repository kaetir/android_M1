package worldline.ssm.rd.ux.wltwitter.bdd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

@Database(entities = {Tweet.class}, version = 1)
public abstract class TwiterDatabase extends RoomDatabase {
    public abstract TwitterDao twitterDao();
}
