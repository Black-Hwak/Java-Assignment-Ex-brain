package ui;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class Main {
    static {
        FlatIntelliJLaf.setup();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ActorSearchForm frame = new ActorSearchForm();
            frame.setTitle("Actors Search");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}