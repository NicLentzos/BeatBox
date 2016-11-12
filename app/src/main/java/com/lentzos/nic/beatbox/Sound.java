package com.lentzos.nic.beatbox;

/**
 * Created by Nic on 08/11/2016.
 */

public class Sound {
    //Create this class to create objects to keep information about the sound file name, the name of the sound tha the
    //user should see and any other information about the sound.
    private String mAssetPath;
    private String mName;

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    //integer instead of int - means it can have a null value when no sound plays.
    private Integer mSoundId;

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
