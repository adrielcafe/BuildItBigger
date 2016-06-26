package cafe.adriel.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public final class Util {

    private static ConnectivityManager connectivityManager;

    private Util(){

    }

    public static boolean isConnected(final Activity activity, boolean showToast){
        if(connectivityManager == null) {
            connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = (info != null && info.isConnected());
        if(!isConnected && showToast){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, R.string.connect_internet, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return isConnected;
    }
}