package labib.com.nudgememvp;

import android.util.Log;


public class Logy {
    public Logy(int i) {
        Log.i("Tag", "" + i);
    }

    public Logy(String s) {
        Log.i("Tag", s);
    }

    public Logy(long aLong) {
        Log.i("Tag", String.valueOf(aLong));
    }

    public Logy(double aLong) {
        Log.i("Tag", String.valueOf(aLong));
    }

    public Logy(boolean b) {
        Log.i("Tag", String.valueOf(b));
    }


}
