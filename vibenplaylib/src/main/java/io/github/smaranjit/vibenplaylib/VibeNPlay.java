package io.github.smaranjit.vibenplaylib;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * controls alarm and vibration
 */
public class VibeNPlay {
    private static MediaPlayer mMediaPlayer;
    private static Context mCtx;
    private static boolean mIsVibrationOn = true;
    private static boolean mIsSoundOn = true;
    private static long mVibratorFirstBeatLength = 300;
    private static long mVibratorRestLength = 250;
    private static long mVibratorSecondBeatLength =720;
    private static int mVibratorLoopCount = 1000;
    private static boolean mIsSoundLoop = true;

    /**
     * starts playing alarm and vibration
     * @param ctx pass the context
     */
    public static VibeNPlay with(Context ctx){
        mCtx = ctx;
        return new VibeNPlay();
    }

    public VibeNPlay vibration(boolean isOn){
        mIsVibrationOn = isOn;
        return this;
    }

    public VibeNPlay sound(boolean isOn){
        mIsSoundOn = isOn;
        return this;
    }

    public static void vibrationPattern(long firstbeat, long rest, long secondbeat, int loop){
        mVibratorFirstBeatLength = firstbeat;
        mVibratorRestLength = rest;
        mVibratorSecondBeatLength = secondbeat;
        mVibratorLoopCount = loop;
    }

    public static void soundPattern(boolean isloop){
        mIsSoundLoop = isloop;
    }

    public static void setVibration(boolean isOn){
        mIsVibrationOn = isOn;
    }

    public static void setSound(boolean isOn){
        mIsSoundOn = isOn;
    }

    public static void reset(){
        mIsVibrationOn = true;
        mIsSoundOn = true;
        mVibratorFirstBeatLength = 300;
        mVibratorRestLength = 250;
        mVibratorSecondBeatLength =720;
        mVibratorLoopCount = 1000;
        mIsSoundLoop = true;
    }

    public static void start(Context context){
        if (context!=null){
            if(mIsVibrationOn){
                try {
                    Rumble.init(context);
                    Rumble.makePattern()
                            .beat(mVibratorFirstBeatLength)
                            .rest(mVibratorRestLength)
                            .beat(mVibratorSecondBeatLength)
                            .playPattern(mVibratorLoopCount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(mIsSoundOn){
                try {
                    Uri alert =  RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setDataSource(context, alert);
                    final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
                        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                        mMediaPlayer.setLooping(mIsSoundLoop);
                        mMediaPlayer.prepare();
                        mMediaPlayer.start();
                    }
                } catch(Exception e) {
                }
            }
        }

    }

    /**
     * stops playing alarm and vibration
     */
    public static void stop(){
        try {
            Rumble.stop();
            mMediaPlayer.stop();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
