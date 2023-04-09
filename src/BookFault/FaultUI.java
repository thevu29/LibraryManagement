package BookFault;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class FaultUI {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable faultTable;
    private JButton addRowButton;
    private JButton bookDeleteAllButton;
    private JButton bookFilterButton;
    private JTabbedPane tabbedPane2;
    private JButton faultIDDelBtn;
    private JComboBox<String> faultIDComboBox;
    private JComboBox<String> faultNameCB;
    private JButton faultNameDelBtn;
    private JComboBox<String> heSoCB;
    private JButton heSoDelBtn;
    private JButton add_btn;
    private JButton xóaLỗiButton;
    private JButton chỉnhSửaLỗiButton;
    private JPanel panel_main;

    private JTextField bookIDTF;

    private final FaultModel faultModel = new FaultModel();

    public FaultUI() {
        faultModel.setEditable(false);

        faultModel.addTestData();
//        table2.setModel(test);
        faultTable.setModel(faultModel);
        TableRowSorter<FaultModel> sorter
                = new TableRowSorter<>(faultModel);
        faultTable.setRowSorter(sorter);
        faultTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        faultTable.getTableHeader().setBackground(Color.WHITE);

        addRowButton.addActionListener(e -> faultModel.addTestData());

        faultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = {faultTable.getSelectedRow(), faultTable.getSelectedColumn()};
                    System.out.println(pos[0] + " " + pos[1]);
                }
            }
        });
        faultTable.getTableHeader().setReorderingAllowed(false);

        var faultIDTF = AutoSuggestComboBox.createWithDelete(faultIDComboBox, 0 ,faultModel::getColumnValueToString, faultIDDelBtn);
        var faultNameTF = AutoSuggestComboBox.createWithDelete(faultNameCB, 1,  faultModel::getColumnValueToString, faultNameDelBtn);
        var heSoTF = AutoSuggestComboBox.createWithDelete(heSoCB, 2,  faultModel::getColumnValueToString, heSoDelBtn);

        bookDeleteAllButton.addActionListener(e -> {
            faultIDTF.setText("");
            faultNameTF.setText("");
            heSoTF.setText("");
        });

        faultModel.setFilterField(0, faultIDTF);
        faultModel.setFilterField(1, faultNameTF);
        faultModel.setFilterField(2, heSoTF);

        for (Iterator<TableColumn> it = faultTable.getColumnModel().getColumns().asIterator(); it.hasNext(); ) {
            var column = it.next();
            column.setMinWidth(100);
        }

        bookFilterButton.addActionListener(e -> {
            TableUtils.filter(faultTable);
        });
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FaultUI");
        frame.setContentPane(new FaultUI().panel_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
