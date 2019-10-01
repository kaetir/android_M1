package worldline.ssm.rd.ux.wltwitter.Interface;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

public interface TweetListener {
    public void onRetweet(Tweet t);
    public  void onViewTweet(Tweet t);
}
