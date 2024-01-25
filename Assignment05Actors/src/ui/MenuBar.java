package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuBar {
    private JFrame currentFrame;
    public MenuBar(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        class TestAction extends AbstractAction {
            public TestAction(String name) {
                super(name);
            }

            public void actionPerformed(ActionEvent event) {
                System.out.println(getValue(Action.NAME) + " selected.");

                // Close the current frame
                currentFrame.dispose();

                EventQueue.invokeLater(() -> {
                    JFrame newFrame;
                    if (getValue(Action.NAME).equals("Search")) {
                        newFrame = new ActorSearchForm();
                        newFrame.setTitle("Actors Search");
                    } else {
                        newFrame = new ActorNewForm();
                        newFrame.setTitle("Actors Add");
                    }
                    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    newFrame.setVisible(true);
                });
            }
        }

        var fileMenu = new JMenu("Details");
        fileMenu.add(new TestAction("Search"));
        fileMenu.add(new TestAction("Add"));
        var helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }
}
