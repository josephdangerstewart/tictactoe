package com.tictactoe.state;

import com.tictactoe.state.meta.State;

/**
 * Created by josephstewart on 9/24/17.
 */
public class MainState extends State {

    public MainState(String backgroundName) {
        super(backgroundName);
        System.out.println("Hello world!");
    }

}
