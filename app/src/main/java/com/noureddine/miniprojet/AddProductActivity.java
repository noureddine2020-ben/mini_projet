package com.noureddine.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddProductActivity extends AppCompatActivity {

    TextInputEditText name,price,quantity;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        name=(TextInputEditText) findViewById(R.id.add_name);
        price=(TextInputEditText)findViewById(R.id.add_price);
        quantity=(TextInputEditText)findViewById(R.id.add_quantity);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


        public void add (View view)
        {
            String nameText,priceText,quantityText;

            nameText=name.getText().toString();
            priceText=price.getText().toString();
            quantityText=quantity.getText().toString();

            if(nameText.isEmpty()||nameText.equals(" "))
            {
                name.setError("Fill here please !");
                return;
            }

            if(priceText.isEmpty()||priceText.equals(" "))
            {
                price.setError("Fill here please !");
                return;
            }

            if(quantityText.isEmpty()||quantityText.equals(" "))
            {
                quantity.setError("Fill here please !");
                return;
            }

            Product product =new Product(nameText , priceText , quantityText);

            String key = mDatabase.child("products").push().getKey();
            product.setId(key);

            mDatabase.child("products").child(key).setValue(product);

            name.setText("");
            price.setText("");
            quantity.setText("");


        }
    }