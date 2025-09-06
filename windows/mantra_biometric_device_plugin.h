#ifndef FLUTTER_PLUGIN_MANTRA_BIOMETRIC_DEVICE_PLUGIN_H_
#define FLUTTER_PLUGIN_MANTRA_BIOMETRIC_DEVICE_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace mantra_biometric_device {

class MantraBiometricDevicePlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  MantraBiometricDevicePlugin();

  virtual ~MantraBiometricDevicePlugin();

  // Disallow copy and assign.
  MantraBiometricDevicePlugin(const MantraBiometricDevicePlugin&) = delete;
  MantraBiometricDevicePlugin& operator=(const MantraBiometricDevicePlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace mantra_biometric_device

#endif  // FLUTTER_PLUGIN_MANTRA_BIOMETRIC_DEVICE_PLUGIN_H_
