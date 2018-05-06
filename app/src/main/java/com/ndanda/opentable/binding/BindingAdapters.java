
package com.ndanda.opentable.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ndanda.opentable.vo.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Data Binding adapters specific to the app.
 */
public class BindingAdapters {
    @BindingAdapter("showLoading")
    public static void showLoading(ProgressBar view, Resource resource) {
        switch (resource.status){
            case ERROR:
            case SUCCESS:
                view.setVisibility(View.GONE);
                break;
            case LOADING:
                view.setVisibility(View.VISIBLE);
                break;
        }
    }


    @BindingAdapter("showErrorMessage")
    public static void showErrorMessage(TextView view,Resource resource){
        switch (resource.status){
            case LOADING:
            case SUCCESS:
                view.setVisibility(View.GONE);
                break;
            case ERROR:
                view.setVisibility(View.VISIBLE);
                break;
        }
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
