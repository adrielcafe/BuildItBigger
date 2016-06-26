package cafe.adriel.joker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

public class Util {

    public static void setupToolbar(AppCompatActivity activity){
        if(activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setTitle(null);
            activity.getSupportActionBar().setElevation(0);
            activity.getSupportActionBar().setBackgroundDrawable(getPrimaryDarkColorDrawable(activity));
        }
    }

    private static ColorDrawable getPrimaryDarkColorDrawable(Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimaryDark, value, true);
        return new ColorDrawable(value.data);
    }

}