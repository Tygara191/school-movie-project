package com.pkdevs.vnikolov.schoolproj.ui.menu;

import com.pkdevs.vnikolov.schoolproj.ui.model.MenuItem;

import java.util.LinkedHashMap;

/**
 * MainMenu - A model for a menu
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class MainMenu extends LinkedHashMap<Integer, MenuItem> {
    // We don't want anyone constructing this class with anything different than .make()
    private MainMenu(){}
    /**
     * Builds a main menu
     *
     * @return A built Menu map
     */
    public static MainMenu make() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.put(1, new MenuItem("Enter new movie", MenuItem.Command.COMMAND_ENTER_NEW));
        mainMenu.put(2, new MenuItem("See movies ordered alphabetically", MenuItem.Command.COMMAND_SHOW_ALL));
        mainMenu.put(3, new MenuItem("See movies from actor", MenuItem.Command.COMMAND_SHOW_ACTOR));
        mainMenu.put(4, new MenuItem("Populate with dummy data", MenuItem.Command.COMMAND_DUMMY_DATA));
        mainMenu.put(0, new MenuItem("Exit", MenuItem.Command.COMMAND_EXIT));
        return mainMenu;
    }
}
