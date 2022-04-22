package ca.bcit.comp2522.termproject.valhalla.game;

import com.almasb.fxgl.dsl.FXGL;

/**
 * A MusicPlayer singleton class.
 * @author FonseLULW
 * @author kaioh
 * @version 1.0
 */
public final class MusicPlayer {
    private static MusicPlayer singleton = null;
    private final String mainMenuMusic;
    private final String pauseMenuMusic;
    private final String gameMusic;
    private final String bossMusic;
    private final String sadMusic;

    /*
     * Constructs a new MusicPlayer if not already made.
     */
    private MusicPlayer() {
        mainMenuMusic = "introX.wav";
        pauseMenuMusic = "pause.mp3";
        gameMusic = "bensound-instinct.mp3";
        bossMusic = "bensound-epic.mp3";
        sadMusic = "sadendingalt.mp3";
    }

    /**
     * Returns the single instance of this MusicPlayer.
     * @return the single instance of this MusicPlayer
     */
    public static MusicPlayer getSingleton() {
        if (singleton == null) {
            singleton = new MusicPlayer();
        }
        return singleton;
    }

    /**
     * Plays the game background music.
     */
    public void playGameMusic() {
        FXGL.getAudioPlayer().pauseAllMusic();
        if (FXGL.getWorldProperties().getBoolean("bossSpawned")) {
            FXGL.loopBGM(bossMusic);
        } else {
            FXGL.loopBGM(gameMusic);
        }
    }

    /**
     * Plays the pause menu background music.
     */
    public void playGameMenuMusic() {
        FXGL.getAudioPlayer().pauseAllMusic();
        FXGL.loopBGM(pauseMenuMusic);
    }

    /**
     * Plays the main menu background music.
     */
    public void playMainMenuMusic() {
        FXGL.getAudioPlayer().pauseAllMusic();
        FXGL.loopBGM(mainMenuMusic);
    }

    /**
     * Plays the bad ending background music.
     */
    public void playSadMusic() {
        FXGL.getAudioPlayer().pauseAllMusic();
        FXGL.loopBGM(sadMusic);
    }
}
