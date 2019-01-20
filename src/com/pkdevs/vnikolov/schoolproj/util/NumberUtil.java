package com.pkdevs.vnikolov.schoolproj.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * NumberUtil
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class NumberUtil {
    public static int randomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
