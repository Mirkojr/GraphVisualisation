package src.view;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class AnimatedButton extends JButton {

    private int BUTTON_WIDTH;
    private int BUTTON_HEIGHT;
    private static final int ANIMATION_DELAY = 10;
    private static final int SIZE_INCREMENT = 2;
    private static final float COLOR_TRANSITION_SPEED = 0.1f;

    private Color baseColor = Color.LIGHT_GRAY;
    private Color transitionColor = Color.CYAN;
    private Color hoverColor;
    private float[] currentRGB;

    public AnimatedButton(String text, int width, int height) {
        super(text);
        this.BUTTON_WIDTH = width;
        this.BUTTON_HEIGHT = height;
        initializeButton();
        addMouseListeners();
    }

    public void setSize(int width, int height) {
        if(width > 50 && height > 20)
            super.setSize(width, height);
            BUTTON_WIDTH = width;
            BUTTON_HEIGHT = height;
    }

    private void initializeButton() {
        setBounds(10, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
        setBackground(baseColor);
        setFocusPainted(false);
        setBorder(javax.swing.BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        currentRGB = new float[3];
    }


    private void addMouseListeners() {
        Timer expandTimer = createExpandTimer();
        Timer shrinkTimer = createShrinkTimer();
        Timer colorTransitionTimer = createColorTransitionTimer();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                handleMouseEntered(expandTimer, shrinkTimer, colorTransitionTimer);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                handleMouseExited(expandTimer, shrinkTimer, colorTransitionTimer);
            }

        });
    }

    private Timer createExpandTimer() {
        return new Timer(ANIMATION_DELAY, e -> {
            if (getWidth() < BUTTON_WIDTH + 10 && getHeight() < BUTTON_HEIGHT + 10) {
                setBounds(getX() - 1, getY() - 1, getWidth() + SIZE_INCREMENT, getHeight() + SIZE_INCREMENT);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
    }

    private Timer createShrinkTimer() {
        return new Timer(ANIMATION_DELAY, e -> {
            if (getWidth() > BUTTON_WIDTH && getHeight() > BUTTON_HEIGHT) {
                setBounds(getX() + 1, getY() + 1, getWidth() - SIZE_INCREMENT, getHeight() - SIZE_INCREMENT);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
    }

    private Timer createColorTransitionTimer() {
        return new Timer(ANIMATION_DELAY, e -> {
            float[] targetRGB = hoverColor.getRGBColorComponents(null);
            boolean transitionComplete = true;

            for (int i = 0; i < 3; i++) {
                if (Math.abs(currentRGB[i] - targetRGB[i]) > 0.01f) {
                    currentRGB[i] += (targetRGB[i] - currentRGB[i]) * COLOR_TRANSITION_SPEED;
                    transitionComplete = false;
                }
            }

            setBackground(new Color(currentRGB[0], currentRGB[1], currentRGB[2]));

            if (transitionComplete) {
                ((Timer) e.getSource()).stop();
            }
        });
    }

    private void handleMouseEntered(Timer expandTimer, Timer shrinkTimer, Timer colorTransitionTimer) {
        shrinkTimer.stop();
        expandTimer.start();

        hoverColor = transitionColor;
        startColorTransition(colorTransitionTimer);
    }

    private void handleMouseExited(Timer expandTimer, Timer shrinkTimer, Timer colorTransitionTimer) {
        expandTimer.stop();
        shrinkTimer.start();

        hoverColor = baseColor;
        startColorTransition(colorTransitionTimer);
    }

    private void startColorTransition(Timer colorTransitionTimer) {
        System.arraycopy(getBackground().getRGBColorComponents(null), 0, currentRGB, 0, 3);
        colorTransitionTimer.start();
    }
}
