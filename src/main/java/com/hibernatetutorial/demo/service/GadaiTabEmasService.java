package com.hibernatetutorial.demo.service;


import com.hibernatetutorial.demo.constant.UtilityConstant;
import com.hibernatetutorial.demo.dao.TblRekeningEmasDAO;
import com.hibernatetutorial.demo.dao.TblRekeningNasabahDAO;
import com.hibernatetutorial.demo.dao.TblTabunganDAO;
import com.hibernatetutorial.demo.entity.*;
import com.hibernatetutorial.demo.loader.ProductLoader;
import com.hibernatetutorial.demo.payload.request.GTE.SimulasiGTERequest;
import com.hibernatetutorial.demo.payload.response.GTE.SimulasiGTEResponse;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import com.hibernatetutorial.demo.utility.BussinesUtil;
import com.hibernatetutorial.demo.utility.TaksiranHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class GadaiTabEmasService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GadaiTabEmasService.class);
    @Autowired
    TblTabunganDAO tblTabunganDAO;

    @Autowired
    TblRekeningNasabahDAO tblRekeningNasabahDAO;

    @Autowired
    TblRekeningEmasDAO tblRekeningEmasDAO;

    SimulasiGTEResponse gteResponse = null;
    TblStandardHarga hargaEmas = null;

    public DataApiResponse simulasiKreditEmas(SimulasiGTERequest simulasiGTERequest) {
        Class methodName = new Object(){}.getClass();
        LOGGER.info(UtilityConstant.INFO_SERVICE.START_SERVICE.getDescription(methodName));
        gteResponse = new SimulasiGTEResponse();
        String valid = "";
        try {
            TblTabungan tblTabungan = tblTabunganDAO.findById(simulasiGTERequest.getNoRek());
            hargaEmas = TaksiranHelper.getStandardHargaBerlaku();
            TblProduct product = ProductLoader.getProductBy("32");
            gteResponse.setHargaSTL(hargaEmas.getHargaEmas().toString());
            if (tblTabungan != null) {
                gteResponse.setCif(tblTabungan.getCif());
                gteResponse.setNoRek(tblTabungan.getNorek());
                gteResponse.setNoBukuTabungan(tblTabungan.getNoBuku());
                gteResponse.setNamaNasabah(tblTabungan.getCustName());
                gteResponse.setJangkaWaktu(simulasiGTERequest.getJangkaWaktu());
                gteResponse.setUangNasabah(simulasiGTERequest.getUangNasabah());
                gteResponse.setGramNasabah(simulasiGTERequest.getGramNasabah());
                gteResponse.setJenisInputTransaksi(simulasiGTERequest.getJenisInputTransaksi());
                gteResponse.setNominalJual(simulasiGTERequest.getUangNasabah());
                TblRekeningNasabah rekeningNasabah = tblRekeningNasabahDAO.findByNorek(simulasiGTERequest.getNoRek());
                if (rekeningNasabah != null) {
                    gteResponse.setSaldoNominal(rekeningNasabah.getSaldoAkhir().doubleValue());
                }
                TblRekeningEmas rekeningEmas = tblRekeningEmasDAO.findByNorek(simulasiGTERequest.getNoRek());
                if (rekeningEmas != null) {
                    double saldoGram = rekeningEmas.getSaldoAkhir().doubleValue();
                    double saldoBlokir = rekeningEmas.getSaldoBlokir() != null ? rekeningEmas.getSaldoBlokir().doubleValue() : 0.0;
                    double saldoTrx = (double) Math.round((saldoGram - saldoBlokir) * 10000) / 10000;
                    gteResponse.setSaldoGram(saldoGram);
                    gteResponse.setSaldoBlokir(saldoBlokir);
                    gteResponse.setSaldoTransaksi(saldoTrx);
                }
                if (simulasiGTERequest.getJenisInputTransaksi().equals("UANG")) {
                    valid = doSumUangPinjaman(false, simulasiGTERequest.getGramNasabah() != null ? Double.valueOf(simulasiGTERequest.getGramNasabah()) : 0.0,
                            Double.valueOf(simulasiGTERequest.getUangNasabah()), product.getKodeProduct(),
                            String.valueOf(simulasiGTERequest.getJangkaWaktu()), hargaEmas.getHargaEmas().doubleValue());
                } else {
                    valid = doSumUangPinjaman(true, Double.valueOf(simulasiGTERequest.getGramNasabah()),
                            Double.valueOf(simulasiGTERequest.getUangNasabah()), product.getKodeProduct(),
                            String.valueOf(simulasiGTERequest.getJangkaWaktu()), hargaEmas.getHargaEmas().doubleValue());
                }
                if (!valid.equals("")) {
                    LOGGER.info(UtilityConstant.INFO_SERVICE.END_SERVICE.getDescription(methodName));
                    return new DataApiResponse(false, valid);
                }
            } else {
                LOGGER.info(UtilityConstant.INFO_SERVICE.END_SERVICE.getDescription(methodName));
                return new DataApiResponse(false, "No rek Tabungan Not Found!");
            }
            LOGGER.info("Data : {}",gteResponse.toString());
            LOGGER.info(UtilityConstant.INFO_SERVICE.END_SERVICE.getDescription(methodName));
            return new DataApiResponse(true, gteResponse);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(UtilityConstant.INFO_SERVICE.END_SERVICE.getDescription(methodName));
            return new DataApiResponse(false, e.getMessage());
        }
    }


    private String doSumUangPinjaman(boolean isGram, Double gramJual, Double uangNasabah, String kodeProduct,
                                     String jangkaWaktu, Double hargaSTL) {
        TblGolongan golongan = null;
        double ttlTaksiran = 0;
        double maksimalPinjaman = 0;
        String rubrik = "KT";

        if (isGram) {
            ttlTaksiran = BussinesUtil.pembulatanAmount(TaksiranHelper.calculateTaksiranPerhiasan(hargaEmas,
                    "KTTE", 1L, gramJual, Double.parseDouble("24")), 1.0);
        } else {
            maksimalPinjaman = uangNasabah;
            double pinjamanMinProduct = ProductLoader.getProductBy(kodeProduct).getPinjamanMin().doubleValue();
            if (maksimalPinjaman < ProductLoader.getProductBy(kodeProduct).getPinjamanMin().doubleValue()) {
                return "Nominal Uang Pinjaman Nasabah tidak boleh kurang dari Minimal Pinjaman";
            }
            golongan = TaksiranHelper.getGolonganPinjaman(maksimalPinjaman);
            TblRasioTarif tblRasio = TaksiranHelper.getRasioAndTarif("RSTAKSIR", golongan.getKodeGolongan(),
                    kodeProduct, rubrik, jangkaWaktu);
            if (tblRasio != null) {
                ttlTaksiran = BussinesUtil.pembulatanAmount(maksimalPinjaman * 100 / tblRasio.getTarifOrRasio(), 1.0);
                double beratTabungan = ttlTaksiran / hargaSTL;
                gteResponse.setNominalJualUl((Double) Math.ceil(beratTabungan * 10000) / 10000);
                gteResponse.setGramJual((Double) Math.ceil(beratTabungan * 10000)/10000);
            }
        }
        golongan = TaksiranHelper.getGolonganPinjaman(ttlTaksiran);
        if (golongan != null) {
            TblRasioTarif tblRasio = TaksiranHelper.getRasioAndTarif("RSTAKSIR", golongan.getKodeGolongan(),
                    kodeProduct, rubrik, jangkaWaktu);

            if (tblRasio != null) {
                maksimalPinjaman = (double) tblRasio.getIdRasioTarif() / 100 * ttlTaksiran;
                maksimalPinjaman = BussinesUtil.pembulatanAmountUp(maksimalPinjaman, golongan.getPembulatanUP().doubleValue(),
                        golongan.getJenisPembulatan());

                if (maksimalPinjaman > golongan.getMaksimalUP().doubleValue())
                    maksimalPinjaman = golongan.getMaksimalUP().doubleValue();

                if (maksimalPinjaman < golongan.getMinimalUP().doubleValue()) {
                    TblGolongan golonganMaxPinjam = TaksiranHelper.getGolonganPinjaman(maksimalPinjaman);
                    if (golonganMaxPinjam != null && !golonganMaxPinjam.getKodeGolongan().equals(golongan.getKodeGolongan())) {
                        golongan = golonganMaxPinjam;
                        tblRasio = TaksiranHelper.getRasioAndTarif("RSTAKSIR", golongan.getKodeGolongan(),
                                kodeProduct, rubrik, jangkaWaktu);
                        if (tblRasio != null && tblRasio.getTarifOrRasio() > 0) {
                            maksimalPinjaman = (double) tblRasio.getTarifOrRasio() / 100 * ttlTaksiran;
                            maksimalPinjaman = BussinesUtil.pembulatanAmountUp(maksimalPinjaman,
                                    golongan.getPembulatanUP().doubleValue(), golongan.getJenisPembulatan());

                            if (maksimalPinjaman > golongan.getMaksimalUP().doubleValue()) {
                                maksimalPinjaman = golongan.getMaksimalUP().doubleValue();
                            }
                        }
                    }
                }
                BigDecimal tempDecimal = BigDecimal.valueOf(maksimalPinjaman).setScale(2);
                gteResponse.setNominalJual(String.valueOf(tempDecimal));
            } else {
                gteResponse.setNominalJual("");
            }
        }
        if (gteResponse.getNominalJualUl() > gteResponse.getSaldoGram()) {
            return "Saldo Gram Tidak Mencukupi!";
        }
        return "";
    }
}
