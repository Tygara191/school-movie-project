package com.pkdevs.vnikolov.schoolproj.util;

/**
 * StringUtil
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class StringUtil {
    public static String fixedLengthString(String string, int length) {
        return String.format("%1$-"+length+ "s", string);
    }
}
