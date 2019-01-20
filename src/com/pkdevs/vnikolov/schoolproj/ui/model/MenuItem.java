package com.pkdevs.vnikolov.schoolproj.ui.model;

/**
 * MenuItem
 * -
 * One menu item
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class MenuItem {
    public enum Command{
        COMMAND_ENTER_NEW,
        COMMAND_SHOW_ALL,
        COMMAND_SHOW_ACTOR,
        COMMAND_DUMMY_DATA,
        COMMAND_EXIT
    }

    public final String title;
    public final Command command;

    public MenuItem(String title, Command command) {
        this.title = title;
        this.command = command;
    }
}
