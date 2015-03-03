package com.grappes.sandwedgesadmin;
import android.app.Application;

import com.parse.Parse;
import com.parse.ParsePush;
import com.parse.PushService;
 
public class App extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
 
    Parse.initialize(this, "rLDuBNajsyienDD24YTTCKUuJN2S0sdPdtSy9wIe", "P1bKXK9sdtwWT3Nd3fTVnVu2zrC0fcGLLBalyXS2");
    PushService.setDefaultPushCallback(this, OrderActivity.class);
    ParsePush.subscribeInBackground("admin");
  }
}
