package com.noureddine.miniprojet;
import android.widget.ArrayAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ProductsAdapter extends ArrayAdapter<Product> {


    private DatabaseReference mDatabase;

    public ProductsAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, products);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("products");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Product product = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        TextView name,price , quantity;
        ImageView edit,delete;


        name = (TextView) convertView.findViewById(R.id.product_name);
        price =  (TextView) convertView.findViewById(R.id.product_price);
        quantity =  (TextView) convertView.findViewById(R.id.product_quantity);

        name.setText(product.getName());
        price.setText("Price = "+product.getPrice());
        quantity.setText("Quantity = "+product.getQuantity());

        edit=(ImageView) convertView.findViewById(R.id.edit);
        delete=(ImageView) convertView.findViewById(R.id.delete);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(getContext(), EditProductActivity.class);

                edit.putExtra("name", product.getName());
                edit.putExtra("price", product.getPrice());
                edit.putExtra("quantity", product.getQuantity());
                edit.putExtra("id", product.getId());

                getContext().startActivity(edit);
            }

        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child(product.getId()).removeValue();
            }
        });

        return convertView;
    }

}