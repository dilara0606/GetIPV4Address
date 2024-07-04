package com.IPV4Address.demo.GetIPV4Address;

import lombok.Getter;

@Getter
public enum NetworkInterfaceName {
    ETHERNET("ethernet"),
    ETHERNET_ADAPTER("ethernet adapter"),
    WIFI("wi-fi"),
    WIRELESS("wireless"),
    WLAN("wlan"),
    ETH0("eth0"),
    ETH1("eth1"),
    AIRPORT("airport");

    private final String name;

    NetworkInterfaceName(String name) {
        this.name = name;
    }
}
