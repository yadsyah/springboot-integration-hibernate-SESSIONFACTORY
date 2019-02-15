package com.hibernatetutorial.demo.utility;

import com.hibernatetutorial.demo.entity.TblGolongan;
import com.hibernatetutorial.demo.entity.TblRasioTarif;
import com.hibernatetutorial.demo.entity.TblStandardHarga;
import com.hibernatetutorial.demo.loader.ProductJaminanLoader;
import com.hibernatetutorial.demo.loader.TarifLoader;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TaksiranHelper {


    public static Double calculateTaksiranPerhiasan(TblStandardHarga tblStl, String tipeJaminan, Long jumlah, Double berat, Double karat) {
        double nilaiTaksiran = 0;
        if (tblStl != null && jumlah > 0 && berat > 0 && karat > 0) {
            if (tipeJaminan.equals("KTTP")) {
                nilaiTaksiran = jumlah * berat * karat / 1000 * tblStl.getHargaPerak().doubleValue();
            } else if (tipeJaminan.equals("KTLM")) {
                nilaiTaksiran = berat * karat / 24 * tblStl.getHargaLantakanEmas().doubleValue();
            } else if (tipeJaminan.equals("KTML")) {
                nilaiTaksiran = jumlah * berat * karat / 24 * tblStl.getHargaEmas().doubleValue();
            } else {
                nilaiTaksiran = jumlah * berat * karat / 24 * +tblStl.getHargaEmas().doubleValue();
            }
        }
        return BussinesUtil.pembulatanAmount(nilaiTaksiran, 1.0);
    }

    public static TblGolongan getGolonganPinjaman(Double up) {
        TblGolongan tblGolongan = null;
        TblGolongan tblGolonganDown = null;
        ConcurrentHashMap<String, TblGolongan> hGolongan = ProductJaminanLoader.getListGolongan();
        List<String> keys = new ArrayList<>();
        keys.addAll(hGolongan.keySet());
        Collections.sort(keys);
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            TblGolongan tblGol = ProductJaminanLoader.getGolongan((String) it.next());
            if (up >= tblGol.getMinimalUP().doubleValue()
                    && up <= tblGol.getMaksimalUP().doubleValue()) {
                tblGolongan = tblGol;
                break;
            } else {
                if (up > tblGol.getMaksimalUP().doubleValue()) {
                    tblGolonganDown = tblGol;
                }
            }
        }
        if (tblGolongan == null) {
            return tblGolonganDown;
        }
        return tblGolongan;
    }


    private static class DateRasioComparator implements Comparator<TblRasioTarif> {

        @Override
        public int compare(TblRasioTarif o1, TblRasioTarif o2) {
            return o2.getTglBerlaku().compareTo(o1.getTglBerlaku());
        }
    }

    //String kodeTarif, String golongan, String kodeProduk, String rubrik, String tenor
    private final static DateRasioComparator rasioComparator = new DateRasioComparator();

    public static TblRasioTarif getRasioAndTarif(String kodeTarif, String golongan, String kodeProduk, String rubrik, String tenor) {
        TblRasioTarif tblTarif = null;
        ConcurrentHashMap<Long, TblRasioTarif> hListTarif = ProductJaminanLoader.getListRasioAndTarif(kodeTarif + golongan + kodeProduk + rubrik + tenor);
        if (hListTarif != null) {
            List<TblRasioTarif> keys = new ArrayList<TblRasioTarif>();
            for (Iterator<Long> it = hListTarif.keySet().iterator(); it.hasNext(); ) {
                keys.add((TblRasioTarif) hListTarif.get(it.next()));
            }
            Collections.sort(keys, rasioComparator);
            for (TblRasioTarif tarif : keys) {
                if (DateUtil.getBussinesDate().getTime() >= tarif.getTglBerlaku().getTime()) {
                    tblTarif = tarif;
                    break;
                }
            }
        }
        return tblTarif;
    }

    public static TblRasioTarif getRasioAndTarif(String kodeTarif, String golongan, String kodeProduk, String rubrik) {
        TblRasioTarif tblTarif = null;
        ConcurrentHashMap<Long, TblRasioTarif> hListTarif = ProductJaminanLoader.getListRasioAndTarif(kodeTarif + golongan + kodeProduk + rubrik);
        if (hListTarif != null) {
            List<TblRasioTarif> keys = new ArrayList<TblRasioTarif>();
            for (Iterator<Long> it = hListTarif.keySet().iterator(); it.hasNext(); ) {
                keys.add((TblRasioTarif) hListTarif.get(it.next()));
            }
            Collections.sort(keys, rasioComparator);
            for (TblRasioTarif tbRso : keys) {
                if (DateUtil.getBussinesDate().getTime() >= tbRso.getTglBerlaku().getTime()) {
                    tblTarif = tbRso;
                    break;
                }
            }
        }
        return tblTarif;
    }

    public static TblRasioTarif getRasioAndTarif(String kodeTarif, String golongan, String kodeProduk, String rubrik, Date tglKredit) {
        TblRasioTarif tblTarif = null;
        ConcurrentHashMap<Long, TblRasioTarif> hListTarif = ProductJaminanLoader.getListRasioAndTarif(kodeTarif + golongan + kodeProduk + rubrik);
        if (hListTarif != null) {
            List<TblRasioTarif> keys = new ArrayList<TblRasioTarif>();
            for (Iterator<Long> it = hListTarif.keySet().iterator(); it.hasNext(); ) {
                keys.add((TblRasioTarif) hListTarif.get(it.next()));
            }
            Collections.sort(keys, rasioComparator);
            for (TblRasioTarif tbRso : keys) {
                if (tglKredit.getTime() >= tbRso.getTglBerlaku().getTime()) {
                    tblTarif = tbRso;
                    break;
                }
            }
        }
        return tblTarif;
    }

    private static class DateComparator implements Comparator<TblStandardHarga> {

        @Override
        public int compare(TblStandardHarga o1, TblStandardHarga o2) {
            return o2.getTglBerlaku().compareTo(o1.getTglBerlaku());
        }
    }

    private final static DateComparator comparator = new DateComparator();

    public static final TblStandardHarga getStandardHargaBerlaku() {
        TblStandardHarga tblStandardHarga = null;
        ConcurrentHashMap<Long, TblStandardHarga> hListHarga = TarifLoader.getListStandardHarga();
        if (hListHarga != null) {
            List<TblStandardHarga> keys = new ArrayList<TblStandardHarga>();
            for (Iterator<Long> it = hListHarga.keySet().iterator(); it.hasNext(); ) {
                keys.add((TblStandardHarga) hListHarga.get(it.next()));
            }
            Collections.sort(keys, comparator);
            for (TblStandardHarga standardHarga : keys) {
                if (DateUtil.getBussinesDate().getTime() >= standardHarga.getTglBerlaku().getTime()) {
                    tblStandardHarga = standardHarga;
                    break;
                }
            }
        }
        return tblStandardHarga;
    }
}
