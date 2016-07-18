package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasChocolate, hasWhippedCream, name);
        //displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "leila.s.oliveira@hotmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void increment(View view){
        if(quantity == 100){
            Toast.makeText(this, R.string.error_more_than_100_coffees, Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if(quantity == 1){
            Toast.makeText(this, R.string.error_less_than_1_coffee, Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculate price of order.
     *
     * @param hasWhippedCream
     * @param hasChocolate
     * @return price of order
     * */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        int priceOfOneCup = 5;
        int priceWhippedCream = 1;
        int priceChocolate = 2;

        int total = priceOfOneCup;
        if(hasWhippedCream){
            total += priceWhippedCream;
        }
        if(hasChocolate){
            total += priceChocolate;
        }

        total = total * quantity;
        return total;
    }

    /**
     * Create summary of the order.
     *
     * @param priceOfOrder of the order
     * @param addChocolate is whether or not the user wants whipped cream topping
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param name of the costumer
     * @return text summary
     */
    private String createOrderSummary(int priceOfOrder, boolean addChocolate, boolean addWhippedCream, String name){
        String message = getString(R.string.order_summary_name, name);
        message += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        message += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        message += "\n" + getString(R.string.order_summary_quantity, quantity);
        message += "\n" + getString(R.string.order_summary_price, priceOfOrder);
        message += "\n" + getString(R.string.thank_you);
        return message;
    }
}
