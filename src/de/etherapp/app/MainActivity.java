package de.etherapp.app;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.etherapp.beans.PadlistItem;
import de.etherapp.adapters.CustomBaseAdapter;

public class MainActivity extends Activity {

	ListView lv;
    List<PadlistItem> padlistItems;
	
	public static final String[] titles = new String[] { "Strawberry",
        "Banana", "Orange", "Mixed" };
	 
	public static final String[] descriptions = new String[] {
        "It is an aggregate accessory fruit",
        "It is the largest herbaceous flowering plant", "Citrus Fruit",
        "Mixed Fruits" };
    
	public static final Integer[] images = {
		R.drawable.ic_launcher,
		R.drawable.ic_launcher,
		R.drawable.ic_launcher,
		R.drawable.ic_launcher
	};
    
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//get Listview we want to fill
        lv = (ListView) findViewById(R.id.padlist);
        
        /*//create array list
        ArrayList<String> padlist = new ArrayList<String>();
        padlist.add("netze1");
        padlist.add("protolol");
        padlist.add("netze2");
        padlist.add("uet");
        padlist.add("komplexlabor");
        padlist.add("netzak");
        padlist.add("netzak2");
        padlist.add("swe");
        padlist.add("NZ1-Switching");
        padlist.add("blabla");
        padlist.add("foobar");
        padlist.add("loremipsum");
        
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, padlist);
        lv.setAdapter(arrayAdapter);*/
        
        padlistItems = new ArrayList<PadlistItem>();
        for (int i = 0; i < titles.length; i++) {
        	PadlistItem item = new PadlistItem(images[i], titles[i], descriptions[i]);
        	padlistItems.add(item);
        }
 
        CustomBaseAdapter adapter = new CustomBaseAdapter(this, padlistItems);
        lv.setAdapter(adapter);
        
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
