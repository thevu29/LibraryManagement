package Customer.BUS;

import Customer.DAO.CustomerDAO;
import Customer.DTO.Customer;
import Utils.ValidationContract.Validation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CustomerBUS {
    private ArrayList<Customer> customerList;
    private CustomerDAO cusDAO = new CustomerDAO();

    public CustomerBUS() {
        customerList = cusDAO.getCustomerAll();

        for (Customer customer : customerList) {
            customer.setCustomerDOB(reverseDate(customer.getCustomerDOB()));
            customer.setRegistrationDate(reverseDate(customer.getRegistrationDate()));
            customer.setExpirationDate(reverseDate(customer.getExpirationDate()));
        }
    }

    public void importExcel(String path) {
        int cnt = 0;
        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook workbook = getWorkBook(fis, path);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) {
                    continue;
                }

                Customer customer = new Customer();

                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);

                    switch (cell.getColumnIndex()) {
                        case 0:
                            customer.setCustomerId(cellValue.toString());
                            break;
                        case 1:
                            customer.setCustomerName(cellValue.toString());
                            break;
                        case 2:
                            customer.setCustomerDOB(reverseDate(cellValue.toString()));
                            break;
                        case 3:
                            customer.setCustomerAddress(cellValue.toString());
                            break;
                        case 4:
                            customer.setCccd(cellValue.toString());
                            break;
                        case 5:
                            customer.setCustomerEmail(cellValue.toString());
                            break;
                        case 6:
                            customer.setCustomerPhone(cellValue.toString());
                            break;
                        case 7:
                            customer.setCustomerGender(cellValue.toString());
                            break;
                        case 8:
                            customer.setMembership(cellValue.toString());
                            break;
                        case 9:
                            customer.setRegistrationDate(reverseDate(cellValue.toString()));
                            break;
                        case 10:
                            customer.setExpirationDate(reverseDate(cellValue.toString()));
                            break;
                        default:
                            break;
                    }
                }

                if (customer.getMembership().equals("Bình thường")) {
                    customer.setRegistrationDate("");
                    customer.setExpirationDate("");
                }

                if (cusDAO.addCustomer(customer)) {
                    cnt++;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thể mở file", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        if (cnt > 0) {
            JOptionPane.showMessageDialog(null, "Đã import " + cnt + " dòng excel");
        } else {
            JOptionPane.showMessageDialog(null, "Import excel thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Object getCellValue(Cell cell) {
        Object cellValue = null;
        CellType cellType = cell.getCellType();

        switch (cellType) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case _NONE:
            case BLANK:
                cellValue = "";
                break;
            default:
                break;
        }

        return cellValue;
    }

    public Workbook getWorkBook(FileInputStream fis, String path) throws IOException {
        Workbook workbook = null;
        if (path.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(fis);
        } else if (path.endsWith("xls")) {
            workbook = new HSSFWorkbook(fis);
        } else {
            JOptionPane.showMessageDialog(null, "File excel không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return workbook;
    }

    public void exportExcel() {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("Customer");
        sheet.trackAllColumnsForAutoSizing();

        int rowIndex = 0;

        writeExcelTitle(sheet, rowIndex);

        for (Customer customer : customerList) {
            if (!customer.isDeleted()) {
                rowIndex++;
                SXSSFRow row = sheet.createRow(rowIndex);
                writeExcelData(customer, row);
            }
        }

        autoResizeColumn(sheet, 11);

        if(writeExcel(workbook)) {
            JOptionPane.showMessageDialog(null, "Xuất danh sách khách hàng thành file excel thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Xuất danh sách khách hàng thành file excel thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean writeExcel(SXSSFWorkbook workbook) {
        try {
            FileOutputStream fos = new FileOutputStream("./Excel/customer.xlsx");
            workbook.write(fos);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void writeExcelTitle(SXSSFSheet sheet, int rowIndex) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);

        SXSSFRow row = sheet.createRow(rowIndex);

        SXSSFCell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã khách hàng");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên khách hàng");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày sinh");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Địa chỉ");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("CCCD");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số điện thoại");

        cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giới tính");

        cell = row.createCell(8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Loại thành viên");

        cell = row.createCell(9);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày đăng ký thành viên");

        cell = row.createCell(10);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày hết hạn thành viên");
    }

    public void writeExcelData(Customer customer, SXSSFRow row) {
        SXSSFCell cell = row.createCell(0);
        cell.setCellValue(customer.getCustomerId());

        cell = row.createCell(1);
        cell.setCellValue(customer.getCustomerName());

        cell = row.createCell(2);
        cell.setCellValue(customer.getCustomerDOB());

        cell = row.createCell(3);
        cell.setCellValue(customer.getCustomerAddress());

        cell = row.createCell(4);
        cell.setCellValue(customer.getCccd());

        cell = row.createCell(5);
        cell.setCellValue(customer.getCustomerEmail());

        cell = row.createCell(6);
        cell.setCellValue(customer.getCustomerPhone());

        cell = row.createCell(7);
        cell.setCellValue(customer.getCustomerGender());

        cell = row.createCell(8);
        cell.setCellValue(customer.getMembership());

        cell = row.createCell(9);
        cell.setCellValue(customer.getRegistrationDate());

        cell = row.createCell(10);
        cell.setCellValue(customer.getExpirationDate());
    }

    public void autoResizeColumn(SXSSFSheet sheet, int columns) {
        for (int i = 0; i < columns; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public boolean validateAdd(Customer customer) {
        if (!validateCustomerInfo(customer)) {
            return false;
        }

        if (findCustomerById(customer.getCustomerId()) != null) {
            JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        customer.setCustomerDOB(reverseDate(customer.getCustomerDOB()));
        customer.setRegistrationDate(reverseDate(customer.getRegistrationDate()));
        customer.setExpirationDate(reverseDate(customer.getExpirationDate()));
        if(cusDAO.addCustomer(customer)) {
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
            return true;
        }

        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean validateDelete(String id) {
        if (cusDAO.deleteCustomer(id)) {
            JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean validateUpdate(Customer customer) {
        if (!validateCustomerInfo(customer)) {
            return false;
        }

        customer.setCustomerDOB(reverseDate(customer.getCustomerDOB()));
        customer.setRegistrationDate(reverseDate(customer.getRegistrationDate()));
        customer.setExpirationDate(reverseDate(customer.getExpirationDate()));
        if (cusDAO.updateCustomer(customer)) {
            JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thành công");
            return true;
        }

        JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean validateEmpty(String id, String name, String dob, String address, String email, String phone, String membership,
                                 String registrationDate, String expirationDate) {
        StringBuilder sb = new StringBuilder();

        if (id.equals("")) {
            sb.append("Mã khách hàng không được để trống \n");
        }
        if (name.equals("")) {
            sb.append("Tên khách hàng không được để trống \n");
        }
        if (dob.equals("")) {
            sb.append("Ngày sinh không được để trống \n");
        }
        if (address.equals("")) {
            sb.append("Địa chỉ không được để trống \n");
        }
        if (email.equals("")) {
            sb.append("Email không được để trống \n");
        }
        if (phone.equals("")) {
            sb.append("Số điện thoại không được để trống \n");
        }

        if (!membership.equals("Bình thường")) {
            if (registrationDate.equals("")) {
                sb.append("Ngày đăng ký không được để trống \n");
            }
            if (expirationDate.equals("")) {
                sb.append("Ngày hết hạn không được để trống \n");
            }
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean validateCustomerInfo(Customer customer) {
        if (!validateEmpty(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerDOB(), customer.getCustomerAddress(),
                customer.getCustomerEmail(), customer.getCustomerPhone(), customer.getMembership(), customer.getRegistrationDate(),
                customer.getExpirationDate())) {
            return false;
        }

        if (!Validation.isValidDate(customer.getCustomerDOB())) {
            JOptionPane.showMessageDialog(null, "Ngày sinh phải là số có định dạng dd-mm-yyyy ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Validation.isValidEmail(customer.getCustomerEmail())) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Validation.isValidPhoneNumber(customer.getCustomerPhone())) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải là 10 chữ số", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Validation.isValidDate(customer.getRegistrationDate())) {
            JOptionPane.showMessageDialog(null, "Ngày đăng kí phải là số có định dạng dd-mm-yyyy ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Validation.isValidDate(customer.getExpirationDate())) {
            JOptionPane.showMessageDialog(null, "Ngày hết hạn phải là số có định dạng dd-mm-yyyy ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public int countMembershipByMonth(int month, String type) {
        int cnt = 0;
        int regisMonth = 0;
        for (Customer customer : customerList) {
            if (type.equals("Tất cả")) {
                if (!customer.getMembership().equals("Bình thường")) {
                    regisMonth = findRegistrationMonth(customer.getRegistrationDate());
                    if (regisMonth == month) {
                        cnt++;
                    }
                }
            } else {
                if (customer.getMembership().equals(type)) {
                    regisMonth = findRegistrationMonth(customer.getRegistrationDate());
                    if (regisMonth == month) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public int findRegistrationMonth(String date) {
        int month = 0;
        month = Integer.parseInt(date.split("-")[1]);
        return month;
    }

    public Customer findCustomerById(String id) {
        for (Customer cus : customerList) {
            if (cus.getCustomerId().equals(id)) {
                return cus;
            }
        }
        return null;
    }

    public Customer findMembershipByCustomerId(String cusId) {
        for (Customer cus : customerList) {
            if (!cus.getMembership().equals("Bình thường")) {
                if (cus.getCustomerId().equals(cusId)) {
                    return cus;
                }
            }
        }
        return null;
    }

    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);

        customerList = cusDAO.getCustomerAll();
        for (Customer customer : customerList) {
            customer.setCustomerDOB(reverseDate(customer.getCustomerDOB()));
            customer.setRegistrationDate(reverseDate(customer.getRegistrationDate()));
            customer.setExpirationDate(reverseDate(customer.getExpirationDate()));
        }
        customerList.sort((a, b) -> a.getCustomerId().compareTo(b.getCustomerId()));

        for (Customer customer : customerList) {
            if (!customer.isDeleted()) {
                tblModel.addRow(new Object[]{customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(),
                        customer.getMembership()});
            }
        }

        tblModel.fireTableDataChanged();
    }

    public String reverseDate(String date) {
        if (date.equals("")) {
            return "";
        }

        String newDate = "";
        String[] arr = date.split("-");

        if (arr.length < 2) {
            return date;
        }

        newDate = arr[2] + "-" + arr[1] + "-" + arr[0];
        return newDate;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public int getCustomerListLength() {
        return customerList.size();
    }
}