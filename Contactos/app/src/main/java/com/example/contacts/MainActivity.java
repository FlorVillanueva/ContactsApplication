package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.contacts.Adapter.AdapterContacts;
import com.example.contacts.Entity.ContactsClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        AQUI INICA APLICACION DE CONTACTOS
        RECUERDA NO HACER CONTROL C - CONTROL V.
        REPLICA ESTE EJERCCIO PASO A PASO.ss
        CON PRACTICA VERAS QUE ES FACIL.
        REPASAR PROGRAMACION ORIENTADA A OBJETOS AYUDARA MAS ADELANTE
        * */

       /* NO TOMAR ENCUENTA ESTE CODIGO
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        //QUI EMPIEZA EL RecyclerView -> SE EXPLICA EN LOS VIDEOS COMO FUNCIONA REVISA
        RecyclerView recyclerView = findViewById(R.id.listContact);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        //AQUI INSTANCEAMOS EL ADAPTER Y LE PASAMOS LA LISTA DE CONTACTOS Y EL CONTEXT
        AdapterContacts adapterContacts = new AdapterContacts(list(), this);
        //VEAMOS EL ADPTER -> EN LA CARPETA Adapter la clase AdapterContacts
        recyclerView.setAdapter(adapterContacts);
    }

    // ESTE ES UN METODO QUE RETORNA UNA LISTA DE CONTACTOS
    List<ContactsClass> list() {

        //CREAMOS UNA LISTA
        // ContactsClass es una clase revisar la clase - EN LA CARPETA Entity
        List<ContactsClass> contacts = new ArrayList<>();

        //AQUI VAMOS AGREGANDO CONTACTOS A LA LISTA
        contacts.add(new ContactsClass(
                "Pepe Cruzado",
                "999999999",
                "Pepe@hotmail.com",
                "https://lamenteesmaravillosa.com/wp-content/uploads/2018/07/hombre-capucha.jpg"));

        contacts.add(new ContactsClass(
                "Juan Tello",
                "999999999",
                "Juan@hotmail.com",
                "https://cdn.autobild.es/sites/navi.axelspringer.es/public/styles/1200/public/media/image/2016/09/569465-whatsapp-que-tus-contactos-ponen-rana-perfil.jpg?itok=tpvHWpeZ"));

        contacts.add(new ContactsClass(
                "Kevin Alva",
                "999999999",
                "Kevin@hotmail.com",
                "https://lamenteesmaravillosa.com/wp-content/uploads/2018/07/hombre-capucha.jpg"));

        contacts.add(new ContactsClass(
                "Ana Mego",
                "999999999",
                "Ana@hotmail.com",
                "https://lamenteesmaravillosa.com/wp-content/uploads/2018/07/hombre-capucha.jpg"));

        // RETORNAMOS LA LISTA DE CONTACTOS
        return contacts;
    }
}