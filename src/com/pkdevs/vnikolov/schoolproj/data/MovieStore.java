package com.pkdevs.vnikolov.schoolproj.data;

import com.pkdevs.vnikolov.schoolproj.data.comparator.AlphabeticalComparator;
import com.pkdevs.vnikolov.schoolproj.data.comparator.YearAndTaxComparator;
import com.pkdevs.vnikolov.schoolproj.data.model.Movie;
import com.pkdevs.vnikolov.schoolproj.util.StringUtil;

import java.util.ArrayList;

/**
 * MovieStore
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class MovieStore extends ArrayList<Movie> {
    private static final String TABLE_HEAD_TITLE = "Title";
    private static final String TABLE_HEAD_YEAR = "Year";
    private static final String TABLE_HEAD_ACTORS = "Actors";
    private static final String TABLE_HEAD_TAXES = "Taxes";

    @SuppressWarnings("UnusedReturnValue")
    public MovieStore sortAlphabetically(){
        this.sort(new AlphabeticalComparator());
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public MovieStore sortByYearAndTax(){
        this.sort(new YearAndTaxComparator());
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public MovieStore filterByActor(String actor){
        MovieStore filtered = new MovieStore();
        if(this.size() > 0){
            for(Movie movie : this){
                if(movie.hasActor(actor)) filtered.add(movie);
            }
        }
        return filtered;
    }

    /**
     * Method to make a movieStore into a complete table with paddings n' shit.
     * <br>
     * Probably the single ugliest piece of code i've written
     *
     * @return Formatted table of movies, ready to print out
     */
    public String toTable(){
        StringBuilder result = new StringBuilder();
        // There's probably a way to make this code more generic
        // but I don't feel like thinking that much at the moment :)

        // Titles are longest by default
        int longestTitle = TABLE_HEAD_TITLE.length();
        int longestYear = TABLE_HEAD_YEAR.length();
        int longestActor = TABLE_HEAD_ACTORS.length();
        int longestTax = TABLE_HEAD_TAXES.length();

        // Iterate through everything and see the longest values
        for(Movie movie : this){
            if(movie.title.length() > longestTitle) longestTitle = movie.title.length();
            String yearString = String.valueOf(movie.productionYear);
            if(yearString.length() > longestYear) longestYear = yearString.length();
            for(String actor : movie.actors){
                if(actor.length() > longestActor) longestActor = actor.length();
            }
            String taxString = String.valueOf(movie.licensingTax);
            if(taxString.length() > longestTax) longestTax = taxString.length();
        }

        // Get the heading in place
        result.append(StringUtil.fixedLengthString(TABLE_HEAD_TITLE, longestTitle))
                .append(" | ")
                .append(StringUtil.fixedLengthString(TABLE_HEAD_YEAR, longestYear))
                .append(" | ")
                .append(StringUtil.fixedLengthString(TABLE_HEAD_ACTORS, longestActor))
                .append(" | ")
                .append(StringUtil.fixedLengthString(TABLE_HEAD_TAXES, longestTax))
                .append("\n");

        for(Movie movie : this){
            // Get the heading in place
            result.append(StringUtil.fixedLengthString(movie.title, longestTitle))
                    .append(" | ")
                    .append(StringUtil.fixedLengthString(String.valueOf(movie.productionYear), longestYear))
                    .append(" | ")
                    .append(StringUtil.fixedLengthString(movie.actors.size() > 0 ? movie.actors.get(0) : "-", longestActor))
                    .append(" | ")
                    .append(StringUtil.fixedLengthString(String.valueOf(movie.licensingTax), longestTax))
                    .append("\n");
            if(movie.actors.size() > 1){
                // Start from 1 because we already added the first actor above
                for(int i=1;i<movie.actors.size();i++){
                    result.append(StringUtil.fixedLengthString("", longestTitle))
                            .append(" | ")
                            .append(StringUtil.fixedLengthString("", longestYear))
                            .append(" | ")
                            .append(StringUtil.fixedLengthString(movie.actors.get(i), longestActor))
                            .append(" | ")
                            .append(StringUtil.fixedLengthString("", longestTax))
                            .append("\n");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }
}
