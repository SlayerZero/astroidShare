package com.example.galleryexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Integer[] pics = { R.drawable.antartica1, R.drawable.antartica2,
			R.drawable.antartica3, R.drawable.antartica4,
			R.drawable.antartica5,R.drawable.antartica6};
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		// create adapter Gallery
		gallery.setAdapter(new ImageAdapter(this));
		imageView = (ImageView) findViewById(R.id.imageView);
		gallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "pic: " + arg2,
						Toast.LENGTH_SHORT).show();
				imageView.setImageResource(pics[arg2]);
			}
		});
		
		
		Button camara = (Button)findViewById(R.id.camara);
		camara.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this , Camara.class);
				startActivity(i);
//				Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
//				startActivity(intent);
			}
		});
		
		Button galeria = (Button)findViewById(R.id.galeria);
		galeria.setOnClickListener(new OnClickListener() {		
		@Override
			public void onClick(View v) {
				Intent obj = new Intent(MainActivity.this,Galeria.class);
			    startActivity(obj);
			}
		});
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class ImageAdapter extends BaseAdapter {
		private Context context;
		int imageBackground;

		public ImageAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pics.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(pics[arg0]);
			return imageView;
		}
	}

}
