package cafe.adriel.builditbigger;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import cafe.adriel.builditbigger.R;

public class MainActivity extends BaseActivity {

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadAd();
    }

    @Override
    public void onClick(View v) {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            showJoke();
        }
    }

    private void loadAd(){
        MobileAds.initialize(getApplicationContext(), getString(R.string.ad_app_id));

        AdView adView = (AdView) findViewById(R.id.ad);
        if(adView != null) {
            adView.loadAd(getAdRequest());
        }

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadInterstitialAd();
                showJoke();
            }
        });
        loadInterstitialAd();
    }

    private void loadInterstitialAd(){
        interstitialAd.loadAd(getAdRequest());
    }

    private AdRequest getAdRequest(){
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    }
}