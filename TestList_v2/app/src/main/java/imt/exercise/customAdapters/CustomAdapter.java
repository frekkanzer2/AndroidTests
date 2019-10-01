package imt.exercise.customAdapters;

import android.app.Activity;
import android.widget.ArrayAdapter;

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



    //?????
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextViewID);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1ID);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        infoTextField.setText(infoArray[position]);
        imageView.setImageResource(imageIDarray[position]);

        return rowView;

    };

}
