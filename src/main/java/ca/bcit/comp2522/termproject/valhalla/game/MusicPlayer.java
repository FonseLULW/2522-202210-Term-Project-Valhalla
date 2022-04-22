package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Scene;

public class MusicPlayer {
    private static MusicPlayer singleton = null;
    private final String mainMenuMusic;
    private final String pauseMenuMusic;
    private final String gameMusic;
    private final String bossMusic;
    private final String sadMusic;

    private MusicPlayer() {
        mainMenuMusic = "introX.wav";
        pauseMenuMusic = "pause.mp3";
        gameMusic = "bensound-instinct.mp3";
        bossMusic = "bensound-epic.mp3";
        sadMusic = "sadendingalt.mp3";
    }

    public static MusicPlayer getSingleton() {
        if (singleton == null) {
            singleton = new MusicPlayer();
        }
        return singleton;
    }

    public void playGameMusic() {
        FXGL.getAudioPlayer().pauseAllMusic();
        if (FXGL.getWorldProperties().getBoolean("bossSpawned")) {
            FXGL.loopBGM(bossMusic);
        } else {
            FXGL.loopBGM(gameMusic);
        }
    }

    public void playGameMenuMusic() {
        FXGL.getAudioPlayer().pauseAllMusic();
        FXGL.loopBGM(pauseMenuMusic);
    }

    public void playMainMenuMusic() {
        FXGL.getAudioPlayer().pauseAllMusic();
        FXGL.loopBGM(mainMenuMusic);
    }

    public void playSadMusic() {
        FXGL.getAudioPlayer().pauseAllMusic();
        FXGL.loopBGM(sadMusic);
    }
}
