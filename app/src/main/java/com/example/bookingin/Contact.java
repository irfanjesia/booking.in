package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Contact extends AppCompatActivity implements View.OnClickListener {

    Button _btnSubmit, _btnClear;
    ImageButton _btnBack;
    EditText _email, _message, _subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        _btnSubmit = findViewById(R.id.btn_submit);
        _btnClear = findViewById(R.id.btn_clear);
        _btnBack = findViewById(R.id.btn_back);
        _email = (EditText) findViewById(R.id.email);
        _message = findViewById(R.id.message);
        _subject = findViewById(R.id.subject);

        _email.setText("booking.in@gmail.com", TextView.BufferType.EDITABLE);

        _btnSubmit.setOnClickListener(this);
        _btnClear.setOnClickListener(this);
        _btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == _btnSubmit) {
            String recipientList = _email.getText().toString();
            String[] recipients = recipientList.split(",");
            String subject = _subject.getText().toString();
            String message = _message.getText().toString();

//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
//            intent.putExtra(Intent.EXTRA_SUBJECT, "");
//            intent.putExtra(Intent.EXTRA_TEXT, message);
//
//            intent.setType("message/rfc822");
//
//            startActivity(Intent.createChooser(intent, "Choose an email client"));
//            Intent intent = new Intent(Contact.this, ConfirmContact.class);
//            startActivity(intent);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                _subject.getText().clear();
                _message.getText().clear();
            }
        } else if (v == _btnClear) {
            _subject.getText().clear();
            _message.getText().clear();
        } else if (v == _btnBack) {
            finish();
        }
    }
}




