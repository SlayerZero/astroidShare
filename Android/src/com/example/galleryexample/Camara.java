package com.example.galleryexample;


import java.io.File;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;

public class Camara extends Activity {
	
	/*
	 * Constantes 
	 */
	Button btnCamara;
	private String foto;
	private static int TAKE_PICTURE = 2;
	double aleatorio = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camara);
		aleatorio = new Double(Math.random() * 100).intValue();
		foto = Environment.getExternalStorageDirectory() + "/imagen"+ aleatorio +".jpg";
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri output = Uri.fromFile(new File(foto));
		intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
		startActivityForResult(intent, TAKE_PICTURE); // 1 para la camara, 2 para la galeria		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		ImageView iv = (ImageView) findViewById(R.id.imageView1);
		iv.setImageBitmap(BitmapFactory.decodeFile(foto));
		File file = new File(foto);
	
		AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
 
        builder.setMessage("¿Desea subir la imagen?")
        .setTitle("Confirmacion")
        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
               @Override
			public void onClick(DialogInterface dialog, int id) {
                    Log.i("Dialogos", "Confirmacion Aceptada.");
                    	
                    	UploaderFoto nuevaTarea = new UploaderFoto();
                    	nuevaTarea.execute(foto);
                        dialog.cancel();
                   }
               })
        .setNegativeButton("No, solo Guardar", new DialogInterface.OnClickListener() {
               @Override
			public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Guardando");
                        dialog.cancel();
                   }
               });
	}

	class UploaderFoto extends AsyncTask<String, Void, Void>{

		ProgressDialog pDialog;
		String miFoto = "";
		
		@Override
		protected Void doInBackground(String... params) {
			miFoto = params[0];
			try { 
				HttpClient httpclient = new DefaultHttpClient();
				httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
				HttpPost httppost = new HttpPost("http://172.16.170.87/Server/upload.php");
				File file = new File(miFoto);
				MultipartEntity mpEntity = new MultipartEntity();
				ContentBody foto = new FileBody(file, "image/jpeg");
				mpEntity.addPart("fotoUp", foto);
				httppost.setEntity(mpEntity);
				httpclient.execute(httppost);
				httpclient.getConnectionManager().shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Camara.this);
	        pDialog.setMessage("Subiendo la imagen, espere." );
	        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pDialog.setCancelable(true);
	        pDialog.show();
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			pDialog.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
