package com.gaspardbruno.staticsafeareainsets;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.WritableMap;

import java.util.Map;
import java.util.HashMap;

import android.view.WindowInsets;
import android.view.View;
import android.os.Build;
import android.app.Activity;

public class RNStaticSafeAreaInsetsModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNStaticSafeAreaInsetsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNStaticSafeAreaInsets";
  }

  @Override
  public Map<String, Object> getConstants() {
    return this._getSafeAreaInsets();
  }

  private Map<String, Object> _getSafeAreaInsets() {
    final Map<String, Object> constants = new HashMap<>();

    if (getCurrentActivity() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      final Activity activity = getCurrentActivity();
      final View view = activity.getWindow().getDecorView();
      final WindowInsets insets = view.getRootWindowInsets();

      constants.put("safeAreaInsetsTop", insets.getSystemWindowInsetTop());
      constants.put("safeAreaInsetsBottom", insets.getSystemWindowInsetBottom());
      constants.put("safeAreaInsetsLeft", insets.getSystemWindowInsetLeft());
      constants.put("safeAreaInsetsRight", insets.getSystemWindowInsetRight());
    } else {
      constants.put("safeAreaInsetsTop", 0);
      constants.put("safeAreaInsetsBottom", 0);
      constants.put("safeAreaInsetsLeft", 0);
      constants.put("safeAreaInsetsRight", 0);
    }

    return constants;
  }

  @ReactMethod
  public void getSafeAreaInsets(Callback cb) {
    // Approach A
    // Map<String, Object> constants = this._getSafeAreaInsets();
    // WritableMap map = new WritableNativeMap();

    // map.putInt("safeAreaInsetsTop", constants.safeAreaInsetsTop);
    // map.putInt("safeAreaInsetsBottom", constants.safeAreaInsetsBottom);
    // map.putInt("safeAreaInsetsLeft", constants.safeAreaInsetsLeft);
    // map.putInt("safeAreaInsetsRight", constants.safeAreaInsetsRight);

    // cb.invoke(map);

    // Approach B
    WritableMap map = new WritableNativeMap();

    if (getCurrentActivity() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      final Activity activity = getCurrentActivity();
      final View view = activity.getWindow().getDecorView();
      final WindowInsets insets = view.getRootWindowInsets();

      map.putInt("safeAreaInsetsTop", insets.getSystemWindowInsetTop());
      map.putInt("safeAreaInsetsBottom", insets.getSystemWindowInsetBottom());
      map.putInt("safeAreaInsetsLeft", insets.getSystemWindowInsetLeft());
      map.putInt("safeAreaInsetsRight", insets.getSystemWindowInsetRight());
    } else {
      map.putInt("safeAreaInsetsTop", 0);
      map.putInt("safeAreaInsetsBottom", 0);
      map.putInt("safeAreaInsetsLeft", 0);
      map.putInt("safeAreaInsetsRight", 0);
    }

    cb.invoke(map);
  }
}
