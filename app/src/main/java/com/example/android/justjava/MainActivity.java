package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

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
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, hasChocolate, hasWhippedCream, name);
        displayMessage(priceMessage);
    }

    public void increment(View view){
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view){
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

    private int calculatePrice(){
        return quantity * 5;
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
        String message = "Name: " + name;
        message += "\nAdd whipped cream? " + addWhippedCream;
        message += "\nAdd chocolate? " + addChocolate;
        message += "\nQuantity: " + quantity;
        message += "\nTotal: R$" + priceOfOrder;
        message += "\nThank you!";
        return message;
    }
}
