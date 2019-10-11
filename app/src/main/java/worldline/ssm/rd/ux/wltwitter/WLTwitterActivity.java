package worldline.ssm.rd.ux.wltwitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.ui.fragments.TweetFragment;
import worldline.ssm.rd.ux.wltwitter.ui.fragments.TweetsFragment;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class WLTwitterActivity extends AppCompatActivity implements TweetListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wltwitter);

        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                final String  login = extras.getString(Constants.Login.EXTRA_LOGIN);
                getSupportActionBar().setSubtitle(login);

            }
        }
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,new TweetsFragment()).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wltwitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //action de logout
        if (id == R.id.actionLogout) {
            PreferenceUtils.setLogin(null);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRetweet(Tweet tweet) {
        Toast.makeText(this,"OnRetweet"+tweet.text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewTweet(Tweet tweet) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        final TweetFragment fragment = new TweetFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(Constants.Tweet.EXTRA_TEXT, tweet.text);
        bundle.putString(Constants.Tweet.EXTRA_IMAGEURL, tweet.user.profileImageUrl);
        bundle.putString(Constants.Tweet.EXTRA_NAME, tweet.user.name);
        bundle.putString(Constants.Tweet.EXTRA_ALIAS, tweet.user.screenName);
        fragment.setArguments(bundle);

        transaction.add(R.id.container, fragment);
        transaction.setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left);
        transaction.addToBackStack(null).commit();
    }
}
