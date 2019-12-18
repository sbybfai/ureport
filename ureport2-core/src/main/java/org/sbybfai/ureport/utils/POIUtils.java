package org.sbybfai.ureport.utils;

import static java.lang.Math.round;

public class POIUtils {

    public static short getPOIColWidth(double point) {
        double pixel = UnitUtils.pointToPixel(point);
        double result = (pixel + 5) / 8 * 256;
        return (short)round(result);
    }

}
