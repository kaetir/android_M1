package worldline.ssm.rd.ux.wltwitter.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import worldline.ssm.rd.ux.wltwitter.Interface.TweetChangeListner;
import worldline.ssm.rd.ux.wltwitter.Interface.TweetListener;
import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;


public class TweetsFragment extends Fragment implements TweetChangeListner, AdapterView.OnItemClickListener {

    private RetrieveTweetsAsyncTask mRetrieveTweets;
    private ListView mListView;
    private TweetListener mListener;


    public TweetsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (TweetListener) activity;

    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        mListView.setAdapter(new ArrayAdapter<Tweet>(
                getActivity(), android.R.layout.simple_list_item_1, tweets
        ));
    }

    @Override
    public void onStart() {
        super.onStart();
        final String login = PreferenceUtils.getLogin();
        if(TextUtils.isEmpty(login)){

            mRetrieveTweets = new RetrieveTweetsAsyncTask(this);
            mRetrieveTweets.execute(login);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_tweets, container,false);

        if(container != null){
            mListView = ret.findViewById(R.id.tweetsListView);
            mListView.setAdapter(new ArrayAdapter<Tweet>(
                    getActivity(), android.R.layout.simple_list_item_1, new ArrayList<Tweet>()
            ));

        }

        mListView.setOnItemClickListener(this);

        return ret;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tweet t = (Tweet) mListView.getItemAtPosition(position);
        if(mListener != null) mListener.onViewTweet(t);

    }
}
