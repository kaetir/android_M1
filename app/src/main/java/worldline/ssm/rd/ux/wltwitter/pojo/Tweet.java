package worldline.ssm.rd.ux.wltwitter.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.annotations.SerializedName;

@Entity
public class Tweet {

	@ColumnInfo @SerializedName("created_at")
	public String dateCreated;

	@NonNull @PrimaryKey @SerializedName("id")
	public String id;

	@ColumnInfo @SerializedName("text")
	public String text;

	@ColumnInfo @SerializedName("in_reply_to_status_id")
	public String inReplyToStatusId;

	@ColumnInfo @SerializedName("in_reply_to_user_id")
	public String inReplyToUserId;

	@ColumnInfo @SerializedName("in_reply_to_screen_name")
	public String inReplyToScreenName;

	@Embedded @SerializedName("user")
	public TwitterUser user;

	@Override
	public String toString() {
		return text;
	}
	
	public long getDateCreatedTimestamp(){
		final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
		dateFormat.setLenient(false);
		try {
			final Date created = dateFormat.parse(dateCreated);
			return created.getTime();
		} catch (Exception e) {
			return 0;
		}
	}
	
}