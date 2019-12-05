package org.sbybfai.ureport.font.comicsansms;

import org.sbybfai.ureport.export.pdf.font.FontRegister;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
public class ComicSansMSFontRegister implements FontRegister {

	public String getFontName() {
		return "Comic Sans MS";
	}

	public String getFontPath() {
		return "org/sbybfai/ureport/font/comicsansms/COMIC.TTF";
	}
}
