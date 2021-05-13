package com.MyGymRoutine.myapp.view.activity.exercise;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyGymRoutine.myapp.R;
import com.MyGymRoutine.myapp.databinding.FragmentExerciseBinding;

public class ExerciseFragment extends Fragment {

    private FragmentExerciseBinding binding;
    public ExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}