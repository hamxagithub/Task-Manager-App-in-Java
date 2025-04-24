package com.example.assignment2;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;
import androidx.preference.ListPreference;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        initializePreferences();
    }

    private void initializePreferences() {

        SwitchPreferenceCompat darkModePref = findPreference("dark_mode");
        if (darkModePref != null) {
            darkModePref.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isDarkModeEnabled = (boolean) newValue;
                updateDarkMode(isDarkModeEnabled);
                return true;
            });
        }


        SwitchPreferenceCompat notificationsPref = findPreference("notifications");
        if (notificationsPref != null) {
            notificationsPref.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isEnabled = (boolean) newValue;
                toggleNotifications(isEnabled);
                return true;
            });
        }

        ListPreference sortingPref = findPreference("task_sorting");
        if (sortingPref != null) {
            sortingPref.setOnPreferenceChangeListener((preference, newValue) -> {
                updateTaskSorting((String) newValue);
                return true;
            });
        }
    }

    private void updateDarkMode(boolean isEnabled) {
        int nightMode = isEnabled ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }

    private void toggleNotifications(boolean isEnabled) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("notifications", isEnabled);
        editor.apply();
    }

    private void updateTaskSorting(String sortingOrder) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("task_sorting", sortingOrder);
        editor.apply();
    }
}
