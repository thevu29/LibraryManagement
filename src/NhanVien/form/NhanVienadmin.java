package NhanVien.form;

import NhanVien.arraylistNV.NVDataTableModel;
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

public class NhanVienadmin {
    private JPanel main;
    private JButton addButton;
    private JButton findButton;
    private JButton delButton;
    private JButton editButton;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JButton delallButton;
    private JComboBox<String> comboBox2;
    private JButton delFliterIDButton;
    private JComboBox<String> comboBox3;
    private JButton delFlitercvButton;
    private JComboBox<String> comboBox1;
    private JButton delFliterNameButton;

    private NVDataTableModel NVmodel = new NVDataTableModel();

    public NhanVienadmin() {

        NVmodel.setEditable(false);

        table1.setModel(NVmodel);
        TableRowSorter<NVDataTableModel> sorter = new TableRowSorter<>(NVmodel);
        table1.setRowSorter(sorter);
        table1.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        table1.getTableHeader().setBackground(Color.WHITE);

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = {table1.getSelectedRow(), table1.getSelectedColumn()};
                    System.out.println(pos[0] + " " + pos[1]);
                }
            }
        });

        table1.getTableHeader().setReorderingAllowed(false);

        var IDNV = AutoSuggestComboBox.createWithDeleteBtn(comboBox3, 0 , NVmodel :: getColumnValueToString, delFliterIDButton );
        var NameNV = AutoSuggestComboBox.createWithDeleteBtn(comboBox2, 0 , NVmodel :: getColumnValueToString, delFliterNameButton );
        var CVNV = AutoSuggestComboBox.createWithDeleteBtn(comboBox1, 0 , NVmodel :: getColumnValueToString, delFlitercvButton );

        delallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDNV.setText("");
                NameNV.setText("");
                CVNV.setText("");
            }
        });

        NVmodel.setFilterField(0,IDNV);
        NVmodel.setFilterField(1,NameNV);
        NVmodel.setFilterField(4,CVNV);

        for (Iterator<TableColumn> it = table1.getColumnModel().getColumns().asIterator(); it.hasNext(); ) {
            var column = it.next();
            column.setMinWidth(100);
        }
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableUtils.filter(table1);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVienC a = new NhanVienC();
                a.setContentPane(a.getMain());
                a.setLocationRelativeTo(null);
                a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                a.setVisible(true);
                a.pack();
            }
        });
    }

    public JPanel getMainPanel() {
        return main;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NhanVienadmin");
        frame.setContentPane(new NhanVienadmin().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
