package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

public class WLTwitterLoginActivity extends Activity implements View.OnClickListener {
    private EditText mLogin;
    private EditText mPasswd;

    public class Login{
        public static final String EXTRA_LOGIN =  "extraLogin";
    }

    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(mLogin.getText())){
            Toast.makeText(this, "Empty login", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mPasswd.getText())){
            Toast.makeText(this, "Empty password", Toast.LENGTH_SHORT).show();
            return;
        }
        PreferenceUtils.setLogin(mLogin.getText().toString());

        startMainActivity(mLogin.getText().toString());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final String log = PreferenceUtils.getLogin();
        if(!TextUtils.isEmpty(log))
            startMainActivity(log);

        mLogin  = (EditText) findViewById(R.id.LoginEditText);
        mPasswd = (EditText) findViewById(R.id.PasswordEditText);

        findViewById(R.id.Login).setOnClickListener(this);
    }


    private void startMainActivity(String username){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putString(Login.EXTRA_LOGIN, username);
        intent.putExtras(extras);
        startActivity(intent);
    }

}
