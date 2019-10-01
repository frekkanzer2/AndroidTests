package imt.exercise.customAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import imt.exercise.testlist_v2.R;

public class CustomAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the contact image
    private final Integer[] imagesIDarray;
    //to store the list of names
    private final String[] namesArray;
    //to store the list of subNames
    private final String[] subnamesArray;

    public CustomAdapter(Activity context, Integer[] imgsArray, String[] namesArray, String[] subnamesArray){
        super(context, R.layout.complex_item, namesArray);
        this.context = context;
        this.imagesIDarray = imgsArray;
        this.namesArray = namesArray;
        this.subnamesArray = subnamesArray;
    }

    public View getView(int position, View view, ViewGroup group){
        //creating a LayoutInflater to generate the view
        LayoutInflater injector = context.getLayoutInflater();
        //creating a single row by using the inflater
        View singleRow = injector.inflate(R.layout.complex_item, null, true);
        //referencing all objects
            //getting views
        TextView textViewName = singleRow.findViewById(R.id.itemName);
        TextView textViewSubname = singleRow.findViewById(R.id.itemSubname);
        ImageView imageViewImg = singleRow.findViewById(R.id.itemImage);
        //now we have to set values to the views generated
        textViewName.setText(this.namesArray[position]);
        textViewSubname.setText(this.subnamesArray[position]);
        imageViewImg.setImageResource(this.imagesIDarray[position]);
        return singleRow;
    }

}
