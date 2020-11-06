package com.example.contacts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.ContactDetailsActivity;
import com.example.contacts.Entity.ContactsClass;
import com.example.contacts.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterContacts extends RecyclerView.Adapter<AdapterContacts.AdapterContactsViewHolder> {

    List<ContactsClass> list;
    Context context;

    // EN EL CONSTRUCTOR RESIVIMOS LA LISTA DE CONTACTOS Y EL CONTEXT
    public AdapterContacts(List<ContactsClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // ESTO SE EXPLICA EN LOS VIDEOS REVISAR
    @NonNull
    @Override
    public AdapterContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new AdapterContactsViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_contacts, parent, false)
        );
    }
    // ESTO SE EXPLICA EN LOS VIDEOS REVISAR
    @Override
    public void onBindViewHolder(@NonNull AdapterContactsViewHolder holder, int position) {

        // aqui pasamos al contacto al metodo SetInfo que resive una contacto
        holder.SetInfo(list.get(position));

        // aqui se ve si se hace click en un contacto en cierta posicion en la lista
        holder.CardViewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactDetailsActivity.class);
                // en el putExtra pasamos el contacto
                //para poder pasar la clase ContactsClass a otro intent la clase debe  "implements Serializable"
                // revisar la clase
                intent.putExtra("contact", list.get(position));
                // ver la siguiente actividad
                context.startActivity(intent);
            }
        });
    }
    // ESTO SE EXPLICA EN LOS VIDEOS REVISAR
    @Override
    public int getItemCount() {
        return list.size();
    }

    static class AdapterContactsViewHolder extends RecyclerView.ViewHolder {


        CircleImageView profileId;
        TextView fullNameId;
        CardView CardViewId;

        public AdapterContactsViewHolder(@NonNull View itemView) {
            super(itemView);

            // AQUI ASIGNAMOS LOS ID

            profileId = itemView.findViewById(R.id.profileId);
            fullNameId = itemView.findViewById(R.id.fullNameId);
            CardViewId = itemView.findViewById(R.id.CardViewId);
        }

        // AQUI LE VAMOS A PASAR LOS CONTACTOS
        void SetInfo(ContactsClass aClass) {

            // AQUI USAMOS Picasso - No tomar en cuenta a un ya que todavia manejamos imagenes con URL
            Picasso.get()
                    .load(aClass.getImageUrl())
                    .into(profileId);

            // aqui resivmos el nombre y lo seteamos a la variable fullNameId
            fullNameId.setText(aClass.getFullName());
        }
    }
}
