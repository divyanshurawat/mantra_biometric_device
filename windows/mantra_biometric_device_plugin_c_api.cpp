#include "include/mantra_biometric_device/mantra_biometric_device_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "mantra_biometric_device_plugin.h"

void MantraBiometricDevicePluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  mantra_biometric_device::MantraBiometricDevicePlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
