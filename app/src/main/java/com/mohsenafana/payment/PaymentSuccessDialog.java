package com.mohsenafana.payment;

import android.os.Bundle;
import android.os.Handler;

import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mohsenafana.mbank.R;


public class PaymentSuccessDialog extends AppCompatActivity {

    private AppCompatButton show_dialog;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success_dialog);

        show_dialog =  findViewById(R.id.show_dialog);
        progress_bar =  findViewById(R.id.progress_bar);

        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAction();
            }
        });

        submitAction();
    }


    private void submitAction() {
        progress_bar.setVisibility(View.VISIBLE);
        show_dialog.setAlpha(0f);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialogPaymentSuccess();
                progress_bar.setVisibility(View.GONE);
                show_dialog.setAlpha(1f);
            }
        }, 500);
    }

    private void showDialogPaymentSuccess() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogPaymentSuccessFragment newFragment = new DialogPaymentSuccessFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
