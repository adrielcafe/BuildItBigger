package cafe.adriel.builditbigger.api;

public interface JokerCallback {

    void onGetJoke(String joke);

    void onError(Exception error);
}