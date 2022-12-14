package UI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controllers.SeatController;
import Entitity.Theatre;
import Entitity.User;

public class ShowtimeScreen extends JFrame implements UI{
    private JButton backButton;
    private JPanel backgroundPanel;
    private JButton continueButton;
    private JLabel headerText;
    private JScrollPane jScrollPane1;
    private JPanel line;
    private JTable showtimeTable;
    private User user;
    private ArrayList<Theatre> theatres;
    private int t;
    private int m;


    /**
	 * ShowtimeScreen Constructor
	 * 
	 * @param user User object using the Screen
     * @param theatres list of theatres
     * @param t index of theatre 
     * @param m index of movie
     * @param s index of showtime
	*/    
    public ShowtimeScreen(User user, ArrayList<Theatre> theatres, int t, int m) {
        this.user = user;
        this.theatres = theatres;
        this.t = t;
        this.m = m;
        initComponents();
    }


    /**
	 * Function that initializes all components and displays them to the user
	 * 
	 * @param None
	*/       
    @Override
    public void initComponents() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }  

        backgroundPanel = new JPanel();
        backButton = new JButton();
        headerText = new JLabel();
        line = new JPanel();
        continueButton = new JButton();
        jScrollPane1 = new JScrollPane();
        showtimeTable = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cinemopolis");
        setBackground(new Color(0, 0, 0));
        setResizable(false);

        backgroundPanel.setBackground(Color.black);

        backButton.setBackground(new Color(221, 5, 37));
        backButton.setFont(new Font("Dubai", 1, 14)); // NOI18N
        backButton.setForeground(Color.white);
        backButton.setText("Back");
        backButton.setBorderPainted(false);

        headerText.setFont(new Font("Nirmala UI", 1, 48)); // NOI18N
        headerText.setForeground(Color.white);
        headerText.setText("Select a Showtime");
        headerText.setToolTipText("");

        line.setMaximumSize(new Dimension(0, 2));
        line.setMinimumSize(new Dimension(0, 2));

        GroupLayout lineLayout = new GroupLayout(line);
        line.setLayout(lineLayout);
        lineLayout.setHorizontalGroup(
            lineLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );
        lineLayout.setVerticalGroup(
            lineLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        continueButton.setBackground(new Color(221, 5, 37));
        continueButton.setFont(new Font("Dubai", 1, 20)); // NOI18N
        continueButton.setForeground(Color.white);
        continueButton.setText("Continue");
        continueButton.setBorderPainted(false);
        continueButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        showtimeTable.setTableHeader(null);
        showtimeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showtimeTable.setDefaultEditor(Object.class, null);
        showtimeTable.setBackground(new Color(77, 77, 77));
        showtimeTable.setBorder(new LineBorder(Color.white, 1, true));
        showtimeTable.setFont(new Font("Dubai", 1, 18)); // NOI18N
        showtimeTable.setForeground(Color.white);
        showtimeTable.setGridColor(Color.white);
        showtimeTable.setRowHeight(61);
        showtimeTable.setSelectionBackground(new Color(183, 183, 183));
        showtimeTable.setShowHorizontalLines(true);
        showtimeTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(showtimeTable);
        jScrollPane1.getViewport().setBackground(new Color(77,77,77));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.white));

        DefaultTableModel model = (DefaultTableModel)showtimeTable.getModel();
        model.addColumn(new Object[] {"bruh"});

        int i =0;
        while(i < theatres.get(0).getMovieList().get(m).getShowTimes().size()){
            model.addRow(new Object[] {theatres.get(0).getMovieList().get(m).getShowTimes().get(i).getTime()});
            i++;
        }

        showtimeTable.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (i = 0; i < showtimeTable.getModel().getColumnCount(); i++) {
            showtimeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        GroupLayout backgroundPanelLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                        .addComponent(line, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156))
                    .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(headerText)
                        .addContainerGap())))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(headerText)
                        .addGap(5, 5, 5)
                        .addComponent(line, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }                  


    /**
	 * Function that sends user back to the previous page if the back button is clicked
	 * 
	 * @param evt event used to trigger method
	*/ 
    private void backButtonActionPerformed(ActionEvent evt) {                                           
        dispose();     
        MovieScreen ms = new MovieScreen(this.user, this.theatres, this.t);
    }                                          


    /**
	 * Function that sends user to the seat selection page after they have selected a showtime and the continue button is pressed
	 * 
	 * @param evt event used to trigger method
	*/     
    private void continueButtonActionPerformed(ActionEvent evt) {    
        //get row number based on selected row   
        int row = showtimeTable.getSelectedRow();

        //Make sure something is selected
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please select a showtime to continue!","Error!", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        //Get corresponding seats to the showtime
        SeatController sco = new SeatController();
        sco.getSeats(theatres.get(t).getMovieList().get(m).getShowTimes().get(row));

        //Send user to the seats selction page
        sco.closeControl();
        dispose();
        SeatsScreen sc = new SeatsScreen(user, theatres, t, m, row);
    }                                                         
}
