package com.example.sessionseven.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sessionseven.services.MusicService;
import com.example.sessionseven.R;

public class MusicFragment extends Fragment implements View.OnClickListener {
    public MusicFragment() {
        // Required empty public constructor
    }

    public static MusicFragment newInstance() {
        return new MusicFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        Button play = view.findViewById(R.id.play);
        Button stop = view.findViewById(R.id.stop);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), MusicService.class);
            if (view.getId() == R.id.play) {
                getActivity().startService(intent);
                Toast.makeText(getActivity(), R.string.music_started, Toast.LENGTH_SHORT).show();
            } else if (view.getId() == R.id.stop) {
                getActivity().stopService(intent);
                Toast.makeText(getActivity(), R.string.music_stopped, Toast.LENGTH_SHORT).show();
            }
        }
    }
}