package fabertelecom.fabergroup.Clients;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class APIClient
{
    private static final String API_URL = "http://faberapp.herokuapp.com";
    public static final String KEY_USER_EMAIL = "user_email";
    public static final String KEY_USER_TOKEN = "user_token";
    private static FaberAPIInterface client;
    private static String token;
    private static String email;

    public static FaberAPIInterface getInstance()
    {
        if (client == null) {
            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    if (token != null && email != null) {
                        request.addHeader("X-User-Email", APIClient.email);
                        request.addHeader("X-User-Token", APIClient.token);
                    }
                }
            };
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .setRequestInterceptor(requestInterceptor)
                    .build();
            client = restAdapter.create(FaberAPIInterface.class);
        }
        return client;
    }

    public static Boolean isLogged(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String email = preferences.getString(KEY_USER_EMAIL, null);
        String token = preferences.getString(KEY_USER_TOKEN, null);
        return email != null && token != null;
    }

    public static void setupAuth(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        APIClient.email = preferences.getString(KEY_USER_EMAIL, null);
        APIClient.token = preferences.getString(KEY_USER_TOKEN, null);
    }

    public static void saveAuth(String email, String token, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USER_TOKEN, token);
        editor.apply();
        APIClient.token = token;
        APIClient.email = email;
    }

    public static void clearAuth(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_USER_EMAIL);
        editor.remove(KEY_USER_TOKEN);
        editor.apply();
    }
}