package cafe.adriel.joker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Util.setupToolbar(this);

        if(getIntent().hasExtra(EXTRA_JOKE)){
            String joke = getIntent().getStringExtra(EXTRA_JOKE);
            TextView jokeView = (TextView) findViewById(R.id.joke);
            jokeView.setText(joke);
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}