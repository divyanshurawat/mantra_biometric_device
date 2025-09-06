import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'mantra_biometric_device_method_channel.dart';

abstract class MantraBiometricDevicePlatform extends PlatformInterface {
  /// Constructs a MantraBiometricDevicePlatform.
  MantraBiometricDevicePlatform() : super(token: _token);

  static final Object _token = Object();

  static MantraBiometricDevicePlatform _instance = MethodChannelMantraBiometricDevice();

  /// The default instance of [MantraBiometricDevicePlatform] to use.
  ///
  /// Defaults to [MethodChannelMantraBiometricDevice].
  static MantraBiometricDevicePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [MantraBiometricDevicePlatform] when
  /// they register themselves.
  static set instance(MantraBiometricDevicePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
