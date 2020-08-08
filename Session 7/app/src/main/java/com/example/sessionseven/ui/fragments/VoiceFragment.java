package com.example.sessionseven.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sessionseven.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class VoiceFragment extends Fragment implements View.OnClickListener {
    private final int VOICE_REQUEST = 1999;
    TextView speechText;

    public VoiceFragment() {
        // Required empty public constructor
    }

    public static VoiceFragment newInstance() {
        return new VoiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_voice, container, false);

        speechText = view.findViewById(R.id.speech_text);
        Button speechToText = view.findViewById(R.id.speech_to_text);
        speechToText.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.speech_to_text) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            startActivityForResult(intent, VOICE_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result != null) {
                    speechText.setText(result.get(0));
                }
            }
        }
    }
}