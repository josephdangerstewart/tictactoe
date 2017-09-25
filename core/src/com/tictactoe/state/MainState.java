package com.tictactoe.state;

import com.tictactoe.resources.components.Button;
import com.tictactoe.resources.meta.Event;
import com.tictactoe.state.meta.State;

/**
 * Created by josephstewart on 9/24/17.
 */
public class MainState extends State {

    public MainState(String backgroundName) {
        super(backgroundName);

        this.list.add(new Button(0, 0, "button.png", new Event() {
            @Override
            public void onCall() {
                System.out.println("Button clicked");
            }
        }));
    }

}
