import 'package:flutter_test/flutter_test.dart';
import 'package:mantra_biometric_device/mantra_biometric_device.dart';
import 'package:mantra_biometric_device/mantra_biometric_device_platform_interface.dart';
import 'package:mantra_biometric_device/mantra_biometric_device_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockMantraBiometricDevicePlatform
    with MockPlatformInterfaceMixin
    implements MantraBiometricDevicePlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final MantraBiometricDevicePlatform initialPlatform = MantraBiometricDevicePlatform.instance;

  test('$MethodChannelMantraBiometricDevice is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelMantraBiometricDevice>());
  });

  test('getPlatformVersion', () async {
    MantraBiometricDevice mantraBiometricDevicePlugin = MantraBiometricDevice();
    MockMantraBiometricDevicePlatform fakePlatform = MockMantraBiometricDevicePlatform();
    MantraBiometricDevicePlatform.instance = fakePlatform;

    expect(await mantraBiometricDevicePlugin.getPlatformVersion(), '42');
  });
}
