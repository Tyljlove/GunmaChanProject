package asu.gunma;

import com.badlogic.gdx.Input.TextInputListener;


public class InputHandler implements TextInputListener {

    public String myText;

    @Override
    public void input(String text) {
        myText = text;
    }
    @Override
    public void canceled() {

    }

    public String getInputText() {

        return myText;

    }


}
