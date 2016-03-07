package id.tech.verificareolx;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.tech.util.Olx_ServiceHandlerJSON;
import id.tech.util.Parameter_Collections;
import id.tech.util.Public_Functions;
import id.tech.util.RowData_JenisOutlet;

/**
 * Created by RebelCreative-A1 on 04/03/2016.
 */
public class Olx_Service_UpdateJenisOutlet extends Service {
    Intent myIntent;
    PendingIntent alarmIntent;
    AlarmManager alarams;
    SharedPreferences spf;
    String cContactId;
    Intent activate;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        spf =  getSharedPreferences(Parameter_Collections.SH_NAME, Context.MODE_PRIVATE);

//		new AsyncTas_RegistedGCM().execute();

    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Log.e(TAG, "Error");
            } else {
                Log.i(TAG, "This device is not supported.");

            }
            return false;
        }
        return true;
    }

    private class AsyncTas_RegistedGCM extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Olx_ServiceHandlerJSON olx_sh = new Olx_ServiceHandlerJSON();
                JSONObject jobj = olx_sh.json_get_jenis_outlet();
                String total_data = jobj.getString(Parameter_Collections.TAG_TOTAL_DATA);

                if(!total_data.equals("0")) {
                    JSONArray jsonArray = jobj.getJSONArray(Parameter_Collections.TAG_DATA);

                    List<RowData_JenisOutlet> array_jenis_outlet = new ArrayList<RowData_JenisOutlet>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);

                        String id_jenis_outlet = c.getString(Parameter_Collections.TAG_ID_JENIS_OUTLET);
                        String nama_jenis_outlet = c.getString(Parameter_Collections.TAG_NAMA_JENIS_OUTLET);

                        Log.e("nama jenis outlet = ", nama_jenis_outlet);
                        array_jenis_outlet.add(new RowData_JenisOutlet(id_jenis_outlet, nama_jenis_outlet));
                    }

                    Gson gson = new Gson();
                    String json_jenisoutlet = gson.toJson(array_jenis_outlet);
                    spf.edit().putString(Parameter_Collections.SH_STRINGSET_JENISOUTLET, json_jenisoutlet).commit();

                }
            }catch (JSONException e){

            }catch (Exception e){

            }




            return null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        myIntent = intent;

        Calendar c = Calendar.getInstance();

        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 113, activate,PendingIntent.FLAG_CANCEL_CURRENT);

        alarams = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarams.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                (1000 * 60 * 60) * 23, alarmIntent);
        Log.e("Service Started", "update Jenis Outlet");

        new AsyncTas_RegistedGCM().execute();

       return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

}

