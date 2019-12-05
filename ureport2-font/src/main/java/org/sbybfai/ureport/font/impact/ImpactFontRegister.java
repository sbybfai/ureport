package org.sbybfai.ureport.font.impact;

import org.sbybfai.ureport.export.pdf.font.FontRegister;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
public class ImpactFontRegister implements FontRegister {

	public String getFontName() {
		return "Impact";
	}

	public String getFontPath() {
		return "org/sbybfai/ureport/font/impact/IMPACT.TTF";
	}
}
