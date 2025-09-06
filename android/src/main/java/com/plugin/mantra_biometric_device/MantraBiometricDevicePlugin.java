package com.plugin.mantra_biometric_device;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** MantraBiometricDevicePlugin */
public class MantraBiometricDevicePlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

  private MethodChannel channel;
  private Context context;
  private Activity act;
  private Result result;

  // ------------------ FlutterPlugin ------------------
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    context = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "mantra_biometric");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  // ------------------ ActivityAware ------------------
  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    act = binding.getActivity();
    binding.addActivityResultListener(this::handleActivityResult);
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    act = null;
  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
    act = binding.getActivity();
    binding.addActivityResultListener(this::handleActivityResult);
  }

  @Override
  public void onDetachedFromActivity() {
    act = null;
  }

  // ------------------ MethodCallHandler ------------------
  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    this.result = result;

    switch (call.method) {
      case "getDeviceInfo":
        try {
          Intent intent = new Intent();
          intent.setAction("in.gov.uidai.rdservice.fp.INFO");
          act.startActivityForResult(intent, 1);
        } catch (Exception e) {
          result.error("ClientNotFound", e.getMessage(), "Install Client Application");
        }
        break;

      case "capture":
        try {
          String arguments = call.argument("pidOptions");
          Intent intent2 = new Intent();
          intent2.setAction("in.gov.uidai.rdservice.fp.CAPTURE");
          intent2.putExtra("PID_OPTIONS", arguments);
          act.startActivityForResult(intent2, 2);
        } catch (Exception e) {
          result.error("ClientNotFound", e.getMessage(), "Install Client Application");
        }
        break;

      default:
        result.notImplemented();
        break;
    }
  }

  // ------------------ Activity Result Handling ------------------
  private boolean handleActivityResult(int requestCode, int resultCode, Intent data) {
    if (result == null) return false;

    try {
      switch (requestCode) {
        case 1: // getDeviceInfo
          if (resultCode == Activity.RESULT_OK && data != null) {
            String rdService = data.getStringExtra("RD_SERVICE_INFO");
            if (rdService != null) {
              result.success(rdService);
            } else {
              result.error("Error", "Unable to fetch device information", "RD_SERVICE_INFO is null");
            }
          }
          break;

        case 2: // capture
          if (resultCode == Activity.RESULT_OK && data != null) {
            String pidData = data.getStringExtra("PID_DATA");
            if (pidData != null) {
              result.success(pidData);
            } else {
              result.error("Error", "Received PID_DATA null", "PID_DATA is null");
            }
          }
          break;
      }
    } catch (Exception e) {
      result.error("Error", "Something went wrong", e.getMessage());
    }

    return true; // consumed
  }

  // ------------------ Optional helper ------------------
  public static JSONObject mapToJson(Map<?, ?> data) {
    JSONObject object = new JSONObject();
    for (Map.Entry<?, ?> entry : data.entrySet()) {
      String key = (String) entry.getKey();
      if (key == null) throw new NullPointerException("key == null");
      try {
        object.put(key, wrap(entry.getValue()));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return object;
  }

  // Helper method to wrap object for JSONObject
  private static Object wrap(Object o) {
    if (o == null) return JSONObject.NULL;
    return o;
  }
}