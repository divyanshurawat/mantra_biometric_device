// In order to *not* need this ignore, consider extracting the "web" version
// of your plugin as a separate package, instead of inlining it in the same
// package as the core of your plugin.
// ignore: avoid_web_libraries_in_flutter

import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:web/web.dart' as web;

import 'mantra_biometric_device_platform_interface.dart';

/// A web implementation of the MantraBiometricDevicePlatform of the MantraBiometricDevice plugin.
class MantraBiometricDeviceWeb extends MantraBiometricDevicePlatform {
  /// Constructs a MantraBiometricDeviceWeb
  MantraBiometricDeviceWeb();

  static void registerWith(Registrar registrar) {
    MantraBiometricDevicePlatform.instance = MantraBiometricDeviceWeb();
  }

  /// Returns a [String] containing the version of the platform.
  @override
  Future<String?> getPlatformVersion() async {
    final version = web.window.navigator.userAgent;
    return version;
  }
}
