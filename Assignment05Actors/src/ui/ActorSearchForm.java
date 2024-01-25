package ui;

import dto.ActorDTO;
import database.ActorDAO;
import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.SQLException;

public class ActorSearchForm extends JFrame {
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 600;
    private int[] colWidth = {40, 220, 220};

    public ActorSearchForm() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // create manu bar
        MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar.createMenuBar());


        JPanel topPnl = new JPanel();
        GridBagLayout gbLayout = new GridBagLayout();
        topPnl.setLayout(gbLayout);

        JLabel fnameLbl = new JLabel("First Name");
        JLabel lnameLbl = new JLabel("Last Name");
        JTextField fnameTxf = new JTextField();
        JTextField lnameTxf = new JTextField();

        topPnl.add(fnameLbl, new GBC(0, 0).setIpad(100, 0)
                .setInsets(20, 0, 0, 30)
                .setAnchor(GridBagConstraints.WEST));
        topPnl.add(fnameTxf, new GBC(0, 1).setIpad(100, 0)
                .setInsets(5, 0, 0, 10)
                .setAnchor(GridBagConstraints.WEST));

        topPnl.add(lnameLbl, new GBC(1, 0).setIpad(100, 0)
                .setInsets(5, 0, 0, 10)
                .setWeight(100, 0)
                .setAnchor(GridBagConstraints.WEST));
        topPnl.add(lnameTxf, new GBC(1, 1).setIpad(100, 0)
                .setInsets(5, 0, 0, 10)
                .setWeight(100, 0)
                .setAnchor(GridBagConstraints.WEST));

        JButton searchBtn = new JButton("Search");

        topPnl.add(searchBtn, new GBC(0, 2).setIpad(80, 10)
                .setInsets(10, 0, 0, 10)
                .setAnchor(GridBagConstraints.WEST));

        add(topPnl, BorderLayout.NORTH);

        // Table To Be In Center And Make Control Width
        JTable table = new JTable();
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel middlePanel = new JPanel();
        GridBagLayout gbLayoutTable = new GridBagLayout();
        middlePanel.setLayout(gbLayoutTable);
        middlePanel.add(new JScrollPane(table), new GBC(0, 0)
                .setWeight(1.0, 1.0)
                .setInsets(5, 200, 0, 200)
                .setFill(GridBagConstraints.BOTH));

        add(middlePanel, BorderLayout.CENTER);

        // Btn Action
        searchBtn.addActionListener(ev -> {
            try {
                String firstName = fnameTxf.getText();
                String lastName = lnameTxf.getText();
                ActorDTO actor = new ActorDTO();
                actor.setFirst_name(firstName);
                actor.setLast_name(lastName);

                CachedRowSet rs = ActorDAO.searchActor(actor);

                // use the row set as the data source for the table model
                table.setModel(new ActorTableModel(rs));

                for(int i = 0; i < colWidth.length; i++) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(colWidth[i]);
                }
            }catch (SQLException e) {
                System.out.println(e.getErrorCode());
                e.printStackTrace();
            }
        });


        // Bottom Panel
        JPanel btmPnl = new JPanel();

        JButton nxtBtn = new JButton(">");
        nxtBtn.setPreferredSize(new Dimension(50, 30));

        JButton prevBtn = new JButton("<");
        prevBtn.setPreferredSize(new Dimension(50, 30));

        btmPnl.add(prevBtn);
        btmPnl.add(nxtBtn);

        add(btmPnl, BorderLayout.SOUTH);

        nxtBtn.addActionListener(ev -> {
            try {
                ActorTableModel model = (ActorTableModel) table.getModel();
                model.nextPage();
                // understand what this method is, and what is its purpose
                model.fireTableDataChanged();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        prevBtn.addActionListener(ev -> {
            try {
                ActorTableModel model = (ActorTableModel) table.getModel();
                model.prevPage();
                model.fireTableDataChanged();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }
}

class ActorTableModel extends AbstractTableModel {

    // crs is the only jdbc related object exposed to UI related
    private CachedRowSet crs = null;
    private String[] columnNames = { "Id", "First Name", "Last Name"};
    public ActorTableModel(CachedRowSet rs) {
        this.crs = rs;
    }

    // cannot return constant value, like 20 rows
    // why???
    @Override
    public int getRowCount() {
        return crs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Object obj = null;
        try{
            crs.beforeFirst();
            crs.absolute(rowIndex + 1);
            obj = crs.getObject(columnIndex + 1);
        } catch (SQLException e) {
            // again do proper exception handling
            System.out.println("Error in getValueAt(r, c): " + e.getMessage());
        }
        return  obj;
    }

    @Override
    public String getColumnName(int c) {
        return columnNames[c];
    }

    public void nextPage() throws SQLException{
        if(!crs.nextPage()) crs.previousPage();
    }

    public void prevPage() throws SQLException{
        if(!crs.previousPage()) crs.nextPage();
    }
}
