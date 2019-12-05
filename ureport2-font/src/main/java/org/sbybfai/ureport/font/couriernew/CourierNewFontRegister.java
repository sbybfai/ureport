package org.sbybfai.ureport.font.couriernew;

import org.sbybfai.ureport.export.pdf.font.FontRegister;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
public class CourierNewFontRegister implements FontRegister {

	public String getFontName() {
		return "Courier New";
	}

	public String getFontPath() {
		return "org/sbybfai/ureport/font/couriernew/COUR.TTF";
	}
}
