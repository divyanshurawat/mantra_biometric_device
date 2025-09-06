
import 'mantra_biometric_device_platform_interface.dart';

class MantraBiometricDevice {
  Future<String?> getPlatformVersion() {
    return MantraBiometricDevicePlatform.instance.getPlatformVersion();
  }
}
