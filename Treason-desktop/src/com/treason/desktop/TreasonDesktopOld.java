package com.treason.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.treason.TreasonGame;
import com.treason.ui.MainMenuScreen;

public class TreasonDesktopOld extends Game {
        public static void main (String[] args) {
                new LwjglApplication(new TreasonGame(), "Game", 1280, 720, false);
        }

		@Override
		public void create() {

		}
}