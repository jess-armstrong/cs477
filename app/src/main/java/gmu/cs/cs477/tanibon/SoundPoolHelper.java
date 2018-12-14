package gmu.cs.cs477.tanibon;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
/* Sounds belong to Nintendo Pokemon series
 */
import java.util.HashMap;
/*followed SoundPool help at https://stackoverflow.com/questions/13883883/playing-short-wav-files-android?rq=1 */
public class SoundPoolHelper {
    SoundPool player;
    HashMap soundsMap = new HashMap();

    public  SoundPoolHelper(Context context){
        player = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundsMap.put(R.raw.primarina, player.load(context, R.raw.primarina, 1));

    }

    public void play(int sound){
        int id = (Integer) soundsMap.get(sound);
        player.play(id, .99f, .99f, 0, 0, 1);
    }

    public void release(){
        player.release();
    }
}
