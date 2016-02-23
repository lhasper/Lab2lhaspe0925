package wmich.edu.lab2lhaspe0925;

/*
*************************************
* Programmer: Lee Hasper
* Class ID: lhaspe0925
* Lab 2
* CIS 2610: Business Mobile Programming
* Spring 2016
* Due date: 03/01/2016
* Date completed: 02/20/2016
*************************************
* Program Explanation
* This is a prototype shopping application for a Mobile device accessory wholesaler.
* It allows a buyer to tap on an item they would like to purchase, input the quantity to buy,
* and submit the order.
* If the order has an quantity error, the user immediately sees a toast Error message indicating what went wrong.
* Their quantity input is also deleted to further convey the need to input a proper quantity.
 * Once the proper quantity is input a pre-programmed text view displays a completed order message.
 * This message includes the name of the product, the quantity ordered, and subtotal for the purchase.
 * If the buyer decides to purchase another item, the order message is cleared from the screen.
 * The user now has a fresh canvas in which to begin a new purchase.
*************************************
*/


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


  Integer intCableQuantity=0;//Holds the integer value of cables input by user.
    String strCableChoice="none";//Takes the value of arrCables that matches the checked choice of the user.
    String strChkOutCost="$0.00";//Holds the converted string value of dblOrderCost
    String strChkOutQuantity="0";//Holds the value of string value of intCableQuantity
    String strChkOutMessage="none";//Holds value of successful checkout message to display to the user.

    //arrStringCables holds the product names of all products available for sale in the app.
    final String[] arrStrCables ={"Apple 6' Lightning Cable", "Apple 3' Lightning Cable", "Samsung 6' Micro Usb Cable", "Samsung 3' Micro Usb Cable"};

    //arrDblPrices holds prices of all products for sales.  where price[0] corresponds to cables[0].
    final Double[] arrDblPrices = {29.99, 19.99, 24.99, 12.99};

    Double dblUnitCost=0.00;//takes the value of arrDblPrices for product specific calculations
    Double dblOrderCost=0.00;//calculated value of unit cost*quantity.

    final String strToastMsg1 ="Valid orders must have a minimum of three units";//Quantity input is less than 3 message
    final String strToastMsg2="Maximum quantity is 24 units";// Quantity input is greater than 24 message


        @Override
        //Start Main Method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            /*
            Show action bar.
            Add battery logo to action bar.
            Allow action bar to display logo.
             */
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icbattery);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        /*
        **Variables related to buttons and textboxes**
        lightning6 corresponds to rdobtnLightning6
        lightning3 corresponds to rdobtnLightning3
        micro6 corresponds to rdobtnMicro6
        micro3 corresponds to rdobtnMicro3
        btnCheckOut corresponds to btnChkOut
        getQuantity corresponds to txtBxInputQty
        txtOrderMessage corresponds to txtOrderCost
             */
        final RadioButton lightning6=(RadioButton)findViewById(R.id.rdobtnLightning6);
        final RadioButton lightning3=(RadioButton)findViewById(R.id.rdobtnLightning3);
        final RadioButton micro6=(RadioButton)findViewById(R.id.rdobtnMicro6);
        final RadioButton micro3=(RadioButton)findViewById(R.id.rdobtnMicro3);
        final Button btnCheckOut= (Button) findViewById(R.id.btnChkOut);
        final EditText getQuantity=(EditText)findViewById(R.id.txtBxInputQty);
        final TextView txtOrderMessage=(TextView)findViewById(R.id.txtOrderCost);


            /* Method btnCheckOut.setOnClickListener
            ***EACH LINE IN DOCUMENTATION CORRESPONDS WITH EACH LINE OF CODE
            * ********************************************************************
               1. Get the quantity input by user from edit text box and convert it to a string.
               2. Format Decimal Numbers as currency for use in check out message.
               3. If Quantity is less than 3, Display Error message and delete user input.
               4. If Quantity is greater than 24, Display Error message and delete user input.
               5. If Quantity is greater or equal to 3 AND less or equal to 24 Continue to radio buttons.
                ***THE FOLLOWING ARE REPEATED FOR ECH OF THE RADIO BUTTON IFS (lightning6,lightning3,Micro6,Micro3.
                1. IF radio button (X) is clicked....
                2. Clear (if applicable) the Order Message box.
                3. The Cable chosen by the user is equal to cables array [X].
                4. The Unit cost of the chosen cable is equal to prices array [X].
                5. Order Cost = unit cost * quantity.
                6. Format the order cost as a currency string for reporting.
                7. Convert the input quantity to a string for reporting.
                8. Create the string message to be displayed to the user as a check out message.
                9. Display the check out message on the users screen in the text view.
                10. Clear the Quantity Input Box.
           */
            btnCheckOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intCableQuantity = Integer.parseInt(getQuantity.getText().toString());
                    DecimalFormat currencyFormat = new DecimalFormat("$###,###.##");

                    if (intCableQuantity < 3) {
                        Toast.makeText(MainActivity.this, strToastMsg1, Toast.LENGTH_LONG).show();
                        getQuantity.setText("");

                    }if (intCableQuantity > 24) {
                        Toast.makeText(MainActivity.this, strToastMsg2, Toast.LENGTH_LONG).show();
                        getQuantity.setText("");

                    }if (intCableQuantity >=3 && intCableQuantity <=24) {

                        if (lightning6.isChecked()){
                            txtOrderMessage.setText("");
                            strCableChoice= arrStrCables[0];
                            dblUnitCost= arrDblPrices[0];
                            dblOrderCost =dblUnitCost*intCableQuantity;
                            strChkOutCost = currencyFormat.format(dblOrderCost);
                            strChkOutQuantity=intCableQuantity.toString();
                            strChkOutMessage="Total Cost For "+strChkOutQuantity+" " + strCableChoice + " is " + strChkOutCost;
                            txtOrderMessage.setText(strChkOutMessage);
                            getQuantity.setText("");
                        }
                        if (lightning3.isChecked()){
                            txtOrderMessage.setText("");
                            strCableChoice= arrStrCables[1];
                            dblUnitCost= arrDblPrices[1];
                            dblOrderCost =dblUnitCost*intCableQuantity;
                            strChkOutCost = currencyFormat.format(dblOrderCost);
                            strChkOutQuantity=intCableQuantity.toString();
                            strChkOutMessage="Total Cost For "+strChkOutQuantity+" " + strCableChoice + " is " + strChkOutCost;
                            txtOrderMessage.setText(strChkOutMessage);
                            getQuantity.setText("");
                        }
                        if (micro6.isChecked()){
                            txtOrderMessage.setText("");
                            strCableChoice= arrStrCables[2];
                            dblUnitCost= arrDblPrices[2];
                            dblOrderCost =dblUnitCost*intCableQuantity;
                            strChkOutCost = currencyFormat.format(dblOrderCost);
                            strChkOutQuantity=intCableQuantity.toString();
                            strChkOutMessage="Total Cost For "+strChkOutQuantity+" " + strCableChoice + " is " + strChkOutCost;
                            txtOrderMessage.setText(strChkOutMessage);
                            getQuantity.setText("");
                        }
                        if (micro3.isChecked()){
                            txtOrderMessage.setText("");
                            strCableChoice= arrStrCables[3];
                            dblUnitCost= arrDblPrices[3];
                            dblOrderCost =dblUnitCost*intCableQuantity;
                            strChkOutCost = currencyFormat.format(dblOrderCost);
                            strChkOutQuantity=intCableQuantity.toString();
                            strChkOutMessage="Total Cost For "+strChkOutQuantity+" " + strCableChoice + " is " + strChkOutCost;
                            txtOrderMessage.setText(strChkOutMessage);
                            getQuantity.setText("");
                        }

                    }

                }
            });
        }







  }





















