package com.pkdevs.vnikolov.schoolproj;

import com.pkdevs.vnikolov.schoolproj.data.MovieStore;
import com.pkdevs.vnikolov.schoolproj.ui.MovieUI;

/**
 * Main
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class Main {
    public static void main(String... args){
        MovieApp app = new MovieApp(
                new MovieUI(),
                new MovieStore()
        );
        app.start();
    }
}
