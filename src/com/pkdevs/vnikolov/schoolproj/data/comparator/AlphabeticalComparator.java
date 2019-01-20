package com.pkdevs.vnikolov.schoolproj.data.comparator;

import com.pkdevs.vnikolov.schoolproj.data.model.Movie;

import java.util.Comparator;

/**
 * AlphabeticalComparator
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class AlphabeticalComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie mLeft, Movie mRight) {
        return mLeft.title.compareTo(mRight.title);
    }
}
