package org.sbybfai.ureport.font.kaiti;

import org.sbybfai.ureport.export.pdf.font.FontRegister;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
public class KaiTiFontRegister implements FontRegister {

	public String getFontName() {
		return "楷体";
	}

	public String getFontPath() {
		return "org/sbybfai/ureport/font/kaiti/SIMKAI.TTF";
	}
}
