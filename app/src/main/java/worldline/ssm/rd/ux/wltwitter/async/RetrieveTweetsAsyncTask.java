package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.bdd.DatabaseHelper;
import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

public class RetrieveTweetsAsyncTask extends AsyncTask<String, Void, List<Tweet>> {

    private TweetChangeListener mlistener;

    public RetrieveTweetsAsyncTask(TweetChangeListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    protected List<Tweet> doInBackground(String... login) {
        if((null != login ) && (login.length >0)){
            List<Tweet> myTweets = TwitterHelper.getTweetsOfUser(login[0]);

            if(null != myTweets)
                DatabaseHelper.getInstance().getTwitterDao().insertAll(myTweets);

            return DatabaseHelper.getInstance().getTwitterDao().getAll();
        }
        return DatabaseHelper.getInstance().getTwitterDao().getAll();
    }


    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        super.onPostExecute(tweets);
        if(mlistener!=null && tweets!=null)
            mlistener.onTweetRetrieved(tweets);

    }
}