package com.aar.app.wsp.features;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseIntArray;

import com.aar.app.wsp.R;
import com.aar.app.wsp.features.settings.Preferences;

import javax.inject.Inject;

/**
 * Created by abdularis on 22/07/17.
 */

public class SoundPlayer {

    public enum Sound {
        Correct, Wrong, Winning
    }

    private Preferences mPreferences;

    private SoundPool mSoundPool;
    private SparseIntArray mSoundPoolMap;
    private int mBackgroundMusic;

    @Inject
    public SoundPlayer(Context context, Preferences preferences) {
        mPreferences = preferences;

        init(context);
    }

    public void play(Sound sound) {
        if (mPreferences.enableSound()) {
            mSoundPool.play(mSoundPoolMap.get(sound.ordinal()),
                    1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

    private void init(Context context) {
        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        mSoundPoolMap = new SparseIntArray();

        mSoundPoolMap.put(Sound.Correct.ordinal(),
                mSoundPool.load(context, R.raw.correct, 1));
        mSoundPoolMap.put(Sound.Wrong.ordinal(),
                mSoundPool.load(context, R.raw.wrong, 1));
        mSoundPoolMap.put(Sound.Winning.ordinal(),
                mSoundPool.load(context, R.raw.winning, 1));

        mBackgroundMusic = mSoundPool.load(context, R.raw.music, 1);
    }

}
