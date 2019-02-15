package com.hibernatetutorial.demo.utility;

public class BussinesUtil {


    public static double pembulatanAmount(Double original, Double pembulatan) {
        if (original % pembulatan == 0) {
            return original;
        }
        return original + (pembulatan - (original % pembulatan));
    }

    public static double pembulatanAmountDown(double original, double pembulatan) {
        if (original % pembulatan == 0) {
            return original;
        }
        return original - original % pembulatan;
    }

    public static double pembulatanAmountUp(double original, double pembulatan, String jenisPembulatan) {
        if (original % pembulatan == 0) {
            return original;
        }
        if (jenisPembulatan.equals("1")) {
            return original + (pembulatan - (original % pembulatan));
        } else {
            return original - original % pembulatan;
        }
    }

}
