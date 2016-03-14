package id.tech.util;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Olx_ServiceHandlerJSON {
	private List<NameValuePair> params;
	private JSONObject jObj;
	private String result, url;
	private DefaultHttpClient hClient;
	private HttpEntity hEntity;
	private HttpResponse hResponse;

	private String empty_json_prefix = "{json_code: \"0\",error_message: \"";
	private String empty_json_end = "\"}";

	public Olx_ServiceHandlerJSON() {
		// TODO Auto-generated constructor stub
		hClient = new DefaultHttpClient();
		hEntity = null;
		hResponse = null;
		params = null;
	}

	public JSONObject json_login(String username, String password){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_LOGIN_MOBILE));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_USERNAME, username));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_PASSWORD, password));
			
			url = Parameter_Collections.URL_LOGIN + URLEncodedUtils.format(params, Parameter_Collections.UTF);
			
			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
//			hEntity = null;
			result = EntityUtils.toString(hEntity);
			
			jObj = new JSONObject(result);
		}catch (JSONException e) {
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
			return jObj;
		} catch (UnsupportedEncodingException e) {
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
			return jObj;
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
			return jObj;
		} catch (IOException e) {
			// TODO: handle exception
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
			return jObj;
		} catch (NullPointerException e) {
			// TODO: handle exception
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
			return jObj;

		}catch (Exception e) {
			// TODO: handle exception
			Log.e("Exception = ", e.getMessage().toString());
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
			return jObj;

		}
		if(jObj == null){
			try{
				jObj = new JSONObject(empty_json_prefix + "Null From Server, Please Try again" + empty_json_end);
			}catch (JSONException ee){

			}
		}
		return jObj;
	}

	public JSONObject json_cek_notif(){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_NOTIF));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_NOTIF_TODAY, Parameter_Collections.TAG_NOTIF_TODAY_TRUE));
			
			url = Parameter_Collections.URL_GET + URLEncodedUtils.format(params, Parameter_Collections.UTF);
			
			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);

			Log.e("url", url);
			Log.e("result", result);
			jObj = new JSONObject(result);
		}catch (JSONException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (ClientProtocolException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		return jObj;
	}
	
	public JSONObject json_get_allnotif(){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_NOTIF));
			
			url = Parameter_Collections.URL_GET + URLEncodedUtils.format(params, Parameter_Collections.UTF);
			
			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);

//			Log.e("url", url);
//			Log.e("result", result);
			jObj = new JSONObject(result);
		}catch (JSONException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (ClientProtocolException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		return jObj;
	}
	

	public JSONObject json_get_pic_profile(String id_pegawai){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_PIC_PROFILE));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_ID_PEGAWAI, id_pegawai));

			url = Parameter_Collections.URL_GET+ URLEncodedUtils.format(params, Parameter_Collections.UTF);

			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);

			jObj = new JSONObject(result);
		}catch (JSONException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (ClientProtocolException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		return jObj;
	}
	
	public JSONObject json_get_pic_profile_target(String id_pegawai){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_TARGET_PROFILE));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_ID_PEGAWAI, id_pegawai));
						
			url = Parameter_Collections.URL_GET+ URLEncodedUtils.format(params, Parameter_Collections.UTF);
			
			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);
			
			jObj = new JSONObject(result);
		}catch (JSONException e) {
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		} catch (UnsupportedEncodingException e) {
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		} catch (IOException e) {
			// TODO: handle exception
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		}catch (Exception e){
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		}

		if(jObj == null){
			try{
				jObj = new JSONObject(empty_json_prefix + "Null From Server, Please Try again" + empty_json_end);
			}catch (JSONException ee){

			}
		}
		return jObj;
	}
	
	public JSONObject json_get_target_info(String id_pegawai){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_TARGET));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_ID_PEGAWAI, id_pegawai));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_KODE_TODAY, "true"));
						
			url = Parameter_Collections.URL_GET+ URLEncodedUtils.format(params, Parameter_Collections.UTF);
			
			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);
			
			jObj = new JSONObject(result);
		}catch (JSONException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (ClientProtocolException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		return jObj;
	}



	public JSONObject json_get_visit_store(String id_pegawai){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_INSERT_VISITLIST));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_ID_PEGAWAI, id_pegawai));

			url = Parameter_Collections.URL_GET+ URLEncodedUtils.format(params, Parameter_Collections.UTF);

			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);

			jObj = new JSONObject(result);
		}catch (JSONException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (ClientProtocolException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		return jObj;
	}


	public JSONObject json_insert_stock(String id_pegawai,String id_toko, String id_stock,
										String stock_qty_warehouse, String stock_qty_display,String note,
										String latitude_stock_store, String longitude_stock_store){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_STOCK_STORE));
			params.add(new BasicNameValuePair(Parameter_Collections.SH_ID_PEGAWAI, id_pegawai));
			params.add(new BasicNameValuePair(Parameter_Collections.SH_ID_TOKO, id_toko));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_ID_STOCK, id_stock));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_WAREHOUSE_STOCK, stock_qty_warehouse));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_DISPLAY_STOCK, stock_qty_display));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_NOTE, note));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_LAT_STOCK_STORE, latitude_stock_store));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_LONG_STOCK_STORE, longitude_stock_store));

			url = Parameter_Collections.URL_INSERT+ URLEncodedUtils.format(params, Parameter_Collections.UTF);

