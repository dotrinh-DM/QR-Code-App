package com.dotrinh.qrcodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.dotrinh.qrcodedemo.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import static com.dotrinh.qrcodeapp.LogUtil.LogI;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    EditText textEmailAddress;
    EditText textPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.qrCode);
        editText = findViewById(R.id.editText);
        textEmailAddress = findViewById(R.id.textEmailAddress);
        textPhone = findViewById(R.id.textPhone);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogI("s: " + s);
                setQRCode(s.toString(), Contents.Type.TEXT, null);
            }
        });
        textEmailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogI("s: " + s);
                setQRCode(s.toString(), Contents.Type.EMAIL, null);
            }
        });
        textPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogI("s: " + s);
                setQRCode(s.toString(), Contents.Type.PHONE, null);
            }
        });

        //default
        Bundle bundle = new Bundle();
        bundle.putString(ContactsContract.Intents.Insert.NAME, "Do Trinh");
        bundle.putString(ContactsContract.Intents.Insert.PHONE, "080123456");
        bundle.putString(ContactsContract.Intents.Insert.POSTAL, "121-9999");
        bundle.putString(ContactsContract.Intents.Insert.EMAIL, "contact@dotrinh.com");
        bundle.putString(ContactsContract.Intents.Insert.NOTES, "dotrinh.com");
        setQRCode("CONTACT TEST", Contents.Type.CONTACT, bundle);
    }

    void setQRCode(String qrData, String type, Bundle bundle) {
        int qrCodeDimention = 1000;

        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, bundle, type, BarcodeFormat.QR_CODE.toString(), qrCodeDimention);

        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
