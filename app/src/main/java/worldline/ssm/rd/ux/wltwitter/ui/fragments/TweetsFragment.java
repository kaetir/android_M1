package worldline.ssm.rd.ux.wltwitter.ui.fragments;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.adapters.TweetsAdapter;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetsFragment extends Fragment implements TweetChangeListener, AdapterView.OnItemClickListener {


    private RetrieveTweetsAsyncTask mRetrieveTweetsAsyncTask;
    private TweetListener mTweetListener;
    private ListView mListView;

    public TweetsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        final String login = PreferenceUtils.getLogin();
        if(!TextUtils.isEmpty(login)){
         mRetrieveTweetsAsyncTask = new RetrieveTweetsAsyncTask(this);
         mRetrieveTweetsAsyncTask.execute(login);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_tweets, container, false);

        mListView= (ListView) RootView.findViewById(R.id.tweetsListView);
        // Set adapter with no elements to let the ListView display the empty view
        mListView.setAdapter(
                new ArrayAdapter<Tweet>(getActivity(),
                        android.R.layout.simple_list_item_1, new ArrayList<Tweet>()));

        mListView.setOnItemClickListener(this);
        return RootView;
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        // Set the adapter
        final TweetsAdapter adapter = new TweetsAdapter(tweets);
        adapter.setTweetListener(mTweetListener);
        mListView.setAdapter(adapter);
        // Set our asynctask to null
        mRetrieveTweetsAsyncTask= null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof TweetListener)
        { mTweetListener = (TweetListener) activity; }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //ListView lView = (ListView) view;
        Tweet tweet = (Tweet)mListView.getItemAtPosition(i);
        if(mTweetListener!= null) mTweetListener.onViewTweet(tweet);
    }

}
