package com.pkdevs.vnikolov.schoolproj.ui;

import com.pkdevs.vnikolov.schoolproj.data.MovieStore;
import com.pkdevs.vnikolov.schoolproj.data.model.Movie;
import com.pkdevs.vnikolov.schoolproj.ui.base.UserInterface;
import com.pkdevs.vnikolov.schoolproj.ui.menu.MainMenu;
import com.pkdevs.vnikolov.schoolproj.ui.model.MenuItem;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MovieUI
 * -
 * An implementation of a UserInterface for the entire movie app interaction.
 * Might be a better idea to split this up into multiple implementations, but meh
 * its a school project after all.
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class MovieUI extends UserInterface {

    private MainMenu mainMenu;

    /**
     * @param showError If true, an error message saying "bad command selected" will appear
     * @return The selected command
     */
    private MenuItem.Command mainMenu(boolean showError) {
        if (this.mainMenu == null) {
            this.mainMenu = MainMenu.make();
        }
        clear();
        System.out.println("------ What do you want to do? -----------------------------");
        for (Map.Entry<Integer, MenuItem> item : mainMenu.entrySet()) {
            System.out.println(String.valueOf(item.getKey()) +
                    ". " +
                    item.getValue().title);
        }
        int i = promptIntegerSelection("Choose action: ");
        if (mainMenu.containsKey(i)) {
            return mainMenu.get(i).command;
        } else {
            // Recursively prompt until valid input is given :)
            return mainMenu(true);
        }
    }

    /**
     * Orchestrates the entire user dialog of entering a new movie
     *
     * @return A movie object with semi-validated data
     */
    @NotNull
    public Movie promptNewMovie() {
        clear();
        System.out.println("------ Please enter data about the movie -------------------");
        // Since our prompt* methods are as safe as they get in terms of validation, we can
        // directly use them here without much fear of bad data being given to us
        return new Movie(
                promptTitle(),
                promptActors(),
                promptYear(),
                promptLicenseTax()
        );
    }

    /**
     * @return License tax which is always user-input, cannot be blank or negative
     */
    private int promptLicenseTax() {
        int licenseTax = promptIntegerSelection("License tax: ");
        return licenseTax > -1 ? licenseTax : promptLicenseTax();
    }

    /**
     * @return A list of actors, needs to contain at least one element
     */
    @NotNull
    private List<String> promptActors() {
        String raw = promptStringInput("Enter actors, separated with commas: ");
        if (raw == null) return promptActors();
        String[] split = raw.split(",");
        List<String> filtered = new ArrayList<>();

        for (String actor : split) {
            // We do trim twice, but get real...
            // How often is this really going to be executed?
            if (actor.trim().length() > 0) {
                filtered.add(actor.trim());
            }
        }
        return filtered.size() > 0 ? filtered : promptActors();
    }

    /**
     * A method that asks the user to enter a title, as part of the {@link #promptNewMovie()} process
     *
     * @return A string that is not null :)
     */
    @NotNull
    private String promptTitle() {
        String title = promptStringInput("Movie title: ");
        return title == null ? promptTitle() : title;
    }

    /**
     * @return Year which is always user-input, cannot be blank or negative
     */
    private int promptYear() {
        int year = promptIntegerSelection("Year: ");
        return year > -1 ? year : promptYear();
    }

    /**
     * The public way to start main menu, it defaults showError:false when externally called
     *
     * @return A command
     */
    public MenuItem.Command mainMenu() {
        return mainMenu(false);
    }

    public void showMovies(MovieStore movieStore) {
        clear();
        System.out.println("------ Movie listing ---------------------------------------");
        if (movieStore.size() > 0) {
            System.out.print(movieStore.toTable());
            promptStringInput("Press enter to get back to main menu");
            return;
        }
        promptStringInput("No movies :D, press enter to get back to the menu");
    }

    public String askForActorName(){
        clear();
        System.out.println("------ Show movies from specific actor ---------------------");
        String name = promptStringInput("Enter name: ");
        return name != null ? name : askForActorName();
    }

    public void informLimitReached() {
        clear();
        promptStringInput("You've reached your movie limit. Press enter to get back to the main menu");
    }
}
