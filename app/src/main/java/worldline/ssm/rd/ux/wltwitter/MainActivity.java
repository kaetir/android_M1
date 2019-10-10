package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.Interface.TweetListener;
import worldline.ssm.rd.ux.wltwitter.fragments.TweetsFragment;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;


public class MainActivity extends Activity implements TweetListener {


    @Override
    public void onRetweet(Tweet t) {

    }

    @Override
    public void onViewTweet(Tweet t) {
        Toast.makeText(this, "onTextView"+ t.text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.login);
        setContentView(R.layout.activity_main);


        final Intent intent = getIntent();
        if(intent != null){
            String login = intent.getExtras().getString(WLTwitterLoginActivity.Login.EXTRA_LOGIN);
            //Toast.makeText(this, "on a un intent", Toast.LENGTH_LONG);
            getActionBar().setSubtitle(login);


        }

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container, TweetsFragment.newInstance("")).commit();

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionLogout) {
            PreferenceUtils.setLogin(null);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
