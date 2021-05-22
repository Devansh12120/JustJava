package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;
public class MainActivity extends AppCompatActivity {
    int quatity=1;
    int creamcost = 0;
    int cococost =0;
    float gsttotal= (float) 0.00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increaseQuantity(View view){
        quatity+=1;
        display(quatity);
    }
    public void decreaseQuantity(View view){

        quatity-=1;
        display(quatity);
    }

    public void submitOrder(View view) {
        if (quatity>100 || quatity<1){
            return;
        }
        else {
            displayPrice(quatity);
        }

    }
    public void displayCream(boolean hascream, boolean hascoco){
        TextView creamTextView = (TextView) findViewById(
                R.id.cream_text_view);
        TextView cocoTextView = (TextView) findViewById(
                R.id.coco_text_view);
        if (hascream){
            creamcost = 6*quatity;
            creamTextView.setText("$"+NumberFormat.getIntegerInstance().format(creamcost));
        }
        else{

            creamTextView.setText(NumberFormat.getIntegerInstance().format(0));
        }
        if (hascoco){
            cococost = 6*quatity;
            cocoTextView.setText("$"+NumberFormat.getIntegerInstance().format(cococost));

        }
        else{

            String coco = String.valueOf(hascoco);
            cocoTextView.setText(NumberFormat.getIntegerInstance().format(0));
        }


    }
    private float displayMessage(int number) {
        TextView messageTextView = (TextView) findViewById(
                R.id.message_text_view);
        messageTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        TextView gstTextView = (TextView) findViewById(
                R.id.gst_text_view);
        gsttotal = (float) (number*0.18);
        gstTextView.setText(NumberFormat.getCurrencyInstance().format(gsttotal));
        return (float) (number*0.18);

    }
    public  void  PrintRecipt(View view){
        CheckBox whippedcreamcheckbox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        CheckBox extracococheckbox = (CheckBox) findViewById(R.id.extra_coco_check_box);
        displayCream(whippedcreamcheckbox.isChecked(), extracococheckbox.isChecked());
        EditText name = (EditText) findViewById(R.id.name_view);
        String namevalue = name.getText().toString();
        EditText phone = (EditText) findViewById(R.id.phone_view);
        String phonevalue = phone.getText().toString();
        TextView nameTextView = (TextView) findViewById(
                R.id.name_text_view);
        nameTextView.setText(namevalue);
        TextView contactTextView = (TextView) findViewById(
                R.id.contact_text_view);
        contactTextView.setText(phonevalue);
        float a = (float) displayMessage(quatity*5);
        float b = (float) displayPrice(quatity);
        TextView totalTextView = (TextView) findViewById(
                R.id.total_text_view);
        totalTextView.setText(NumberFormat.getCurrencyInstance().format(a+b+cococost+creamcost));
        displayQuant(quatity);
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms",phonevalue,null));
        intent.putExtra(Intent.EXTRA_TEXT,"Here is your bill " + namevalue+"\n"+"Thanks for paying visit\n"+"Quantity                 " +quatity+"\n"+ "bill amount            " +"$"+(a+b) + "\n" + "Gst @18%              " +"$"+gsttotal + "\n"+"coco toppings       " +"$"+cococost + "\n"+"cream toppings     " +"$"+creamcost + "\n"+"Total cost              " +"$"+(a+b+cococost+creamcost));

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
    private  void displayQuant(int number){
        TextView quantTextView = (TextView) findViewById(
                R.id.quant_text_view);
        quantTextView.setText("" + number);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);


    }

    private int displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(
                R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number*5));
        return (number*5);
    }

}
