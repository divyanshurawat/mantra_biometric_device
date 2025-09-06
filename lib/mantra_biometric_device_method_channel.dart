import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'mantra_biometric_device_platform_interface.dart';

/// An implementation of [MantraBiometricDevicePlatform] that uses method channels.
class MethodChannelMantraBiometricDevice extends MantraBiometricDevicePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('mantra_biometric_device');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
