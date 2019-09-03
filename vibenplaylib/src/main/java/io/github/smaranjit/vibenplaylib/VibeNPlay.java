package io.github.smaranjit.vibenplaylib;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * controls sound and vibration
 * sound types could be ringtone, alarm, notification
 */
public class VibeNPlay {
    private static MediaPlayer mMediaPlayer;
    private static Context mCtx;
    private static boolean mIsVibrationOn = true;
    private static boolean mIsSoundOn = true;
    private static long mVibratorFirstBeatLength = 300;
    private static long mVibratorRestLength = 250;
    private static long mVibratorSecondBeatLength = 720;
    private static int mVibratorLoopCount = 1000;
    private static boolean mIsSoundLoop = true;
    public static final int SOUND_RINGTONE = RingtoneManager.TYPE_RINGTONE;
    public static final int SOUND_ALARM = RingtoneManager.TYPE_ALARM;
    public static final int SOUND_NOTIFICATION = RingtoneManager.TYPE_NOTIFICATION;
    public static final int SOUND_ALL = RingtoneManager.TYPE_ALL;
    private static int SOUND_TYPE = SOUND_RINGTONE;

    /**
     * initialize VibeNPlay
     *
     * @param ctx Context reference
     */
    public static VibeNPlay with(Context ctx) {
        mCtx = ctx;
        return new VibeNPlay();
    }

    /**
     * vibration setting
     *
     * @param isOn true for on or false for off
     */
    public VibeNPlay vibration(boolean isOn) {
        mIsVibrationOn = isOn;
        return this;
    }

    /**
     * sound setting
     *
     * @param isOn true for on or false for off
     */
    public VibeNPlay sound(boolean isOn) {
        mIsSoundOn = isOn;
        return this;
    }

    /**
     * play immediately
     */
    public void play() {
        start();
    }

    /**
     * if play not called then call this
     */
    public void initialize() {
        //
    }

    /**
     * set pattern of vibration
     *
     * @param firstbeat  in millisecond first beat
     * @param rest       in millisecond rest or pause
     * @param secondbeat in millisecond second beat
     * @param loop       no of times to play vibration pattern
     */
    public static void setVibrationPattern(long firstbeat, long rest, long secondbeat, int loop) {
        mVibratorFirstBeatLength = firstbeat;
        mVibratorRestLength = rest;
        mVibratorSecondBeatLength = secondbeat;
        mVibratorLoopCount = loop;
    }

    /**
     * set sound pattern
     *
     * @param isloop play sound in loop or one time
     */
    public static void setSoundPattern(boolean isloop) {
        mIsSoundLoop = isloop;
    }

    /**
     * set sound type
     *
     * @param soundType VibeNPlay.SOUND_RINGTONE,VibeNPlay.SOUND_ALARM,VibeNPlay.SOUND_NOTIFICATION,VibeNPlay.SOUND_ALL
     */
    public static void setSoundType(int soundType) {
        SOUND_TYPE = soundType;
    }

    /**
     * vibration setting
     *
     * @param isOn true for on or false for off
     */
    public static void setVibration(boolean isOn) {
        mIsVibrationOn = isOn;
    }

    /**
     * sound setting
     *
     * @param isOn true for on or false for off
     */
    public static void setSound(boolean isOn) {
        mIsSoundOn = isOn;
    }

    /**
     * resets all settings to their default
     */
    public static void reset() {
        mIsVibrationOn = true;
        mIsSoundOn = true;
        mVibratorFirstBeatLength = 300;
        mVibratorRestLength = 250;
        mVibratorSecondBeatLength = 720;
        mVibratorLoopCount = 1000;
        mIsSoundLoop = true;
        SOUND_TYPE = SOUND_RINGTONE;
    }

    /**
     * starts playing vibration and sound based on their settings
     */
    public static void start() {
        if (mCtx != null) {
            if (mIsVibrationOn) {
                try {
                    Rumble.init(mCtx);
                    Rumble.makePattern()
                            .beat(mVibratorFirstBeatLength)
                            .rest(mVibratorRestLength)
                            .beat(mVibratorSecondBeatLength)
                            .playPattern(mVibratorLoopCount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (mIsSoundOn) {
                try {
                    Uri alert = RingtoneManager.getDefaultUri(SOUND_TYPE);
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setDataSource(mCtx, alert);
                    //final AudioManager audioManager = (AudioManager) mCtx.getSystemService(Context.AUDIO_SERVICE);
                    //if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                    mMediaPlayer.setLooping(mIsSoundLoop);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    //}
                } catch (Exception e) {
                }
            }
        }

    }

    /**
     * stops playing sounds and vibration
     */
    public static void stop() {
        try {
            Rumble.stop();
            mMediaPlayer.stop();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
