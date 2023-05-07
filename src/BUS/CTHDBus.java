package BUS;

import DAO.BookDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import DAO.CTHDDao;
import DAO.SellTicketDao;
import DTO.CTHD;
import DTO.HDPDF;
import DTO.HoaDon;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CTHDBus {
    private CTHDDao cthd ;

    public BookDAO book = new BookDAO();

    public CTHDBus() {
        this.cthd = new CTHDDao();
    }
    public List<CTHD> getDsCTHD(String ma){
        return cthd.getDsCTHD(ma);
    }

    public List<String> getAllMaHD(String id){
        return cthd.getMaHD(id);
    }

    public List<String> getAllMaSeries(String id){
        return cthd.getMaSeries(id);
    }
    public List<Double> getAllHeSo(String id){
        return cthd.getHeSo(id);
    }
    public List<CTHD> filterMaCTHD(String id,String maSeri){
        return cthd.locCTHD(id,maSeri);
    }

    public List<CTHD> filterHeSo(String id,double hs){
        return cthd.locHeSo(id,hs);
    }
    public List<CTHD> filterMaSeri(String id,String ma){
        return cthd.locMaSeri(id,ma);
    }
    public int remove(String maCTHD,String maSeri){
        return cthd.removeCTHD(maCTHD,maSeri);
    }
    public int insert(CTHD hd){
        return cthd.insertCTHD(hd);
    }
    public int update(CTHD hd){
        return cthd.updateCTHD(hd);
    }
    public String goiYTenSach(String maSeri){
        return cthd.goiYTenSach(maSeri);
    }

    public double tinhTienSach(String maHD,String maSeri){
        return cthd.tinhTienSach(maHD,maSeri);
    }
    public double tienSach(String maSeri){
        return cthd.layGiaSach(maSeri);
    }
    public int updateStatusBook(String MaSeri,String tt){
        return cthd.changeTrangThaiSach(MaSeri,tt);
    }

    public void xoaHD(String maHD){
        cthd.hiddenCTHD(maHD);
        ArrayList<CTHD> dsCthd = cthd.getDsCTHD(maHD);
        if(!dsCthd.isEmpty()){
            for (CTHD ct:dsCthd) {
                System.out.println("Hello");
                System.out.println(ct.getMa_series());
                book.changeTrangThaiSach(ct.getMa_series(),"AVAILABLE");
            }
        }

    }
    public DefaultCategoryDataset thongKeSlgSachBan(){
        return cthd.thongKeSLGSachBanTheoThang();
    }

    public DefaultPieDataset thongKeSoLoaiDcBan(){
        return cthd.thongKeSoLoaiSach();
    }
    public DefaultCategoryDataset thongKeSachBanTheoNam(int nam){
        return cthd.thongKeSachBanTheoNam(nam);
    }

    public double layHS(String maSach){
        return cthd.layHeSo(maSach);
    }

    public int xuatPDF(String maHD){
        int kq = 0;
        Document document = new Document();

        SellTicketDao hdDao = new SellTicketDao();
        List<HoaDon> dshd = hdDao.locMaHD(maHD);

        String fontLoc = "Font/calibri.ttf";

        try {
            PdfWriter.getInstance(document,new FileOutputStream("./PDF/PhieuBan.pdf"));
            document.open();
            BaseFont bf = BaseFont.createFont(fontLoc, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font f = new Font(bf, 13);
            Paragraph paragraph1 = new Paragraph("PHIẾU BÁN "+dshd.get(0).getMa_phieu(),f);
            paragraph1.setIndentationLeft(80);
            paragraph1.setIndentationRight(80);
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            paragraph1.setSpacingAfter(15);

            Paragraph paragraph2 = new Paragraph("TÊN KHÁCH HÀNG: "+dshd.get(0).getTenKH(),f);
            paragraph2.setIndentationLeft(80);
            paragraph2.setIndentationRight(80);
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            paragraph2.setSpacingAfter(15);

            Paragraph paragraph3 = new Paragraph("TÊN NHÂN VIÊN: "+dshd.get(0).getTenNV(),f);
            paragraph3.setIndentationLeft(80);
            paragraph3.setIndentationRight(80);
            paragraph3.setAlignment(Element.ALIGN_CENTER);
            paragraph3.setSpacingAfter(15);

            Paragraph paragraph4 = new Paragraph("NGÀY MUA: "+dshd.get(0).getCreatedAt(),f);
            paragraph4.setIndentationLeft(80);
            paragraph4.setIndentationRight(80);
            paragraph4.setAlignment(Element.ALIGN_CENTER);
            paragraph4.setSpacingAfter(15);

            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);

            PdfPTable table = new PdfPTable(5);
            //Khởi tạo 3 ô header
            PdfPCell maSach = new PdfPCell(new Paragraph("Book Id"));
            PdfPCell tenSach = new PdfPCell(new Paragraph("Book Name"));
            tenSach.setColspan(2);
            PdfPCell heSo = new PdfPCell(new Paragraph("Factor"));
            PdfPCell tienSach = new PdfPCell(new Paragraph("Book Price"));
            //Thêm 3 ô header vào table
            table.addCell(maSach);
            table.addCell(tenSach);
            table.addCell(heSo);
            table.addCell(tienSach);

            List<HDPDF> ds = cthd.xuatCTHDPDF(maHD);

            for(HDPDF hd:ds){
                PdfPCell cell2 = new PdfPCell(new Paragraph(hd.getMaSach()));
                PdfPCell cell3 = new PdfPCell(new Paragraph(hd.getTenSach()));
                cell3.setColspan(2);
                PdfPCell cell4 = new PdfPCell(new Paragraph(String.valueOf(hd.getHeSo()) ));
                PdfPCell cell5 = new PdfPCell(new Paragraph(String.valueOf(hd.getGiaSach())));

                // Thêm các ô vào hàng mới
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);

                // Thêm hàng vào bảng
                table.completeRow();
            }

            PdfPCell tongTien = new PdfPCell(new Paragraph("Total: "+dshd.get(0).getTongHD()+" VND " ));
            tongTien.setColspan(5);
            table.addCell(tongTien);

            document.add(table);
            document.close();
            kq =  1;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
        return kq;
    }

    public static void main(String[] args) {
        CTHDBus t = new CTHDBus();
//        List<CTHD> ds = t.filterMaCTHD("1","2");
//        for(CTHD h:ds){
//            System.out.println(h.getMa_series());
//        }
//        System.out.println(t.goiYTenSach("1"));
        t.xuatPDF("HD1");
    }
}
