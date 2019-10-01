package worldline.ssm.rd.ux.wltwitter.Interface;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

public interface TweetChangeListner {

    public void  onTweetRetrieved(List<Tweet> tweets);


}
