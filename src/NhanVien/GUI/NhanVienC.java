package NhanVien.GUI;

import NhanVien.DTO.nhanVien;
import NhanVien.arraylistNV.NVDataTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private  NVDataTableModel NVDataTableModel = new NVDataTableModel();
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
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                ID.setText("");
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("NhanVienC");
        frame.setContentPane(new NhanVienC().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
