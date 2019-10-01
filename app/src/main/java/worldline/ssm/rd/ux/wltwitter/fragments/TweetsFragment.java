package worldline.ssm.rd.ux.wltwitter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import worldline.ssm.rd.ux.wltwitter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetsFragment extends Fragment {


    public TweetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tweets, container, false);
    }

}
