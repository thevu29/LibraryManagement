package BookFault;

import Customer.model.Customer;
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
    private JButton deleteFaultButton;
    private JButton editFaultButton;
    private JPanel panel_main;

    private JTextField bookIDTF;

    public void showFaultInfo(String id, String tenLoi, String heSo,String btnText) {
        FaultInfor faultDetailUI = new FaultInfor(this, id, tenLoi, heSo, btnText);
        faultDetailUI.setContentPane(faultDetailUI.getContentPane());
        faultDetailUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        faultDetailUI.setSize(500, 700);
        faultDetailUI.setLocationRelativeTo(null);
        faultDetailUI.setVisible(true);
    }
    private final FaultModel faultModel = new FaultModel();

    public FaultUI() {
        faultModel.setEditable(false);

        faultModel.addTestData();
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

        var faultIDTF = AutoSuggestComboBox.createWithDeleteBtn(faultIDComboBox, 0 ,faultModel::getColumnValueToString, faultIDDelBtn);
        var faultNameTF = AutoSuggestComboBox.createWithDeleteBtn(faultNameCB, 1,  faultModel::getColumnValueToString, faultNameDelBtn);
        var heSoTF = AutoSuggestComboBox.createWithDeleteBtn(heSoCB, 2,  faultModel::getColumnValueToString, heSoDelBtn);

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
                showFaultInfo("","","","Thêm lỗi");
            }
        });
        editFaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String id = faultTable.getValueAt(selectedRow, 0).toString();
                String tenLoi = faultTable.getValueAt(selectedRow, 1).toString();
                String maLoi = faultTable.getValueAt(selectedRow, 2).toString();
                showFaultInfo(id, tenLoi,maLoi, "Lưu thông tin");
            }
        });
        deleteFaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi muốn xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa lỗi này không?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = faultTable.getValueAt(selectedRow, 0).toString();
                faultModel.deleteTestData(id);

                JOptionPane.showMessageDialog(null, "Xóa lỗi thành công");
                faultModel.renderTable();
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
