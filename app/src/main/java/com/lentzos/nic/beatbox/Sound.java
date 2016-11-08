package com.lentzos.nic.beatbox;

/**
 * Created by Nic on 08/11/2016.
 */

public class Sound {
    //Create this class to create objects to keep information about the sound file name, the name of the sound tha the
    //user should see and any other information about the sound.
    private String mAssetPath;
    private String mName;

    //Sound constructor
    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav", "");
    }

    public String getAssetPath(){
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }
}
