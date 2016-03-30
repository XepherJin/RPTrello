package com.retropoktan.rptrello.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;

/**
 * PreferencesUtils, easy to get or put data. Call {@link #init(String)} first to init the PreferenceName if needed.
 * <ul>
 * <strong>Preference Name</strong>
 * <li>you must set preference name by {@link #init(String)} first!</li>
 * </ul>
 * <ul>
 * <strong>Put Value</strong>
 * <li>put string {@link #putString(Context, String, String)}</li>
 * <li>put int {@link #putInt(Context, String, int)}</li>
 * <li>put long {@link #putLong(Context, String, long)}</li>
 * <li>put float {@link #putFloat(Context, String, float)}</li>
 * <li>put boolean {@link #putBoolean(Context, String, boolean)}</li>
 * </ul>
 * <ul>
 * <strong>Get Value</strong>
 * <li>get string {@link #getString(Context, String)},
 * {@link #getString(Context, String, String)}</li>
 * <li>get int {@link #getInt(Context, String)},
 * {@link #getInt(Context, String, int)}</li>
 * <li>get long {@link #getLong(Context, String)},
 * {@link #getLong(Context, String, long)}</li>
 * <li>get float {@link #getFloat(Context, String)},
 * {@link #getFloat(Context, String, float)}</li>
 * <li>get boolean {@link #getBoolean(Context, String)},
 * {@link #getBoolean(Context, String, boolean)}</li>
 * </ul>
 */
public class PreferencesUtil {

    private static String PREFERENCE_NAME;

    private PreferencesUtil() {

    }

    /**
     * init the <strong>PreferencesUtil</strong> with a name if needed.
     *
     * @param preferenceName the name set for the preferences, which can't be null.
     */
    public static void init(String preferenceName) {
        if (PREFERENCE_NAME == null && !TextUtils.isEmpty(preferenceName)) {
            PREFERENCE_NAME = preferenceName;
        } else {
            throw new RuntimeException("Preference name set error!");
        }
    }

    /**
     * get the {@link #PREFERENCE_NAME}
     *
     * @return {@link #PREFERENCE_NAME} of the preferences
     */
    public static String getPreferenceName() {
        return PREFERENCE_NAME;
    }

    /**
     * put string preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value <b>encoded with Base64</b> for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putStringWithBase64Encoding(Context context, String key, String value) {
        if (context == null) {
            return false;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key,
                Base64.encodeToString(value.getBytes(), Base64.URL_SAFE));
        return editor.commit();
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value <b>decoded with Base64</b> if it exists, or null. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     * @see #getString(Context, String, String)
     */
    public static String getBase64DecodedString(Context context, String key) {
        return getBase64DecodedString(context, key, null);
    }

    /**
     * put string preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putString(Context context, String key, String value) {
        if (context == null) {
            return false;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     * @see #getString(Context, String, String)
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     */
    public static String getString(Context context, String key,
                                   String defaultValue) {
        if (context == null) {
            return null;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return settings.getString(key, defaultValue);
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value <b>decoded with Base64</b> if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     */
    public static String getBase64DecodedString(Context context, String key,
                                                String defaultValue) {
        if (context == null) {
            return null;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        if (settings.getString(key, null) != null) {
            return new String(Base64.decode(
                    settings.getString(key, defaultValue), Base64.URL_SAFE));
        }
        return defaultValue;
    }

    /**
     * put int preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putInt(Context context, String key, int value) {
        if (context == null) {
            return false;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a int
     * @see #getInt(Context, String, int)
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a int
     */
    public static int getInt(Context context, String key, int defaultValue) {
        if (context == null) {
            return 0;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return settings.getInt(key, defaultValue);
    }

    /**
     * put long preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putLong(Context context, String key, long value) {
        if (context == null) {
            return false;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long
     * @see #getLong(Context, String, long)
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1L);
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long
     */
    public static long getLong(Context context, String key, long defaultValue) {
        if (context == null) {
            return 0L;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putFloat(Context context, String key, float value) {
        if (context == null) {
            return false;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a float
     * @see #getFloat(Context, String, float)
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1.f);
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a float
     */
    public static float getFloat(Context context, String key,
                                 float defaultValue) {
        if (context == null) {
            return 0.f;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        if (context == null) {
            return false;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean preferences, default is false
     *
     * @param context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or false. Throws
     * ClassCastException if there is a preference with this name that
     * is not a boolean
     * @see #getBoolean(Context, String, boolean)
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * get boolean preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a boolean
     */
    public static boolean getBoolean(Context context, String key,
                                     boolean defaultValue) {
        if (context == null) {
            return false;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return settings.getBoolean(key, defaultValue);
    }

    /**
     * clear all preferences data
     *
     * @param context
     * @return True if data are all cleared.
     */
    public static boolean clearAll(Context context) {
        if (context == null) {
            return false;
        }
        SharedPreferences settings;
        if (TextUtils.isEmpty(PREFERENCE_NAME)) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            settings = context.getSharedPreferences(
                    PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        return editor.commit();
    }
}
