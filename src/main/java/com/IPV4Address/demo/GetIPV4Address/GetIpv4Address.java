package com.IPV4Address.demo.GetIPV4Address;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class GetIpv4Address {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                // Loopback ve down durumda olanları ve sanal ağ arayüzlerini atlıyoruz
                if (networkInterface.isLoopback() || !networkInterface.isUp() || !isVirtualNetworkInterface(networkInterface)) {
                    continue;
                }

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();

                    // IPv4 adreslerini kontrol ediyoruz ve sadece belirli bir alt ağa ait olanları alıyoruz
                    if (address instanceof Inet4Address) {
                        String ipAddress = address.getHostAddress();
                        if (!networkInterface.getDisplayName().toLowerCase().contains("host-only")){
                            if (ipAddress.startsWith("192.168.")) {
                                System.out.println("Display name: " + networkInterface.getDisplayName().toLowerCase());
                                System.out.println("Name: " + networkInterface.getName().toLowerCase());
                                System.out.println("Yerel IPv4 Adresi: " + ipAddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    // Sanal ağ arayüzlerini tanımlamak için bir yöntem
    private static boolean isVirtualNetworkInterface(NetworkInterface networkInterface) {
        try {
            // Ağ arayüzü isimlerini küçük harfe çevirerek al
            String displayName = networkInterface.getDisplayName().toLowerCase();
            String name = networkInterface.getName().toLowerCase();

            // Enum içindeki isimlerle kontrol et
            for (NetworkInterfaceName value : NetworkInterfaceName.values()) {
                if (displayName.contains(value.getName()) || name.contains(value.getName())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
