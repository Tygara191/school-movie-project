package com.pkdevs.vnikolov.schoolproj;

import com.pkdevs.vnikolov.schoolproj.data.MovieStore;
import com.pkdevs.vnikolov.schoolproj.data.comparator.AlphabeticalComparator;
import com.pkdevs.vnikolov.schoolproj.data.comparator.YearAndTaxComparator;
import com.pkdevs.vnikolov.schoolproj.data.model.Movie;
import com.pkdevs.vnikolov.schoolproj.ui.MovieUI;
import com.pkdevs.vnikolov.schoolproj.ui.model.MenuItem;
import com.pkdevs.vnikolov.schoolproj.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * MovieApp
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
class MovieApp {
    private static final int MAX_MOVIES = 10000;

    // Dependencies
    private final MovieUI ui;
    private final MovieStore movieStore;

    /**
     * All dependencies are injected, a class should not create any of its dependencies :)
     * It is a bit arguable if we can call the {@link MovieStore} a dependency the way it is
     * currently implemented, but meh
     */
    MovieApp(MovieUI ui, MovieStore movieStore) {
        this.ui = ui;
        this.movieStore = movieStore;
    }

    /**
     * Essentially this brings up the starting point of our app, this can be done from any point
     */
    void start(){
        MenuItem.Command command = ui.mainMenu();

        // We could use switch here, but this is actually cleaner so yeah
        if(command == MenuItem.Command.COMMAND_EXIT)       exit();
        if(command == MenuItem.Command.COMMAND_ENTER_NEW)  enterNewMovie();
        if(command == MenuItem.Command.COMMAND_SHOW_ACTOR) showActor();
        if(command == MenuItem.Command.COMMAND_DUMMY_DATA) populateWithDummyData();
        if(command == MenuItem.Command.COMMAND_SHOW_ALL)   showAll();
    }

    /**
     * Didn't feel like typing the movies 12314123 times while testing, so I hardcoded some dummy data
     * Its taught in such a way that it demonstrates all key aspects of
     */
    private void populateWithDummyData() {
        movieStore.add(new Movie("A - Film mnogo hubav", randomActors(), 2000, 1901));
        movieStore.add(new Movie("G - Film mnogo hubav", randomActors(), 2001, 1902));
        movieStore.add(new Movie("B - Drug film", randomActors(), 2001, 1903));
        movieStore.add(new Movie("F - Film mnogo hubav", randomActors(), 2001, 1904));
        movieStore.add(new Movie("C - Oshte edin film", randomActors(), 2001, 1905));
        movieStore.add(new Movie("E - Film mnogo hubav", randomActors(), 2001, 1906));
        movieStore.add(new Movie("D - Film deto pochva s A", randomActors(), 2001, 1907));
        movieStore.add(new Movie("H - Film mnogo hubav", randomActors(), 2001, 1908));
        start();
    }

    /**
     * @return A randomly generated list of actor names
     */
    private List<String> randomActors(){
        ArrayList<String> possibleNames = new ArrayList<>();
        possibleNames.add("Kiro");
        possibleNames.add("Pesho");
        possibleNames.add("Pesho drugiq");
        possibleNames.add("Mincho igracho");
        possibleNames.add("Gosho prezidenta");
        possibleNames.add("Komshiqta");
        possibleNames.add("Robert de nqkakuv si");
        possibleNames.add("I drugi");
        ArrayList<String> result = new ArrayList<>();
        for(int i=0;i< NumberUtil.randomInt(0, possibleNames.size());i++){
            result.add(possibleNames.get(NumberUtil.randomInt(0, possibleNames.size()-1)));
        }
        return result;
    }

    /**
     * Prints out all movies of an actor, sorted using {@link YearAndTaxComparator}
     */
    private void showActor() {
        String name = ui.askForActorName();
        ui.showMovies(movieStore.filterByActor(name)
                .sortByYearAndTax());
        start();
    }

    /**
     * Shows all movies, sorted by title using {@link AlphabeticalComparator}
     */
    private void showAll() {
        movieStore.sortAlphabetically();
        ui.showMovies(movieStore);
        start();
    }

    /**
     * Checks whether the max movie limit is reached and if not, adds the movie
     */
    private void enterNewMovie() {
        if(movieStore.size() < MAX_MOVIES){
            movieStore.add(ui.promptNewMovie());
            start();
            return;
        }
        ui.informLimitReached();
    }

    private void exit() {
        Runtime.getRuntime().exit(0);
    }
}
