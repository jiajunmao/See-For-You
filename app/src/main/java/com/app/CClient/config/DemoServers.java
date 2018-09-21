package com.app.CClient.config;

public class DemoServers {
    private static final String API_SERVER_TEST = "https://apptest.netease.im/api/"; // 测试
    private static final String API_SERVER = "https://app.netease.im/api/"; // 线上

    public static final String apiServer() {
        return ServerConfig.testServer() ? API_SERVER_TEST : API_SERVER;
    }

    public static final String chatRoomAPIServer() {
        return apiServer() + "chatroom/";
    }
}
