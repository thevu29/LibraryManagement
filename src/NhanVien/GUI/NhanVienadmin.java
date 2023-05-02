package NhanVien.GUI;

import NhanVien.BUS.NVBUS;
import NhanVien.DTO.nhanVien;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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



    private  NVBUS NVBUS = new NVBUS();

    private int count = NVBUS.contNV();

    private NVDataTableModel NVmodel = new NVDataTableModel();
//    private final NVBUS;

//    private boolean check(NhanVienC nv){
//        if(nv.getID().getText().toString().equals("")){}
//    }

    public JPanel getMain(){
        return main;
    }

    public NhanVienadmin() {

        NVmodel.setEditable(false);
        NVmodel.setRows(NVBUS.getAllFromDatabase());
        table1.setModel(NVmodel);
        TableRowSorter<NVDataTableModel> sorter
                = new TableRowSorter<>(NVmodel);
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
        var NameNV = AutoSuggestComboBox.createWithDeleteBtn(comboBox2, 1 , NVmodel :: getColumnValueToString, delFliterNameButton );
        var CVNV = AutoSuggestComboBox.createWithDeleteBtn(comboBox1, 5 , NVmodel :: getColumnValueToString, delFlitercvButton );


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
                var a = new NhanVienC();
                var b = new JFrame();
                b.setContentPane(a.getMain());

                b.setVisible(true);
                b.pack();

                a.getID().setText("NV"+count);

                a.getLưuButton().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!a.check()){
                            return;
                        }
                        NVBUS.AddNV(new nhanVien(a.getID().getText(),a.getName().getText(),a.getPhone().getText(),a.getBrith().getText(),a.getAddress().getText(),a.getEmail().getText(),a.getPassword1().getText(),a.getShift().getSelectedIndex(),a.getPosition().getSelectedIndex(),a.getGender().getSelectedIndex(),a.getWordplace().getSelectedIndex(),Integer.parseInt(a.getDaywork().getText()),Integer.parseInt(a.getSalary().getText())));
                        NVmodel.addRow(new nhanVien(a.getID().getText(),a.getName().getText(),a.getPhone().getText(),a.getBrith().getText(),a.getAddress().getText(),a.getEmail().getText(),a.getPassword1().getText(),a.getShift().getSelectedIndex(),a.getPosition().getSelectedIndex(),a.getGender().getSelectedIndex(),a.getWordplace().getSelectedIndex(),Integer.parseInt(a.getDaywork().getText()),Integer.parseInt(a.getSalary().getText())));
                        b.dispose();
                        count++;
                    }
                });

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() < 0){
                    JOptionPane.showMessageDialog(null,"Bạn cần chọn nhân viên để thao tác","thông báo",JOptionPane.WARNING_MESSAGE);
                }else {

                var ro = table1.getSelectedRow();
                var a = new NhanVienC();
                var b = new JFrame();
                b.setContentPane(a.getMain());

                b.setVisible(true);
                b.pack();
                a.getID().setEditable(false);
                a.getID().setText(table1.getValueAt(ro,0).toString());
                a.getName().setText(table1.getValueAt(ro,1).toString());
                a.getPhone().setText(table1.getValueAt(ro,3).toString());
                a.getGender().setSelectedIndex(table1.getValueAt(ro,2).toString().equals("Nam") ? 0 : 1);
                a.getBrith().setText(table1.getValueAt(ro,11).toString());
                a.getAddress().setText(table1.getValueAt(ro,9).toString());
                a.getDaywork().setText(table1.getValueAt(ro,12).toString());
                a.getWordplace().setSelectedIndex(table1.getValueAt(ro,7).toString().equals("CS1") ? 0 : table1.getValueAt(ro,7).toString().equals("CS2") ?1:2);
                a.getPassword1().setText(table1.getValueAt(ro,4).toString());
                a.getPosition().setSelectedIndex(table1.getValueAt(ro,5).toString().equals("Librarian") ? 0 : 1);
                a.getEmail().setText(table1.getValueAt(ro,10).toString());
                int te =   Integer.parseInt(table1.getValueAt(ro,6).toString())/(1000* Integer.parseInt(table1.getValueAt(ro,12).toString()));
                a.getSalary().setText(te+"");
                a.getShift().setSelectedIndex(table1.getValueAt(ro,8).toString().equals("C1") ? 0 :table1.getValueAt(ro,8).toString().equals("C2") ? 1 : 2);

                a.getLưuButton().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!a.check()){
                            return;
                        }
                        var nvt = NVmodel.getRows();
                        for (int i = 0 ; i< nvt.size();i++) {
                            System.out.print("qq");
                            if(nvt.get(i).getID().equals(a.getID().getText())){
                                System.out.print("qq");
//                                nvt.get(i).setID(a.getID().getText());
//                                nvt.get(i).setName(a.getName().getText());
//                                nvt.get(i).setAddress(a.getAddress().getText());
//                                nvt.get(i).setEmail(a.getEmail().getText());
//                                nvt.get(i).setPassword(a.getPassword1().getText());
//                                nvt.get(i).setGender((int) a.getGender().getSelectedItem());
//                                nvt.get(i).setWork(a.getWordplace().getSelectedIndex());
//                                nvt.get(i).setShift((int) a.getShift().getSelectedItem());
//                                nvt.get(i).setDaywork(Integer.parseInt (a.getDaywork().getText()));
//                                nvt.get(i).setSalary(Integer.parseInt(a.getSalary().getText()));
//                                nvt.get(i).setBirth(a.getBrith().getText());
//                                nvt.get(i).setPosition((int) a.getPosition().getSelectedItem());
//                                nvt.get(i).setPhone(a.getPhone().getText());
                                NVBUS.EditNV(new nhanVien(a.getID().getText(),a.getName().getText(),a.getPhone().getText(),a.getBrith().getText(),a.getAddress().getText(),a.getEmail().getText(),a.getPassword1().getText(),a.getShift().getSelectedIndex(),a.getPosition().getSelectedIndex(),a.getGender().getSelectedIndex(),a.getWordplace().getSelectedIndex(),Integer.parseInt(a.getDaywork().getText()),Integer.parseInt(a.getSalary().getText())));
                                nvt.set(i,new nhanVien(a.getID().getText(),a.getName().getText(),a.getPhone().getText(),a.getBrith().getText(),a.getAddress().getText(),a.getEmail().getText(),a.getPassword1().getText(),a.getShift().getSelectedIndex(),a.getPosition().getSelectedIndex(),a.getGender().getSelectedIndex(),a.getWordplace().getSelectedIndex(),Integer.parseInt(a.getDaywork().getText()),Integer.parseInt(a.getSalary().getText())));
                            }
                        }
                        NVmodel.setRows(nvt);
