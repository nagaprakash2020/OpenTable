
package com.ndanda.opentable.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Data Binding adapters specific to the app.
 */
public class BindingAdapters {
    @BindingAdapter("visibility")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("setDouble")
    public static void setDouble(TextView view, double value){
        view.setText(String.valueOf(value));
    }

    @BindingAdapter("date")
    public static void setDate(TextView textView,String dateIn){
        if(dateIn != null && !dateIn.isEmpty()){
            Locale locale = new Locale("US");
            SimpleDateFormat incomingFormat = new SimpleDateFormat("yyyy-MM-dd",locale); // 2018-04-21 example incoming date
            SimpleDateFormat formatToDisplay = new SimpleDateFormat("EEE, dd MMM yyyy ",locale); // Mon, 13 Jun 2016 example displaying format
            try {
                Date date = incomingFormat.parse(dateIn);
                textView.setText(formatToDisplay.format(date));
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
    }
}
