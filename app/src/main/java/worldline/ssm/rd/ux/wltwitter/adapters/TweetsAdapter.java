package worldline.ssm.rd.ux.wltwitter.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.MainActivity;
import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

public class TweetsAdapter extends BaseAdapter {

    List<Tweet> mTweets;
    private final LayoutInflater mInflater;

    public TweetsAdapter(List<Tweet> mTweets) {
        this.mTweets = mTweets;
        this.mInflater = LayoutInflater.from(WLTwitterApplication.getContext());
    }



    @Override
    public int getCount() {
        return (null != mTweets) ? mTweets.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (null != mTweets) ? mTweets.get(position): 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(null == convertView){
            // inflate the view
            convertView = mInflater.inflate(R.layout.tweet_layout, null);

            // Instantiate the viewHolder
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        // Get current Item
        final Tweet tweet = (Tweet) getItem(position);

        // Set the username, to do so, retrieve the corresponding TextView
        final TextView userName = (TextView) convertView.findViewById(R.id.username);
        if(userName != null && tweet.user != null && tweet.user.name != null)
            userName.setText(tweet.user.name);

        // Set the alias, to do so, retrieve the corresponding TextView
        final TextView userAlias = (TextView) convertView.findViewById(R.id.alias);
        if(userAlias != null)
            userAlias.setText("@" + tweet.user.screenName);

        // Set the text of the tweet, to do so, retrieve the correspondting TextView
        final TextView text = (TextView) convertView.findViewById(R.id.tweetContent);
        if(text != null)
            text.setText(tweet.text);



        return convertView;
    }
    private class ViewHolder{
        public ImageView image;
        public TextView name;
        public TextView alias;
        public TextView text;
        public Button button;

        public ViewHolder(View view){
            image = (ImageView) view.findViewById(R.id.tweetListItemImageView);
            name = (TextView) view.findViewById(R.id.username);
            alias = (TextView) view.findViewById(R.id.alias);
            text = (TextView) view.findViewById(R.id.tweetContent);
            button = (Button) view.findViewById(R.id.tweetListItemButton);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "RT   :   " + text.getText().toString(), Toast.LENGTH_LONG).show();

                }
            });
        }

    }
}

