package cafe.adriel.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import cafe.adriel.builditbigger.api.JokerAsyncTask;
import cafe.adriel.builditbigger.api.JokerCallback;
import cafe.adriel.joker.JokeActivity;

public abstract class BaseActivity extends AppCompatActivity implements JokerCallback, View.OnClickListener {

    private Button showJokeView;
    private ProgressBar progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        showJokeView = (Button) findViewById(R.id.show_joke);
        progressView = (ProgressBar) findViewById(R.id.progress);

        showJokeView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showJoke();
    }

    @Override
    public void onGetJoke(String joke){
        Intent i = new Intent(this, JokeActivity.class);
        i.putExtra(JokeActivity.EXTRA_JOKE, joke);
        startActivity(i);
        setLoading(false);
    }

    @Override
    public void onError(final Exception error){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    protected void showJoke(){
        if(Util.isConnected(this, true)) {
            setLoading(true);
            new JokerAsyncTask(this).execute();
        }
    }

    private void setLoading(boolean loading){
        if(loading){
            showJokeView.setEnabled(false);
            progressView.setVisibility(View.VISIBLE);
        } else {
            showJokeView.setEnabled(true);
            progressView.setVisibility(View.GONE);
        }
    }

}