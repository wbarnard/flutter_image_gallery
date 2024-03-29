import 'dart:async';

import 'package:flutter/services.dart';

class FlutterImageGallery {
  static const MethodChannel _channel =
      const MethodChannel('flutter_image_gallery');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  Future<bool> addImage(String imageFilename) async {
    print('FlutterImageGallery:addImage $imageFilename');
    return await _channel.invokeMethod(
      'addImage',
      <String, String>{'imageFilename': imageFilename},
    );
  }
}
