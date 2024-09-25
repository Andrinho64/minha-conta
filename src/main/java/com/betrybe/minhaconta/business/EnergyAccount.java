package com.betrybe.minhaconta.business;

import com.ions.lightdealer.sdk.model.Address;
import com.ions.lightdealer.sdk.model.Client;
import com.ions.lightdealer.sdk.model.ElectronicDevice;


/**
 * The type Energy account.
 */
public class EnergyAccount {

  Client client;

  public EnergyAccount(Client client) {
    this.client = client;
  }

  /**
   * Req. 11 – Find high consumption device per address.
   */
  public ElectronicDevice[] findHighConsumptionDevices() {
    Address[] addresses = client.getAddressesAsArray();
    ElectronicDevice[] highConsumptionDevices = new ElectronicDevice[addresses.length];

    for (int i = 0; i < addresses.length; i++) {
      Address address = addresses[i];
      ElectronicDevice[] devices = address.getDevicesAsArray();

      if (devices == null || devices.length == 0) {
        highConsumptionDevices[i] = null;
        continue;
      }

      ElectronicDevice maxDevice = devices[0];
      for (ElectronicDevice device : devices) {
        if (device.monthlyKwh() > maxDevice.monthlyKwh()) {
          maxDevice = device;
        }
      }
      highConsumptionDevices[i] = maxDevice;
    }

    return highConsumptionDevices;
  }
}
