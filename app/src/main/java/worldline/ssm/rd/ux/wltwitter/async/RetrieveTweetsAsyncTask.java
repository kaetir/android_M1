package worldline.ssm.rd.ux.wltwitter.async;



import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.Interface.TweetChangeListner;
import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;


public class RetrieveTweetsAsyncTask extends AsyncTask<String, Void, List<Tweet>> {

    private TweetChangeListner mListener;

    public RetrieveTweetsAsyncTask(TweetChangeListner listner) {
        mListener = listner;
    }

    @Override
    protected List<Tweet> doInBackground(String... params) {

        if((params != null) && (params.length > 0)){
            return TwitterHelper.getTweetsOfUser(params[0]);
        }

        return null;

    }


    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        super.onPostExecute(tweets);

        if((tweets != null) && (mListener != null)){
            for( Tweet t : tweets){
                Log.i("Tweet", t.text);
            }

            mListener.onTweetRetrieved(tweets);
        }
    }
}

