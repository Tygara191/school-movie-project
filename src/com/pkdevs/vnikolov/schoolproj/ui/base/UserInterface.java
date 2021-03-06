package com.pkdevs.vnikolov.schoolproj.ui.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * UserInterface
 * -
 * A set of methods for basic UI operations
 *
 * @author Vesko Nikolov <vnikolov@pkdevs.com>
 * @since 20.01.2019
 */
public class UserInterface {

    /**
     * Clears cmd
     */
    protected void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException ignored) {}
    }

    /**
     * Prompts integer positive integer selection and returns -1 when an invalid int is passed
     */
    protected int promptPositiveIntegerSelection(String message){
        try {
            String in = promptStringInput(message);
            if(in != null){
                return Integer.parseInt(in);
            }
        } catch (NumberFormatException ignored){}
        return -1;
    }

    /**
     * Prompts the user to enter a string
     */
    protected String promptStringInput(String message){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        try {
            String in = br.readLine();
            return in.isEmpty() ? null : in;
        } catch (IOException e) {
            // In our use case we should never get IOExceptions, but if we do :)
            return null;
        }
    }
}