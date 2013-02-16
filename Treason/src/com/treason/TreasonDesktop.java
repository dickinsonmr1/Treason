package com.treason;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.treason.TreasonGame;
import com.treason.ui.MainMenuScreen;

public class TreasonDesktop extends Game {
        public static void main (String[] args) {
                new LwjglApplication(new TreasonGame(), "Treason", 960, 640, false);
        }

		@Override
		public void create() {

		}
}