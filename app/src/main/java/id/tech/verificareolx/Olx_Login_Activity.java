package id.tech.verificareolx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import id.tech.util.Parameter_Collections;
import id.tech.util.Public_Functions;
import id.tech.util.Olx_ServiceHandlerJSON;
import id.tech.util.RowData_JenisOutlet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Olx_Login_Activity extends ActionBarActivity {
	Button btn_login;
	EditText ed_Username, ed_Password;
	SharedPreferences sh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getSupportActionBar().hide();

		sh = getSharedPreferences(Parameter_Collections.SH_NAME,
				Context.MODE_PRIVATE);

		ed_Username = (EditText) findViewById(R.id.ed_username);
		ed_Password = (EditText) findViewById(R.id.ed_password);

		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Async_Login().execute();
			}
		});
	}

	private class Async_Login extends AsyncTask<Void, Void, String> {
		String cUsername, cPassword, cCode, cMessage, cIdPegawai, cJabatan;
		Olx_DialogFragmentProgress dialogProgress;
		boolean isConnected = true;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialogProgress = new Olx_DialogFragmentProgress();
			dialogProgress.show(getSupportFragmentManager(), "");

			cUsername = ed_Username.getText().toString();
			cPassword = ed_Password.getText().toString();
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub

			if (Public_Functions.isNetworkAvailable(getApplicationContext())) {
				// boolean b = true;
				// if (b) {
				Olx_ServiceHandlerJSON olx_sh = new Olx_ServiceHandlerJSON();

				try {
					JSONObject jObj = olx_sh.json_login(cUsername, cPassword);
//					JSONObject jObj = null;
					Log.e("Result = 	", jObj.toString());

					cCode = jObj.getString(Parameter_Collections.TAG_JSON_CODE);

					if (cCode.equals("1")) {
						cMessage = jObj
								.getString(Parameter_Collections.TAG_JSON_MESSAGE);
						JSONObject c = jObj
								.getJSONObject(Parameter_Collections.TAG_DATA);
						cIdPegawai = c
								.getString(Parameter_Collections.TAG_ID_PEGAWAI);
						cJabatan = c
								.getString(Parameter_Collections.TAG_LOGIN_JABATAN);
					} else {
						cMessage = jObj
								.getString(Parameter_Collections.TAG_JSON_ERROR_MESSAGE);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}catch (NullPointerException e){
//					Log.e("OLX errror = " , e.getMessage().toString() );
					Toast.makeText(getApplicationContext(), "OLX error = " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
				}

				JSONObject jobj = olx_sh.json_get_jenis_outlet();

				try{
					String total_data = jobj.getString(Parameter_Collections.TAG_TOTAL_DATA);

					if(!total_data.equals("0")){
						JSONArray jsonArray = jobj.getJSONArray(Parameter_Collections.TAG_DATA);

						List<RowData_JenisOutlet> array_jenis_outlet = new ArrayList<RowData_JenisOutlet>();

						for(int i=0; i < jsonArray.length();i++){
							JSONObject c = jsonArray.getJSONObject(i);

							String id_jenis_outlet = c.getString(Parameter_Collections.TAG_ID_JENIS_OUTLET);
							String nama_jenis_outlet = c.getString(Parameter_Collections.TAG_NAMA_JENIS_OUTLET);

							Log.e("nama jenis outlet = ", nama_jenis_outlet);

							array_jenis_outlet.add(new RowData_JenisOutlet(id_jenis_outlet, nama_jenis_outlet));
						}

						Gson gson = new Gson();
						String json_jenisoutlet = gson.toJson(array_jenis_outlet);
						sh.edit().putString(Parameter_Collections.SH_STRINGSET_JENISOUTLET, json_jenisoutlet).commit();


					}
				}catch (JSONException e){

				}

			} else {
				isConnected = false;
				cMessage = "No Internet Connections";
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialogProgress.dismiss();

			if (isConnected) {
				if (cCode.equals("1")) {
					sh.edit().putBoolean(Parameter_Collections.SH_LOGGED, true)
							.commit();
					sh.edit()
							.putString(Parameter_Collections.SH_LOG_USERNAME,
									cUsername).commit();
					sh.edit()
							.putString(Parameter_Collections.SH_ID_PEGAWAI,
									cIdPegawai).commit();

					if (cJabatan.equals("Promotor")) {
						sh.edit()
								.putString(
										Parameter_Collections.SH_JABATAN_PEGAWAI,
										cJabatan).commit();
					} else {
						sh.edit()
								.putString(
										Parameter_Collections.SH_JABATAN_PEGAWAI,
										cJabatan).commit();
					}

					Intent load = new Intent(getApplicationContext(),
							Olx_MenuUtama_WP.class);
					startActivity(load);
					finish();

				} else {
					Olx_DialogLocationConfirmation dialog = new Olx_DialogLocationConfirmation();
					dialog.setContext(getApplicationContext());
					dialog.setText(cMessage);
					dialog.setFrom(2);
					dialog.setCancelable(false);
					dialog.show(getSupportFragmentManager(), "");

					// Toast.makeText(getApplicationContext(),
					// cMessage,
					// Toast.LENGTH_LONG).show();
				}
			}
		}

	}
}
