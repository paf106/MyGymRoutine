package com.MyGymRoutine.myapp.view.activity.routine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyGymRoutine.myapp.databinding.FragmentRoutineBinding;


public class RoutineFragment extends Fragment {

    private FragmentRoutineBinding binding;


    public RoutineFragment() {
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
        binding = FragmentRoutineBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}