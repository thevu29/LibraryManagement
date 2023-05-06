package sellBook.GUI;

import Book.BUS.BookBUS;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;
import sellBook.BUS.SellTicketBus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class HoaDon {
    private JPanel main;
    private JButton testAdd;
    private JTable tblCheckOut;
    private JButton btnFilter;
    private JButton btnIdXoa;
    private JComboBox cboIdHD;
    private JButton btnXoaTenKH;
    private JComboBox cboTenKH;
    DefaultTableModel dtm = new DefaultTableModel();
    private List<sellBook.DTO.HoaDon> dshd  ;
    private SellTicketBus sellTicketBus;



    public HoaDon() {


    }

}