//			HttpGet hGet = new HttpGet(url);
			HttpPost hPost = new HttpPost(url);
//			hResponse = hClient.execute(hGet);
			hResponse = hClient.execute(hPost);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);

			jObj = new JSONObject(result);
			Log.e("input stok", jObj.toString());
		}catch (JSONException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (ClientProtocolException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		return jObj;
	}



	public JSONObject json_get_history(String id_pegawai){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_HISTORY));
			params.add(new BasicNameValuePair(Parameter_Collections.SH_ID_PEGAWAI, id_pegawai));
			url = Parameter_Collections.URL_GET+ URLEncodedUtils.format(params, Parameter_Collections.UTF);

			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);

			Log.e("Result json = ", result);
			jObj = new JSONObject(result);
		}catch (JSONException e) {
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		} catch (UnsupportedEncodingException e) {
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		} catch (IOException e) {
			// TODO: handle exception
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		}catch (Exception e){
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		}
		if(jObj == null){
			try{
				jObj = new JSONObject(empty_json_prefix + "Null From Server, Please Try again" + empty_json_end);
			}catch (JSONException ee){

			}
		}
		return jObj;
	}



	public JSONObject json_register_GCM_to_Server(String device_token , String device_unique_id ){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_DEVICE));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_DEVICE_TOKEN, device_token));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_DEVICE_ACTIVE, "1"));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_DEVICE_UNIQUE_ID, device_unique_id));

			url = Parameter_Collections.URL_INSERT+ URLEncodedUtils.format(params, Parameter_Collections.UTF);

			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);

			jObj = new JSONObject(result);
		}catch (JSONException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (ClientProtocolException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		return jObj;
	}


	public JSONObject json_update_dataoutlet( String alamat_outlet,
											 String telepon_outlet, String id_jenis_outlet,
											 String region_outlet, String lati, String longi, String nama_outlet, String email){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_OUTLET));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_NAMA_OUTLET, nama_outlet));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_ALAMAT_OUTLET, alamat_outlet));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_TELEPON_OUTLET, telepon_outlet));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_ID_JENIS_OUTLET, id_jenis_outlet));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_REGION_OUTLET, region_outlet));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_LATITUDE_OUTLET, lati));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_LONGITUDE_OUTLET, longi));
			params.add(new BasicNameValuePair(Parameter_Collections.TAG_EMAIL_OUTLET, email));

			url = Parameter_Collections.URL_INSERT
					+ URLEncodedUtils.format(params, Parameter_Collections.UTF);

			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);

//			Log.e("url", url);
//			Log.e("result", result);
			jObj = new JSONObject(result);
		}catch (JSONException e) {
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}

		} catch (UnsupportedEncodingException e) {
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}

		} catch (ClientProtocolException e) {
			// TODO: handle exception
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		} catch (IOException e) {
			// TODO: handle exception
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		}catch (Exception e){
			try{
				jObj = new JSONObject(empty_json_prefix + e.getMessage().toString() + empty_json_end);
			}catch (JSONException ee){

			}
		}

		if(jObj == null){
			try{
				jObj = new JSONObject(empty_json_prefix + "Null From Server, Please Try again" + empty_json_end);
			}catch (JSONException ee){

			}
		}
		return jObj;
	}

	public JSONObject json_get_jenis_outlet(){
		try{
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Parameter_Collections.KIND, Parameter_Collections.KIND_JENIS_OUTLET));
			url = Parameter_Collections.URL_GET+ URLEncodedUtils.format(params, Parameter_Collections.UTF);

			HttpGet hGet = new HttpGet(url);
			hResponse = hClient.execute(hGet);
			hEntity = hResponse.getEntity();
			result = EntityUtils.toString(hEntity);

			jObj = new JSONObject(result);
			Log.e("jenis outlet = ", jObj.toString());
		}catch (JSONException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (ClientProtocolException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		return jObj;
	}
}
