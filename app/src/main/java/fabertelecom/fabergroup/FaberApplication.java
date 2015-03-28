package fabertelecom.fabergroup;

import android.app.Application;
import fabertelecom.fabergroup.Clients.APIClient;

public class FaberApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        APIClient.setupAuth(this);
    }
}
