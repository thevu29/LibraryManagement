package NhanVien.GUI;

import NhanVien.DTO.nhanVien;
import NhanVien.arraylistNV.NVDataTableModel;
import Utils.ValidationContract;
import Utils.ValidationContract.Validation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NhanVienC {
    private JComboBox gender;
    private JPanel main;
    private JButton resetButton;
    private JButton lưuButton;
    private JTextField ID;
    private JTextField name;
    private JTextField phone;
    private JTextField daywork;
    private JTextField brith;
    private JTextField password1;
    private JTextField address;
    private JTextField email;
    private JComboBox shift;
    private JComboBox position;
    private JComboBox wordplace;
    private JTextField salary;
    private JTextField password2;

    public JComboBox getGender() {
        return gender;
    }

    public void setGender(JComboBox gender) {
        this.gender = gender;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void setResetButton(JButton resetButton) {
        this.resetButton = resetButton;
    }

    public JButton getLưuButton() {
        return lưuButton;
    }

    public void setLưuButton(JButton lưuButton) {
        this.lưuButton = lưuButton;
    }

    public JTextField getID() {
        return ID;
    }

    public void setID(JTextField ID) {
        this.ID = ID;
    }

    public JTextField getName() {
        return name;
    }

    public void setName(JTextField name) {
        this.name = name;
    }

    public JTextField getPhone() {
        return phone;
    }

    public void setPhone(JTextField phone) {
        this.phone = phone;
    }

    public JTextField getDaywork() {
        return daywork;
    }

    public void setDaywork(JTextField daywork) {
        this.daywork = daywork;
    }

    public JTextField getBrith() {
        return brith;
    }

    public void setBrith(JTextField brith) {
        this.brith = brith;
    }

    public JTextField getPassword1() {
        return password1;
    }

    public void setPassword1(JTextField password1) {
        this.password1 = password1;
    }

    public JTextField getAddress() {
        return address;
    }

    public void setAddress(JTextField address) {
        this.address = address;
    }

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JComboBox getShift() {
        return shift;
    }

    public void setShift(JComboBox shift) {
        this.shift = shift;
    }

    public JComboBox getPosition() {
        return position;
    }

    public void setPosition(JComboBox position) {
        this.position = position;
    }

    public JComboBox getWordplace() {
        return wordplace;
    }

    public void setWordplace(JComboBox wordplace) {
        this.wordplace = wordplace;
    }

    public JTextField getSalary() {
        return salary;
    }

    public void setSalary(JTextField salary) {
        this.salary = salary;
    }

    public JTextField getPassword2() {
        return password2;
    }

    public void setPassword2(JTextField password2) {
        this.password2 = password2;
    }

    public NhanVien.arraylistNV.NVDataTableModel getNVDataTableModel() {
        return NVDataTableModel;
    }

    public void setNVDataTableModel(NhanVien.arraylistNV.NVDataTableModel NVDataTableModel) {
        this.NVDataTableModel = NVDataTableModel;
    }

    private NVDataTableModel NVDataTableModel = new NVDataTableModel();

    //    public NhanVienC(nhanVien nvs, NVDataTableModel NVDataTableModel, String title){
//        this.NVDataTableModel = NVDataTableModel;
//        setContentPane(contentPane);
//        setModal(true);
//        getRootPane().setDefaultButton(buttonOK);
//    }
    public JPanel getMain() {
        return main;
    }

    public NhanVienC() {
        ID.setEditable(false);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                position.setSelectedIndex(0);
                daywork.setText("");
                address.setText("");
                wordplace.setSelectedIndex(0);
                brith.setText("");
                daywork.setText("");
                email.setText("");
                gender.setSelectedIndex(0);
                password1.setText("");
                password2.setText("");
                phone.setText("");
                shift.setSelectedIndex(0);
                salary.setText("");
            }
        });

    }

    public boolean check() {
        if (ID.getText().equals("") && name.getText().equals("") && salary.getText().equals("") && getDaywork().getText().equals("") && getBrith().getText().equals("") && email.getText().equals("") && address.getText().equals("") && password1.getText().equals("") && password2.getText().equals("") && password2.getText().equals("") && phone.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Bạn không được để trống vị trí thông tin nào!!", "thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!Validation.isValidCCCD(password2.getText())) {
            JOptionPane.showMessageDialog(null, "CCCD phải có 12 số!", "thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!Validation.isValidEmail(email.getText())) {
            JOptionPane.showMessageDialog(null, "Nhập email sai định dạng", "thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!brith.getText().matches("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$")) {
            JOptionPane.showMessageDialog(null, "Nhập ngày sinh dạng : YYYY-MM-DD và hợp lý", "thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!salary.getText().matches("[0-9]+") && !daywork.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Nhập Số điện thoại từ đúng định dạng 0 và 9-10 số liện sau", "thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!Validation.isValidPhoneNumber(phone.getText())) {
            JOptionPane.showMessageDialog(null, "Nhập Số điện thoại từ đúng định dạng 0 và 9-10 số liện sau", "thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NhanVienC");
        frame.setContentPane(new NhanVienC().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
