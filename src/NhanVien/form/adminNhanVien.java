package NhanVien.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class adminNhanVien {

    private JButton add;
    private JPanel main;
    private JTable table1 ;


    private JButton change;
    DefaultTableModel modelStaff = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JButton del;
    private JTextField textField1;
    private JButton find;
    private JTextField textField2;
    private JLabel ID;
    private JLabel name;
    private JLabel position;
    private JComboBox comboBox1;

    public adminNhanVien() {
        inittable();
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelStaff.removeRow(table1.getSelectedRow());

            }
        });
    }

    public void inittable(){

        modelStaff.setColumnIdentifiers(new String[]{"ID","Tên","Giới tính","SDT","ngày làm việc","ngày sinh","địa chỉ","email","mật khẩu","ca làm việc","nơi làm việc","chức vụ","lương"});
        modelStaff.addRow(new String[]{"0","1","1","1","1","1","1","1","1","1","1","1","1"});
        modelStaff.addRow(new String[]{"1","1","1","1","1","1","1","1","1","1","1","1","1"});
        modelStaff.addRow(new String[]{"2","1","1","1","1","1","1","1","1","1","1","1","1"});
        modelStaff.addRow(new String[]{"3","1","1","1","1","1","1","1","1","1","1","1","1"});
//        modelStaff.isCellEditable();
        table1.setModel(modelStaff);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("adminNhanVien");
        frame.setContentPane(new adminNhanVien().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
