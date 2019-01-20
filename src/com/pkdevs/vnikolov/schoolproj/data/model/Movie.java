package com.pkdevs.vnikolov.schoolproj.data.model;

import java.util.List;

/**
 * Movie
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class Movie{

    public final String title;
    public final List<String> actors;
    public final int productionYear;
    public final int licensingTax;

    public Movie(String title, List<String> actors, int productionYear, int licensingTax) {
        this.title = title;
        this.actors = actors;
        this.productionYear = productionYear;
        this.licensingTax = licensingTax;
    }

    public boolean hasActor(String actorName){
        return actors.contains(actorName);
    }
}
