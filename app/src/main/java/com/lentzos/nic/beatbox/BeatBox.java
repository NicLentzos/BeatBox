package com.lentzos.nic.beatbox;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nic on 08/11/2016.
 */

public class BeatBox {
    //Constants for logging and recording the asset directory.
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    //You can access assets from the AssetManager class. You can get an AssetManager from any Context.
    //Create a constructor that takes in a Context, pulls out an AssetManager and stores it away.
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context){
        mAssets = context.getAssets();
        //deprecated constructor
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    //To get an asset listing, loadSounds() looks at the assets with list(String).
    //AssetManager.list(String) lists filenames in the directory path you pass in.
    //Therefore you should see every .wav file in the directory.
    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }
        //After making presentable filenames in the Sound.java constructor, build up a list of sounds
        //in loadSounds().
        //call load(sounds) within Beatbox.loadsounds().
        for (String filename : soundNames) {
            try {
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            load(sound);
            mSounds.add(sound);
        } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
    }}

    //add a play(Sound) method to play back the sounds.

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

        public List<Sound> getSounds() {
            return mSounds;
        }
    //calling openFd(String) throws an IOException, so the load(Sound) method does too.
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        //Load a file into the soundpool for later playback.
        //mSoundPool.load returns an int ID which you stash in the mSoundId field.
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }
}
