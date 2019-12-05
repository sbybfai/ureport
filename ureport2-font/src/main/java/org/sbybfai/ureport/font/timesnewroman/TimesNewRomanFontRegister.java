package org.sbybfai.ureport.font.timesnewroman;

import org.sbybfai.ureport.export.pdf.font.FontRegister;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
public class TimesNewRomanFontRegister implements FontRegister {

	public String getFontName() {
		return "Times New Roman";
	}

	public String getFontPath() {
		return "org/sbybfai/ureport/font/timesnewroman/TIMES.TTF";
	}
}
