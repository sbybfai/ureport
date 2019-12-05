package org.sbybfai.ureport.font.arial;

import org.sbybfai.ureport.export.pdf.font.FontRegister;


/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
public class ArialFontRegister implements FontRegister {

	public String getFontName() {
		return "Arial";
	}

	public String getFontPath() {
		return "org/sbybfai/ureport/font/arial/ARIAL.TTF";
	}
}
