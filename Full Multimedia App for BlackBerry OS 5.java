import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;
import java.io.InputStream;

public class MultimediaSuiteOS5 extends UiApplication {

    public static void main(String[] args) {
        MultimediaSuiteOS5 app = new MultimediaSuiteOS5();
        app.enterEventDispatcher();
    }

    public MultimediaSuiteOS5() {
        pushScreen(new MultimediaScreen());
    }
}

class MultimediaScreen extends MainScreen {

    private Player audioPlayer;
    private Player videoPlayer;
    private VideoControl videoControl;
    
    private String[] audioFiles = {"/audio1.mp3", "/audio2.mp3"};
    private String[] imageFiles = {"/image1.png", "/image2.png", "/image3.png"};
    private String[] videoFiles = {"/video1.3gp", "/video2.3gp"};

    private int currentAudio = 0;
    private int currentImage = 0;
    private int currentVideo = 0;

    private BitmapField imageField;

    public MultimediaScreen() {
        setTitle(new LabelField("BB OS 5 Multimedia Suite"));

        // Display first image
        imageField = new BitmapField();
        add(imageField);
        showImage(currentImage);

        // Buttons for image slideshow
        add(new ButtonField("Next Image", ButtonField.CONSUME_CLICK) {
            protected boolean navigationClick(int status, int time) {
                currentImage = (currentImage + 1) % imageFiles.length;
                showImage(currentImage);
                return true;
            }
        });

        add(new ButtonField("Previous Image", ButtonField.CONSUME_CLICK) {
            protected boolean navigationClick(int status, int time) {
                currentImage = (currentImage - 1 + imageFiles.length) % imageFiles.length;
                showImage(currentImage);
                return true;
            }
        });

        // Audio controls
        add(new ButtonField("Play Audio", ButtonField.CONSUME_CLICK) {
            protected boolean navigationClick(int status, int time) {
                playAudio(audioFiles[currentAudio]);
                return true;
            }
        });

        add(new ButtonField("Next Audio", ButtonField.CONSUME_CLICK) {
            protected boolean navigationClick(int status, int time) {
                stopAudio();
                currentAudio = (currentAudio + 1) % audioFiles.length;
                playAudio(audioFiles[currentAudio]);
                return true;
            }
        });

        // Video controls
        add(new ButtonField("Play Video", ButtonField.CONSUME_CLICK) {
            protected boolean navigationClick(int status, int time) {
                playVideo(videoFiles[currentVideo]);
                return true;
            }
        });

        add(new ButtonField("Next Video", ButtonField.CONSUME_CLICK) {
            protected boolean navigationClick(int status, int time) {
                stopVideo();
                currentVideo = (currentVideo + 1) % videoFiles.length;
                playVideo(videoFiles[currentVideo]);
                return true;
            }
        });
    }

    private void showImage(int index) {
        try {
            Bitmap bmp = Bitmap.getBitmapResource(imageFiles[index]);
            imageField.setBitmap(bmp);
        } catch (Exception e) {
            Dialog.alert("Error loading image: " + e.getMessage());
        }
    }

    private void playAudio(String file) {
        try {
            stopAudio(); // Stop previous
            InputStream is = getClass().getResourceAsStream(file);
            audioPlayer = Manager.createPlayer(is, "audio/mpeg");
            audioPlayer.realize();
            audioPlayer.prefetch();
            audioPlayer.start();
        } catch (Exception e) {
            Dialog.alert("Error playing audio: " + e.getMessage());
        }
    }

    private void stopAudio() {
        try {
            if (audioPlayer != null) {
                audioPlayer.stop();
                audioPlayer.close();
            }
        } catch (Exception e) {}
    }

    private void playVideo(String file) {
        try {
            stopVideo(); // Stop previous
            InputStream is = getClass().getResourceAsStream(file);
            videoPlayer = Manager.createPlayer(is, "video/3gpp");
            videoPlayer.realize();

            videoControl = (VideoControl) videoPlayer.getControl("VideoControl");
            if (videoControl != null) {
                Field videoField = (Field) videoControl.initDisplayMode(
                        VideoControl.USE_GUI_PRIMITIVE, "net.rim.device.api.ui.Field");
                add(videoField);
                videoPlayer.start();
            }
        } catch (Exception e) {
            Dialog.alert("Error playing video: " + e.getMessage());
        }
    }

    private void stopVideo() {
        try {
            if (videoPlayer != null) {
                videoPlayer.stop();
                videoPlayer.close();
            }
        } catch (Exception e) {}
    }

    protected boolean onClose() {
        stopAudio();
        stopVideo();
        System.exit(0);
        return true;
    }
}