//                        NVmodel.fireTableRowsInserted(0, nvt.size()-1);

//                        NVmodel.addRow(new nhanVien(a.getID().getText(),a.getName().getText(),a.getPhone().getText(),a.getBrith().getText(),a.getAddress().getText(),a.getEmail().getText(),a.getPassword1().getText(),a.getShift().getSelectedIndex(),a.getPosition().getSelectedIndex(),a.getGender().getSelectedIndex(),a.getWordplace().getSelectedIndex(),Integer.parseInt(a.getDaywork().getText()),Integer.parseInt(a.getSalary().getText())));
                        b.dispose();

                    }

            });
    }}});
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Bạn cần chọn nhân viên để thao tác", "thông báo", JOptionPane.WARNING_MESSAGE);
                } else {

                    var ro = table1.getSelectedRow();

                    var nvt = NVmodel.getRows();
                    for (int i = 0 ; i< nvt.size();i++) {
                        if (nvt.get(i).getID().toString().equals(table1.getValueAt(table1.getSelectedRow(), 0).toString())) {
                            NVBUS.removeNV(nvt.get(i).getID());
                            nvt.remove(i);
                        }
                    }
                    NVmodel.setRows(nvt);
                }
            }   });
    }


    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("NhanVienadmin");
        frame.setContentPane(new NhanVienadmin().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }




}
