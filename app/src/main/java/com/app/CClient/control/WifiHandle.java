/*
 * WifiHandle:
 * 	1.used to handle wifi Related operations
 * 
 * AUTHOR: unknown
 * CHANGED BY: Mister Guo
 * DATE: 2014.8
 */
package com.app.CClient.control;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.AuthAlgorithm;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.text.TextUtils;
import android.util.Log;


import com.app.CClient.Utlis.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class WifiHandle
{
    public static final String TAG = "WifiHandle";

    private static WifiManager wifiManager = null;
    private List<ScanResult> scanResultList = new ArrayList<ScanResult>();
    private List<WifiConfiguration> wifiConfigurationList = null;
    public List<ScanResult> scanResultwifiList = null;

    private WifiLock wifiLock = null;
    private static WifiHandle wifiHandle;
    private static Context mcontext;

    public WifiHandle(Context context)
    {
        mcontext = context;
        wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        wifiManager.getConnectionInfo();
    }

    public static WifiHandle getInstance(Context context)
    {
        if (wifiHandle == null)
        {
            wifiHandle = new WifiHandle(context);
        }
        return wifiHandle;
    }

    public void openWifi()
    {
        wifiManager.setWifiEnabled(true);
    }

    public boolean isWifiOpen()
    {
        return wifiManager.isWifiEnabled();
    }

    public void closeWifi()
    {
        if (wifiManager.isWifiEnabled())
        {
            Log.d(TAG, "########close wifi#########");
            wifiManager.setWifiEnabled(false);
        }
    }


    public boolean isWiFiActive()
    {
        ConnectivityManager connectivity = (ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] infos = connectivity.getAllNetworkInfo();
            if (infos != null)
            {
                for (NetworkInfo ni : infos)
                {
                    if (ni.getTypeName().equals("WIFI") && ni.isConnected())
                    {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public WifiManager getWifiManager()
    {
        return wifiManager;
    }

    public void createWifiLock()
    {
        wifiLock = wifiManager.createWifiLock("Machao");
    }

    public void lockWifi()
    {
        wifiLock.acquire();
    }

    public void rlockWifi()
    {
        if (wifiLock.isHeld())
        {
            wifiLock.acquire();
        }
    }

    // use network index in configurationlist connect to the network
    public void connectConfiguration(int index)
    {
        if (index > wifiConfigurationList.size())
        {
            return;
        }
        wifiManager.enableNetwork(wifiConfigurationList.get(index).networkId,
                true);
    }

    public void startScan()
    {
        Log.d(TAG, "########start scan#########");
        wifiManager.startScan();
        wifiConfigurationList = wifiManager.getConfiguredNetworks();
    }

    public List<WifiConfiguration> getWifiConfigurationList()
    {
        return wifiConfigurationList;
    }

    public List<ScanResult> getScanResultList()
    {
        wifiManager.startScan();
        scanResultList = wifiManager.getScanResults();
        List<ScanResult> mScanResultwifiList = new ArrayList<ScanResult>();
        mScanResultwifiList.clear();
        List<String> mSsids = new ArrayList<String>();
        for (int i = 0; i < scanResultList.size(); i++)
        {
            if (!mSsids.contains(scanResultList.get(i).SSID))
            {
                mSsids.add(scanResultList.get(i).SSID);
                mScanResultwifiList.add(scanResultList.get(i));
                Log.e("contains添加", scanResultList.get(i).SSID);
            } else
            {
                int index = mSsids.indexOf(scanResultList.get(i).SSID);
                Log.e("contain", index + "");
                if (mScanResultwifiList.get(index).level < scanResultList.get(i).level)
                {
                    Log.e("contain.level", mScanResultwifiList.get(index).level + ":" + scanResultList.get(i).level);
                    mScanResultwifiList.remove(index);
                    mScanResultwifiList.add(scanResultList.get(i));
                    Log.e("contains移除后添加", scanResultList.get(i).SSID);
                }
            }
        }
        Log.e("containall", mScanResultwifiList.toString());
        scanResultList.clear();
        scanResultList = mScanResultwifiList;
        return scanResultList;
    }
    public List<ScanResult> getScanResult(){
        scanResultList = wifiManager.getScanResults();
        return scanResultList;
    }

    // local MAC address
    public String getMacAddress()
    {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return (wifiInfo == null) ? "NULL" : wifiInfo.getMacAddress();
    }

    // remote AP MAC address
    public String getBSSID()
    {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return (wifiInfo == null) ? "NULL" : wifiInfo.getBSSID();
    }

    // local IP address
    public int getIPAddress()
    {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return (wifiInfo == null) ? 0 : wifiInfo.getIpAddress();
    }

    // the ID of network current connected
    public int getNetworkId()
    {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return (wifiInfo == null) ? 0 : wifiInfo.getNetworkId();
    }

    public WifiInfo getWifiInfo()
    {
        return wifiManager.getConnectionInfo();
    }

    public NetworkInfo getWifiNetworkInfo()
    {
        ConnectivityManager mConnMgr = (ConnectivityManager) mcontext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return mConnMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    }

    public boolean isWifiNetworkAvailable()
    {
        ConnectivityManager mConnMgr = (ConnectivityManager) mcontext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetInfo = mConnMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean flag = false;
        if ((mNetInfo != null) && (mNetInfo.isAvailable()))
        {
            if (mNetInfo.isConnected())
            {
                flag = true;
            }
        }
        return flag;
    }

    // add a network configure and start connect to it
    public boolean addNetwork(WifiConfiguration wcg)
    {
        int wcgID = wifiManager.addNetwork(wcg);
        if (wcgID == -1)
            return false;
        wifiManager.saveConfiguration();
        return true;
    }

    public boolean enableNetworkWcg(int wcgID)
    {
        return wifiManager.enableNetwork(wcgID, true);
    }

    public int addNetworkWcg(WifiConfiguration wcg)
    {
        return wifiManager.addNetwork(wcg);
    }

    public boolean enableNetwork(WifiConfiguration wcg)
    {
        int wcgID = wifiManager.addNetwork(wcg);
        if (wcgID == -1)
            return false;
        return wifiManager.enableNetwork(wcgID, true);
    }

    // disconnect current wifi
    public void disconnectWifi(int netId)
    {
        Log.d(TAG, "########disconnect Wifi#########");
        wifiManager.disconnect();
        wifiManager.disableNetwork(netId);
    }

    public void removeNetwork(int networkId)
    {
        wifiManager.removeNetwork(networkId);
    }

    public boolean reconnect()
    {

        return wifiManager.reconnect();
    }


    public WifiConfiguration createWifiInfo(String SSID, String Password,
                                            int Type)
    {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";

        // clearWifiConfigure();// clear all configures

        if (Type == 1) // WIFICIPHER_NOPASS
        {
            config.wepKeys[0] = "\"" + "\"";
            config.allowedKeyManagement.set(KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 2) // WIFICIPHER_WEP
        {
            config.hiddenSSID = true;
            config.wepKeys[0] = "\"" + Password + "\"";
            config.allowedAuthAlgorithms
                    .set(AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers
                    .set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 3) // WIFICIPHER_WPA
        {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms
                    .set(AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.TKIP);
            //config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }

        return config;
    }

    public void clearWifiConfigure()
    {
        List<WifiConfiguration> Configs = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration Config : Configs)
        {
            if (Config.networkId != -1)
            {
                Log.d(TAG, "clearWifiConfigure" + Config.networkId);
                wifiManager.removeNetwork(Config.networkId);
            }
        }
    }

    public void clearWifiConfigure(String ssid)
    {
        List<WifiConfiguration> Configs = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration Config : Configs)
        {
            Log.d(TAG, "clearWifiConfigure SSID" + Config.SSID + " /// " + ssid);
            if (Config.networkId != -1 && Config.SSID.equals(ssid))
            {
                Log.i(TAG, "clearWifiConfigure SSID" + Config.networkId);
                wifiManager.removeNetwork(Config.networkId);
            }
        }
    }


    public ScanResult scanListIsExsits(String SSID)
    {
        List<ScanResult> existingScanResult = wifiManager
                .getScanResults();
        Log.i(TAG, "scanListIsExsits :" + SSID);
        if (existingScanResult != null)
        {
            for (ScanResult existingConfig : existingScanResult)
            {
                Log.d(TAG, "existingConfig SSID:" + existingConfig.SSID);
                if (existingConfig.SSID.equals(StringUtil.getSSID(SSID)))
                {
                    Log.e(TAG, existingConfig.SSID);
                    return existingConfig;
                }
            }
        }
        return null;
    }

    public WifiConfiguration WifiConfigIsExsits(String SSID)
    {
        List<WifiConfiguration> existingConfigs = wifiManager
                .getConfiguredNetworks();
        Log.i(TAG, "WifiConfigIsExsits :" + SSID + " " + new String("\"" + SSID + "\""));
        if (existingConfigs != null)
        {
            for (WifiConfiguration existingConfig : existingConfigs)
            {
                Log.d(TAG, "existingConfig SSID:" + existingConfig.SSID);
                if (existingConfig.SSID.equals(StringUtil.setSSID(SSID)))
                {
                    Log.e(TAG, existingConfig.SSID);
                    return existingConfig;
                }
            }
        }
        return null;
    }

    public String intToIp(int ip)
    {
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "."
                + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);
    }


    public WifiConfiguration getConfig()
    {
        // TODO Auto-generated method stub
        List<WifiConfiguration> Configs = wifiManager.getConfiguredNetworks();
        if (Configs == null)
            return null;
        for (WifiConfiguration Config : Configs)
        {
            if (Config.networkId != -1)
            {
                return Config;
            }
        }
        return null;
    }

    public int getCipherType(String ssid)
    {
        List<ScanResult> list = wifiManager.getScanResults();
        for (ScanResult scResult : list)
        {
            if (!TextUtils.isEmpty(scResult.SSID) && scResult.SSID.equals(ssid))
            {
                String capabilities = scResult.capabilities;
                if (!TextUtils.isEmpty(capabilities))
                {
                    if (capabilities.contains("WPA")
                            || capabilities.contains("wpa"))
                    {
                        return 3;
                    } else if (capabilities.contains("WEP")
                            || capabilities.contains("wep"))
                    {
                        return 2;
                    } else
                    {
                        return 1;
                    }
                }
            }
        }
        return -1;
    }


}
