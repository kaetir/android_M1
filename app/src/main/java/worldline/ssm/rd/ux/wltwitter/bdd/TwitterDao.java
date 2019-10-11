package worldline.ssm.rd.ux.wltwitter.bdd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

@Dao
public interface TwitterDao {
    @Query("SELECT * FROM Tweet")
    public List<Tweet> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Tweet> tweets);

    @Delete
    public void delete(Tweet tweet);
}
