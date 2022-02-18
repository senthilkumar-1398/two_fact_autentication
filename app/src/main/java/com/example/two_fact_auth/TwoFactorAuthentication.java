package com.example.two_fact_auth;

import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class TwoFactorAuthentication extends AppCompatActivity {
    SwitchCompat switchCompat;
    AppCompatButton btnGetOtp;
    AppCompatEditText etPhoneNo;
    AppCompatButton btnVerifyOtp;
    AppCompatButton btnSave;
    AppCompatEditText etOTP;
    RelativeLayout layoutSecureCode;
    AppCompatEditText etGenerateCode;
    AppCompatTextView tvCopy;
    ClipboardManager clipboardManager;
    AppCompatImageView ivQrCode;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;
    String mStrUniqueKey;
    String enableAuthentication = "";
    String yourKey;
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
//    AuthenticationInfoModel authenticationInfoClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
//        switchCompat = findViewById( R.id.switch_authentication );
//        btnGetOtp = findViewById( R.id.btn_get_otp );
//        btnSave = findViewById( R.id.btn_save );
//        etPhoneNo = findViewById( R.id.et_phone_no );
//        btnVerifyOtp = findViewById( R.id.btn_otp_verification );
//        etOTP = findViewById( R.id.et_otp );
//        layoutSecureCode = findViewById( R.id.layout_security_code );
//        etGenerateCode = findViewById( R.id.et_unique_code );
//        tvCopy = findViewById( R.id.tv_copy );
//        ivQrCode = findViewById( R.id.iv_qr_code );
//        // below line is used to get the
//        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();
//
//        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference( "AuthenticationInfo" );
//
//        // initializing our object
//        // class variable.
//        authenticationInfoClass = new AuthenticationInfoModel();
//        sharedPreferenceEditor = new SharedPreferenceEditor();
//        enableAuthentication = sharedPreferenceEditor.getString( TwoFactorAuthenticationActivity.this, "authentication_enable" );
//        Log.e( "2FA", "onCreate: " + enableAuthentication );
//        yourKey = sharedPreferenceEditor.getString( TwoFactorAuthenticationActivity.this, "unique_code" );
//        Log.e( "2232", "onCreate2fa  : " + enableAuthentication );
//
//        tvCopy.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String text = etGenerateCode.getText().toString();
//                if (!text.isEmpty()) {
//                    clipboardManager = (ClipboardManager) getSystemService( Context.CLIPBOARD_SERVICE );
//                    ClipData clipData = ClipData.newPlainText( "key", text );
//                    clipboardManager.setPrimaryClip( clipData );
//                    Toast.makeText( getApplicationContext(), "Copied", Toast.LENGTH_SHORT ).show();
//                } else {
//                    Toast.makeText( getApplicationContext(), "No text to be copied", Toast.LENGTH_SHORT ).show();
//                }
//            }
//        } );
//
//        btnSave.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addDataToFirebase( 1, mStrUniqueKey, Integer.parseInt( getRandomNumber() ) );
//            }
//        } );
    }

//    private static String getRandomNumber() {
//        Log.e( "TAG", "getRandomNumber: " );
//        Random random = new Random();
//        int randomNumber = random.nextInt( 999999 - 100000 );
//        return String.format( "%06d", randomNumber );
//    }
//
//    public static long generateRandom(int length) {
//        Random random = new Random();
//        char[] digits = new char[length];
//        digits[0] = (char) (random.nextInt( 9 ) + '1');
//        for (int i = 1; i < length; i++) {
//            digits[i] = (char) (random.nextInt( 10 ) + '0');
//        }
//        return Long.parseLong( new String( digits ) );
//    }

    public Bitmap getQrCode(String uniqueKey) {
        // below line is for getting
        // the windowmanager service.
        WindowManager manager = (WindowManager) getSystemService( WINDOW_SERVICE );

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize( point );

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder( uniqueKey, null, QRGContents.Type.TEXT, dimen );
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e( "Tag", e.toString() );
        }
        return bitmap;
    }
//
//    private void addDataToFirebase(int status, String code, int randomCode) {
//        // below 3 lines of code is used to set
//        // data in our object class.
//        authenticationInfoClass.setStatus( status );
//        authenticationInfoClass.setAutoGenCode( randomCode );
//        authenticationInfoClass.setUniqueNo( code );
//        // we are use add value event listener method
//        // which is called with database reference.
//        databaseReference.addValueEventListener( new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.child( code ).setValue( authenticationInfoClass );
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        } );
//    }
//
//    public void updateDisableStatusInFirebase() {
//        HashMap statusUpdate = new HashMap();
//        statusUpdate.put( "status", 0 );
//        databaseReference.child( yourKey ).updateChildren( statusUpdate ).addOnCompleteListener( new OnCompleteListener() {
//            @Override
//            public void onComplete(@NonNull Task task) {
//                if (task.isSuccessful()) {
//                    Log.e( "2FA", "onComplete: " );
//                    sharedPreferenceEditor.putString( TwoFactorAuthenticationActivity.this, "authentication_enable", "0" );
//                } else {
//                    Toast.makeText( TwoFactorAuthenticationActivity.this, "Fail to add data ", Toast.LENGTH_SHORT ).show();
//                }
//            }
//        } );
//    }
//
//    public void updateEnableStatusInFirebase() {
//        HashMap statusUpdate = new HashMap();
//        statusUpdate.put( "status", 1 );
//        databaseReference.child( yourKey ).updateChildren( statusUpdate ).addOnCompleteListener( new OnCompleteListener() {
//            @Override
//            public void onComplete(@NonNull Task task) {
//                if (task.isSuccessful()) {
//                    Log.e( "2FA", "onComplete: " );
//                    sharedPreferenceEditor.putString( TwoFactorAuthenticationActivity.this, "authentication_enable", "1" );
//                    yourKey = sharedPreferenceEditor.getString( TwoFactorAuthenticationActivity.this, "unique_code" );
//                    etGenerateCode.setVisibility( View.VISIBLE );
//                    etGenerateCode.setText( yourKey );
//                    getSecurityCode2();
//                } else {
//                    Toast.makeText( TwoFactorAuthenticationActivity.this, "Fail to add data ", Toast.LENGTH_SHORT ).show();
//                }
//            }
//        } );
//    }
}