package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group_15.bta.R;




public class StudentSettingsActivity extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button logoutBtn =  view.findViewById(R.id.logout_student_account_btn);

        logoutBtn.setOnClickListener(view1 -> {
            Intent courseIntent = new Intent(getContext(), MainActivity.class);
            requireActivity().startActivity(courseIntent);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}