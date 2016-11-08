package com.lentzos.nic.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
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

    //You can access assets from the AssetManager class. You can get an AssetManager from any Context.
    //Create a constructor that takes in a Context, pulls out an AssetManager and stores it away.
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();

    public BeatBox(Context context){
        mAssets = context.getAssets();
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
        for (String filename : soundNames) {
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);
        }
    }
        public List<Sound> getSounds() {
            return mSounds;
        }
}
