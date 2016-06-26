package cafe.adriel.builditbigger.api;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import cafe.adriel.jokerbackend.jokerApi.JokerApi;

public class JokerAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String API_ROOT_URL = "https://joker-1354.appspot.com/_ah/api/";

    private static JokerApi jokerApi;
    private JokerCallback callback;

    public JokerAsyncTask(JokerCallback callback){
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(jokerApi == null) {
            JokerApi.Builder builder = new JokerApi
                    .Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(API_ROOT_URL);
            jokerApi = builder.build();
        }

        try {
            return jokerApi.getJoke()
                    .execute()
                    .getJoke();
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(result != null){
            callback.onGetJoke(result);
        }
    }

}