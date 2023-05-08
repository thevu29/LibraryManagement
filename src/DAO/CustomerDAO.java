package DAO;

import Core.DefaultConnection;
import BUS.CustomerBUS;
import DTO.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class CustomerDAO {
    public ArrayList<Customer> getCustomerAll() {
        ArrayList<Customer> list = new ArrayList<>();
        try {
            Connection con = DefaultConnection.getConnect();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select C.MA_KH, TEN, NGAY_SINH, DIA_CHI, CCCD, EMAIL, PHONE, GIOI_TINH, M.DANG_THE, M.NGAY_DK, M.NGAY_HH, C.IS_DELETED " +
                    "from `CUSTOMER` C LEFT JOIN `MEMBERSHIP` M on C.MA_KH = M.MA_KH and M.IS_DELETED = 0");

            while (rs.next()) {
                String id = rs.getString("MA_KH");
                String name = rs.getString("TEN");
                String dob = rs.getDate("NGAY_SINH").toString();
                String address = rs.getString("DIA_CHI");
                String cccd = rs.getString("CCCD");
                String email = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                String gender = rs.getString("GIOI_TINH");
                String membership = rs.getString("DANG_THE");
                String registrationDate = "";
                String expirationDate = "";
                boolean isDeleted = rs.getBoolean("IS_DELETED");

                if (Objects.isNull(membership)) {
                    membership = "Bình thường";
                    registrationDate = "";
                    expirationDate = "";
                } else {
                    registrationDate = rs.getDate("NGAY_DK").toString();
                    expirationDate = rs.getDate("NGAY_HH").toString();
                }

                list.add(new Customer(id, name, dob, address, cccd, email, phone, gender, membership, registrationDate, expirationDate, isDeleted));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addCustomer(Customer customer) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "insert into `CUSTOMER` values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setString(1, customer.getCustomerId());
            ptmt.setString(2, customer.getCustomerName());
            ptmt.setString(3, customer.getCustomerDOB());
            ptmt.setString(4, customer.getCustomerAddress());
            ptmt.setString(5, customer.getCccd());
            ptmt.setString(6, customer.getCustomerEmail());
            ptmt.setString(7, customer.getCustomerPhone());
            ptmt.setString(8, customer.getCustomerGender());
            ptmt.setInt(9, 0);

            if (ptmt.executeUpdate() == 1); {
                if (!customer.getMembership().equals("Bình thường")) {
                    if(addMembership(customer)) {
                        res = true;
                    }
                }
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean addMembership(Customer customer) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "insert into `MEMBERSHIP` values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ptmt = conn.prepareStatement(query);

            int length = getMembershipLength() + 1;
            String id = String.format("%03d", length);
            String memId = "MEM" + id;

            ptmt.setString(1, memId);
            ptmt.setString(2, customer.getCustomerId());
            ptmt.setString(3, customer.getMembership());
            ptmt.setString(4, customer.getRegistrationDate());
            ptmt.setString(5, customer.getExpirationDate());
            ptmt.setInt(6, 0);

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteCustomer(String id) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "update `CUSTOMER` set IS_DELETED = ? " + "where MA_KH = ?";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setInt(1, 1);
            ptmt.setString(2, id);

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }

            if (isCusHaveMembership(id)) {
                if (deleteMembership(id)) {
                    res = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean isCusHaveMembership(String customerID) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "SELECT MA_KH FROM MEMBERSHIP WHERE MA_KH=?";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setString(1, customerID);

            var rs = ptmt.executeQuery();
            res = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteMembership(String customerId) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "update `MEMBERSHIP` set IS_DELETED = ? " + "where MA_KH = ?";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setInt(1, 1);
            ptmt.setString(2, customerId);

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteDirectlyMembership(String customerId) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "delete from `MEMBERSHIP` where MA_KH = ?";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setString(1, customerId);

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean updateCustomer(Customer customer) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "update `CUSTOMER` set TEN = ?, NGAY_SINH = ?, DIA_CHI = ?, CCCD = ?, EMAIL = ?, PHONE = ?, GIOI_TINH = ? " +
                    "where MA_KH = ?";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setString(1, customer.getCustomerName());
            ptmt.setString(2, customer.getCustomerDOB());
            ptmt.setString(3, customer.getCustomerAddress());
            ptmt.setString(4, customer.getCccd());
            ptmt.setString(5, customer.getCustomerEmail());
            ptmt.setString(6, customer.getCustomerPhone());
            ptmt.setString(7, customer.getCustomerGender());
            ptmt.setString(8, customer.getCustomerId());

            if (ptmt.executeUpdate() >= 1) {
                if(updateMembership(customer)) {
                    res = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean updateMembership(Customer customer) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            if (customer.getMembership().equals("Bình thường")) {
                if (deleteDirectlyMembership(customer.getCustomerId())) {
                    res = true;
                }
            } else {
                boolean isMembership = new CustomerBUS().findMembershipByCustomerId(customer.getCustomerId()) != null ? true : false;

                if (isMembership) {
                    String query = "update `MEMBERSHIP` set DANG_THE = ?, NGAY_DK = ?, NGAY_HH = ?, IS_DELETED = 0 " + "where MA_KH = ?";
                    PreparedStatement ptmt = conn.prepareStatement(query);

                    ptmt.setString(1, customer.getMembership());
                    ptmt.setString(2, customer.getRegistrationDate());
                    ptmt.setString(3, customer.getExpirationDate());
                    ptmt.setString(4, customer.getCustomerId());

                    if (ptmt.executeUpdate() >= 1) {
                        res = true;
                    }
                } else {
                    if (addMembership(customer)) {
                        res = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int getMembershipLength() {
        int cnt = 0;
        try {
            Connection conn = DefaultConnection.getConnect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) as Length from `MEMBERSHIP`");

            if (rs.next()) {
                cnt = rs.getInt("Length");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }
}