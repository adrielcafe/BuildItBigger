package cafe.adriel.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cafe.adriel.builditbigger.api.JokerAsyncTask;
import cafe.adriel.builditbigger.api.JokerCallback;

public class ApplicationTest extends ApplicationTestCase<Application> implements JokerCallback {

    private CountDownLatch signal = new CountDownLatch(1);
    private String joke;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void onGetJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public void onError(Exception error) {

    }

    public void testGetJoke() {
        new JokerAsyncTask(this).execute();
        try {
            signal.await(5, TimeUnit.SECONDS);
            assertNotNull("Joke == null", joke);
            assertFalse("Joke == \"\"", joke.isEmpty());
        } catch (InterruptedException e) {
            fail();
        }
    }
}