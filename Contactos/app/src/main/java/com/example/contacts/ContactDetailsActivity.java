package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contacts.Entity.ContactsClass;
import com.squareup.picasso.Picasso;

public class ContactDetailsActivity extends AppCompatActivity {

    private static final int Permission_SMS = 22;
    private static final int Permission_CALL_PHONE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);


        // AQUI OBTEMOS LOS ID
        ImageView contactImage = findViewById(R.id.contactImage);
        TextView contactName = findViewById(R.id.contactName);
        TextView contactPhone = findViewById(R.id.contactPhone);
        TextView contactEmail = findViewById(R.id.contactEmail);

        // AQUI RESIVIMOS LA CLASE ContactsClass
        ContactsClass contact = (ContactsClass) getIntent().getSerializableExtra("contact");
        // con assert vericamos que contact no sea nulo
        assert contact != null;

        //IGNORAR PICASSO SI NO ESTAMOS CON IMAGENES
        Picasso.get()
                .load(contact.getImageUrl())
                .into(contactImage);

        // aqui seteamos
        contactName.setText(contact.getFullName());
        contactPhone.setText(contact.getPhone());
        contactEmail.setText(contact.getEmail());

        // EN ESTE METODO ENVIAMOS UN MENSAJE
        SendSMS(contact);

        // EN ESTE METODO HACEMOS UNA LLAMADA
        CallPhone(contactPhone, contact);

        // EN ESTE METODO ENVIAMOS UN EMAIL
        SendEMAIL(contactEmail, contact);
    }

    // ESTO SE VIEO EN LOS VIDEOS QUE TIENE QUE VER CON LOS PERMISOS -> REVISAR la carperta manifests y la clase AndroidManifest
    private void SendEMAIL(TextView contactEmail, ContactsClass contact) {
        contactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CLICK EMAIL", Toast.LENGTH_LONG).show();
                Intent email = new Intent(Intent.ACTION_SEND, Uri.fromParts("mailto", contact.getEmail(), null));
                email.putExtra(Intent.EXTRA_SUBJECT, "Hola soy " + contact.getFullName());
                startActivity(Intent.createChooser(email, ContactDetailsActivity.this.getString(R.string.send_mail)));
            }
        });
    }
    // ESTO SE VIEO EN LOS VIDEOS QUE TIENE QUE VER CON LOS PERMISOS -> REVISAR la carperta manifests y la clase AndroidManifest
    private void CallPhone(TextView contactPhone, ContactsClass contact) {
        contactPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CLICK CALL_PHONE", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhone()));
                if (ActivityCompat.checkSelfPermission(
                        ContactDetailsActivity.this, Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            ContactDetailsActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            Permission_CALL_PHONE
                    );
                    return;
                }
                startActivity(intent);
            }
        });
    }
    // ESTO SE VIEO EN LOS VIDEOS QUE TIENE QUE VER CON LOS PERMISOS -> REVISAR la carperta manifests y la clase AndroidManifest
    private void SendSMS(ContactsClass contact) {

        findViewById(R.id.contactSendSMS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "CLICK SMS", Toast.LENGTH_LONG).show();
                Intent intentSMS = new Intent(Intent.ACTION_SEND);
                if (ActivityCompat.checkSelfPermission(
                        ContactDetailsActivity.this, Manifest.permission.SEND_SMS
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            ContactDetailsActivity.this,
                            new String[]{Manifest.permission.SEND_SMS},
                            Permission_SMS
                    );
                    return;
                }

                intentSMS.putExtra("address", contact.getPhone());
                intentSMS.putExtra("sms_body", "Hola soy " + contact.getFullName());
                intentSMS.setType("text/plain");
                Intent intent = Intent.createChooser(intentSMS, null);
                startActivity(intent);
            }
        });
    }
}