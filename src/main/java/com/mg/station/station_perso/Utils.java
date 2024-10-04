package com.mg.station.station_perso;

import javax.servlet.http.HttpServletRequest;

public final class Utils {
    public static final String ASSETS_FOLDER = "assets";
    public static final String NOTHING_TO_INCLUDE = "<!-- Nothing to include -->";
    public static final String LAYOUT = "views/layout.jsp";

    private Utils() { }

    public static boolean isBlank(String string) {
        return string != null && string.trim().isEmpty();
    }

    public static String view(String path) {
        return path + ".jsp";
    }

    public static String asset(String path) {
        return ASSETS_FOLDER + "/" + path;
    }

    public static String pathTo(HttpServletRequest httpServletRequest, String uri) {
        return httpServletRequest.getContextPath() + uri;
    }
}
