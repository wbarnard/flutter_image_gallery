package com.digiatek.flutter_image_gallery;

import android.content.ContentValues;
import android.provider.MediaStore;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterImageGalleryPlugin */
public class FlutterImageGalleryPlugin implements MethodCallHandler {

  private final Registrar registrar;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_image_gallery");
    channel.setMethodCallHandler(new FlutterImageGalleryPlugin(registrar));
  }

  FlutterImageGalleryPlugin(Registrar registrar) {
    this.registrar = registrar;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("addImage")) {
      String imageFilename = call.argument("imageFilename");
      ContentValues values = new ContentValues();

      values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
      values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
      values.put(MediaStore.MediaColumns.DATA, imageFilename);

      registrar.context().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

      result.success(true);
    } else {
      result.notImplemented();
    }
  }
}
