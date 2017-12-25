package ma.carpooli.carpooli.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import timber.log.Timber;

/**
 * Created by Ahmed MOULOU on 20/04/2017.
 */

public class PreferencesHelper {

    private static final String LANGUAGE_KEY = "language_key";
    public static final String DEVICE_ID_KEY = "DEVICE_ID_KEY";
    public static final String ARABIC_LANGUAGE_CODE = "ar";
    public static final String FRENCH_LANGUAGE_CODE = "fr";
    public static final String HASLAUNCHEDONCE = "HasLaunchedOnce";
    public static final String FIREBASEHTTPURL = "https://us-central1-aui-carpo.cloudfunctions.net/";

    private String deviceID;

    public static PreferencesHelper getInstance() {
        return sInstance;
    }

    private static PreferencesHelper sInstance = null;
    private final SharedPreferences mPreference;

    private PreferencesHelper(Context context) {
        mPreference = context.getSharedPreferences("carpooli_prefs", Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (sInstance == null)
            sInstance = new PreferencesHelper(context);
    }

    public static void setLanguage(String language) {
        setPreference(LANGUAGE_KEY, language);
    }

    public static String getCurrentLanguage() {
        return getPreference(LANGUAGE_KEY, FRENCH_LANGUAGE_CODE);
    }

    public static boolean isHasLaunchedOnce(){
        return getPreference(HASLAUNCHEDONCE, false);
    }

    public static void setHasLaunchedOnce(){
        setPreference(HASLAUNCHEDONCE, true);
    }

    public static void setPreference(String key, String value) {
        sInstance.mPreference.edit().putString(key, value).commit();
    }

    public static void setPreference(String key, boolean value) {
        sInstance.mPreference.edit().putBoolean(key, value).commit();
    }

    public static String getPreference(String key, String defValue) {
        return sInstance.mPreference.getString(key, defValue);
    }

    public static boolean getPreference(String key, boolean defValue) {
        return sInstance.mPreference.getBoolean(key, defValue);
    }

    public void initDeviceId(Context context){
        deviceID = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Timber.i("device id %s",deviceID);
    }
    public String getDeviceId(){
        if (deviceID != null)
            return deviceID;
        return "";
    }
}
