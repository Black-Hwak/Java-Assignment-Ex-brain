package ui;

import database.ActorDAO;
import dto.ActorDTO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ActorNewForm extends JFrame {
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 600;

    public ActorNewForm(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // create menu bar
        MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar.createMenuBar());

        JLabel addTitleLbl = new JLabel("REGISTER FROM");
        JLabel firstNameLbl = new JLabel("First Name : ");
        JLabel lastNameLbl = new JLabel("Last Name : ");
        JTextField firstNameTxf = new JTextField();
        JTextField lastNameTxf = new JTextField();

        JPanel formPanel = new JPanel();
        GridBagLayout gbLayoutTable = new GridBagLayout();
        formPanel.setLayout(gbLayoutTable);

        formPanel.add(addTitleLbl, new GBC(0, 0, 2, 1)
                .setInsets(5, 5, 5, 5)
                .setAnchor(GridBagConstraints.CENTER));

        formPanel.add(firstNameLbl, new GBC(0, 1)
                .setInsets(5, 5, 5, 5)
                .setAnchor(GridBagConstraints.EAST));
        formPanel.add(firstNameTxf, new GBC(1, 1)
                .setIpad(80, 5)
                .setInsets(10, 10, 10, 10)
                .setFill(GridBagConstraints.HORIZONTAL));

        formPanel.add(lastNameLbl, new GBC(0, 2)
                .setInsets(5, 5, 5, 5)
                .setAnchor(GridBagConstraints.EAST));
        formPanel.add(lastNameTxf, new GBC(1, 2)
                .setIpad(80, 5)
                .setInsets(10, 10, 10, 10)
                .setFill(GridBagConstraints.HORIZONTAL));

        JButton addNewActorBtn = new JButton("Add New Actor");

        formPanel.add(addNewActorBtn, new GBC(0, 3, 2, 1)
                .setIpad(0, 10)
                .setInsets(10, 0, 0, 0)
                .setFill(GridBagConstraints.HORIZONTAL));

        JLabel resultLbl = new JLabel("New Actor Arriving ....");

        formPanel.add(resultLbl, new GBC(0, 4, 2, 1)
                .setIpad(40, 40)
                .setInsets(10, 0, 0, 0)
                .setAnchor(GridBagConstraints.WEST));


        // Making Center In Vertical
        JPanel mainPanel = new JPanel();
        GridBagLayout gbLayout = new GridBagLayout();
        mainPanel.setLayout(gbLayout);

        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(formPanel, new GBC(0,0)
                .setIpad(50, 70));

        add(mainPanel);


        addNewActorBtn.addActionListener(ev -> {
            String firstName = firstNameTxf.getText();
            String lastName = lastNameTxf.getText();

            if (!(firstName.equals("")) && !(lastName.equals(""))) {
                try {
                    ActorDTO actor = new ActorDTO(firstName, lastName);
                    ActorDAO.addNewActor(actor);
                    firstNameTxf.setText("");
                    lastNameTxf.setText("");
                    resultLbl.setText("Successfully Added!");
                } catch (SQLException e) {
                    System.out.println(e.getErrorCode());
                    e.printStackTrace();
                }
            } else {
                resultLbl.setText("Sorry... Something Went Wrong.");
            }
        });

    }
}
