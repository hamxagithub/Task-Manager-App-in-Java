package com.example.assignment2;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuoteFragment extends Fragment {

    private TextView tvQuote;
    private List<String> quotes = new ArrayList<>();
    private int currentIndex = 0;
    private Handler handler = new Handler();
    private Runnable quoteChanger;

    private static final int INTERVAL = 10_000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        tvQuote = view.findViewById(R.id.tv_quote);

        loadQuotesFromFile();

        if (!quotes.isEmpty()) {
            startQuoteRotation();
        } else {
            tvQuote.setText("No quotes found.");
        }

        return view;
    }

    private void loadQuotesFromFile() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.quotes);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (builder.length() > 0) {
                        quotes.add(builder.toString().trim());
                        builder.setLength(0);
                    }
                } else {
                    builder.append(line).append("\n");
                }
            }

            if (builder.length() > 0) {
                quotes.add(builder.toString().trim());
            }

            reader.close();
        } catch (Exception e) {
            tvQuote.setText("Error reading quotes.");
        }
    }

    private void startQuoteRotation() {
        quoteChanger = new Runnable() {
            @Override
            public void run() {

                tvQuote.setText(quotes.get(currentIndex));


                currentIndex = (currentIndex + 1) % quotes.size();

                handler.postDelayed(this, INTERVAL);
            }
        };

        handler.post(quoteChanger);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (quoteChanger != null) {
            handler.removeCallbacks(quoteChanger);
        }
    }
}
