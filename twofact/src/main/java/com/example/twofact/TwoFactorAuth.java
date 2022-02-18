package com.example.twofact;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class TwoFactorAuth {
    Context context;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference = firebaseDatabase.getReference( "AuthenticationInfo" );

    // creating a variable for
    // our object class
    AuthenticationInfoModel authenticationInfoClass = new AuthenticationInfoModel();


    public TwoFactorAuth(Context context) {
        this.context = context;
    }

    public Bitmap getQrCode(String uniqueKey) {
        // below line is for getting
        // the windowmanager service.
        WindowManager manager = (WindowManager) context.getSystemService( context.WINDOW_SERVICE );

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

    public void addDataToFirebase(int status, String uniqueCode, int randomCode) {
        // below 3 lines of code is used to set
        // data in our object class.
        authenticationInfoClass.setStatus( status );
        authenticationInfoClass.setAutoGenCode( randomCode );
        authenticationInfoClass.setUniqueNo( uniqueCode );
        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child( uniqueCode ).setValue( authenticationInfoClass );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        } );
    }


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
}
