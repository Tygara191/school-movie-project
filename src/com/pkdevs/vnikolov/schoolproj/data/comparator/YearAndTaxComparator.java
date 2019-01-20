package com.pkdevs.vnikolov.schoolproj.data.comparator;

import com.pkdevs.vnikolov.schoolproj.data.model.Movie;

/**
 * YearAndTaxComparator
 * -
 * Primarily orders by year /descending/.
 * If years are equal, orders by tax /ascending/
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class YearAndTaxComparator implements java.util.Comparator<Movie> {
    @Override
    public int compare(Movie mLeft, Movie mRight) {
        if(mLeft.productionYear > mRight.productionYear) return -1;
        if(mLeft.productionYear < mRight.productionYear) return 1;
        return Integer.compare(mLeft.licensingTax, mRight.licensingTax);
    }
}
