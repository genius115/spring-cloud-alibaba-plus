package com.xiaomai.cloud.api.config;

public class UserContext {
	private static final ThreadLocal<CurrentUser> USER_LOCAL = new ThreadLocal<CurrentUser>();

    private UserContext() {
    }

    public static void set(CurrentUser user) {
        USER_LOCAL.set(user);
    }

    public static CurrentUser get() {
        return (CurrentUser)USER_LOCAL.get();
    }

    public static Long getId() {
        return get().getUserId();
    }

    public static String getUsername() {
        return get().getUsername();
    }

    public static String getName() {
        return get().getName();
    }
    public static String getAccountitemcode() {
        return get().getAccountitemcode();
    }
    public static String getAccountuserid() {
        return get().getAccountuserid();
    }
    public static String getAccountusername() {
        return get().getAccountusername();
    }
}
