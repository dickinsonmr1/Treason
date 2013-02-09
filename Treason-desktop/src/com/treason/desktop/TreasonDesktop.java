package com.treason.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.treason.*;
import com.treason.ui.MainMenuScreen;

public class TreasonDesktop extends Game {
        public static void main (String[] args) {
                new LwjglApplication(new TreasonDesktop(), "Game", 1280, 720, false);
        }

		@Override public void create() {
			// TODO Auto-generated method stub
			setScreen(new MainMenuScreen(this));
		}
}