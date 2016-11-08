package com.lentzos.nic.beatbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Nic on 08/11/2016.
 */

public class BeatBoxFragment extends Fragment {
    //mBeatbox used to create BeatBox instance to show log file output (p.333)
    private BeatBox mBeatBox;
    //static newinstance() method to create the fragment.
    public static BeatBoxFragment newinstance() {
        return new BeatBoxFragment();
    }
    //Creating an instance of BeatBox to verify that AssetManager can list the .wav files - p.333
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatBox = new BeatBox(getActivity());
    }

    //Inflate the layout for the fragment's view and return the inflated view to the hosting activity.
    //Call LayoutInflater.inflate, passing it the layout resource ID and the view's parent (the ViewGroup above).
    //Pass false argument to tell Android that you will implement the view in the activity's code.
    //Create a recyclerview, then call the grid layout manager, telling it you want 3 columns.
    //Recyclerview recycles viewholders, but delegates the positioning of the views and the scrolling behaviour
    //to the layoutmanager. Linearlayoutmanager, Gridlayoutmanager etc.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.fragment_beat_box_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        //wire up the SoundAdapter adapter to recyclerView.
        recyclerView.setAdapter(new SoundAdapter());
        return  view;
    }
    //now to create the viewholders. They are wired up to list_item_sound.xml.
     private class SoundHolder extends RecyclerView.ViewHolder {
        private Button mButton;

        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound, container, false));

            mButton = (Button)itemView.findViewById(R.id.list_item_sound_button);
        }
    }
    //now create an adapter to hook up to the SoundHolder viewholders.
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        @Override // Overriding a method provided by the Adapter interface.
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater, parent);
        }

        @Override //implement method
        public void onBindViewHolder (SoundHolder soundHolder, int position) {

        }

        @Override  //implement abstract method
        public int getItemCount() {
            return 0;
        }
    }
    //now wire up SoundAdapter adapter in the Recyclerview (above).
}
