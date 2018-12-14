package gmu.cs.cs477.tanibon.services;
/* Music belongs to Nintendo Animal Crossing series
 */
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

import gmu.cs.cs477.tanibon.R;

public class MusicService extends Service {
    MediaPlayer player;
    int currentPos = 0;

    private final String TAG = "MusicService" ;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate() entered");
        player = MediaPlayer.create(this, R.raw.animal_crossing);
        player.setLooping(true);

    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy() entered");
        if(player != null)
            player.release();
        super.onDestroy();
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startid) {
        Log.i(TAG,"onStartCommand() entered");
        if(currentPos != 0)
            player.seekTo(currentPos);
        player.start();
        return START_NOT_STICKY;
    }

}
