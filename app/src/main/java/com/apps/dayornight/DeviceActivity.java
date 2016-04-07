package com.apps.dayornight;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.utils.DeviceUtils;

public class DeviceActivity extends BaseActivity {

    private TextView deviceIdTV;
    private TextView versionCodeTV;
    private TextView versionNameTV;
    private TextView phoneBrandTV;
    private TextView phoneModelTV;
    private TextView apiLevelTV;
    private TextView apiVersionTV;
    private TextView appProcessIdTV;
    private TextView appNameTV;
    private TextView metaDataTV;

    private LinearLayout layout;

    public static final int REQUEST_READ_PHONE_STATE = 61;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        deviceIdTV = (TextView) findViewById(R.id.device_id_tv);
        versionCodeTV = (TextView) findViewById(R.id.version_code_tv);
        versionNameTV = (TextView) findViewById(R.id.version_name_tv);
        phoneBrandTV = (TextView) findViewById(R.id.phone_brand_tv);
        phoneModelTV = (TextView) findViewById(R.id.phone_model_tv);
        apiLevelTV = (TextView) findViewById(R.id.phone_api_level_tv);
        apiVersionTV = (TextView) findViewById(R.id.phone_api_version_tv);
        appProcessIdTV = (TextView) findViewById(R.id.app_process_id_tv);
        appNameTV = (TextView) findViewById(R.id.app_name_tv);
        metaDataTV = (TextView) findViewById(R.id.meta_data_tv);

        layout = (LinearLayout)findViewById(R.id.layout);

        init();
    }


    private void init(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        REQUEST_READ_PHONE_STATE);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            setData();
        }
    }

    private void setData(){
        deviceIdTV.setText(DeviceUtils.getDeviceId(this));
        versionCodeTV.setText(DeviceUtils.getVersionCode(this));
        versionNameTV.setText(DeviceUtils.getVersionName(this));
        phoneBrandTV.setText(DeviceUtils.getPhoneBrand());
        phoneModelTV.setText(DeviceUtils.getPhoneModel());
        apiLevelTV.setText(DeviceUtils.getBuildLevel() + "");
        apiVersionTV.setText(DeviceUtils.getBuildVersion());
        appProcessIdTV.setText(DeviceUtils.getAppProcessId() + "");
        appNameTV.setText(DeviceUtils.getAppProcessName(this, DeviceUtils.getAppProcessId()));
        metaDataTV.setText(DeviceUtils.getMetaData(this, "DEBUG"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_READ_PHONE_STATE:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    setData();
                }else{
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Snackbar.make(layout,"no permission to obtain data",Snackbar.LENGTH_LONG).show();
                }
                // other 'case' lines to check for other
                // permissions this app might request
            }
        }
    }

}
