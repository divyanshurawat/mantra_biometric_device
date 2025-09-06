//
//  Generated file. Do not edit.
//

// clang-format off

#include "generated_plugin_registrant.h"

#include <mantra_biometric_device/mantra_biometric_device_plugin.h>

void fl_register_plugins(FlPluginRegistry* registry) {
  g_autoptr(FlPluginRegistrar) mantra_biometric_device_registrar =
      fl_plugin_registry_get_registrar_for_plugin(registry, "MantraBiometricDevicePlugin");
  mantra_biometric_device_plugin_register_with_registrar(mantra_biometric_device_registrar);
}
