package com.grappes.sandwedgesadmin;
import android.app.Application;

import com.parse.Parse;
import com.parse.ParsePush;
import com.parse.PushService;
 
public class App extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
 
    Parse.initialize(this, "","");
    PushService.setDefaultPushCallback(this, OrderActivity.class);
    ParsePush.subscribeInBackground("admin");
  }
}
