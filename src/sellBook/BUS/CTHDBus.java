package sellBook.BUS;

import sellBook.DAO.CTHDDao;
import sellBook.DTO.CTHD;

import java.util.List;

public class CTHDBus {
    private CTHDDao cthd ;

    public CTHDBus() {
        this.cthd = new CTHDDao();
    }
    public List<CTHD> getDsHD(String ma){
        return cthd.getDsCTHD(ma);
    }
    public List<String> getAllMaCTHD(String id){
        return cthd.getMaCTHD(id);
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
    public List<CTHD> filterMaCTHD(String id, String maCTHD){
        return cthd.locCTHD(id,maCTHD);
    }

    public List<CTHD> filterHeSo(String id,double hs){
        return cthd.locHeSo(id,hs);
    }
    public List<CTHD> filterMaSeri(String id,String ma){
        return cthd.locMaSeri(id,ma);
    }

    public static void main(String[] args) {
        CTHDBus t = new CTHDBus();
        List<CTHD> ds = t.filterMaSeri("1","7");
        for(CTHD h:ds){
            System.out.println(h.getMa_series());
        }
    }
}
