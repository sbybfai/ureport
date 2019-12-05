package org.sbybfai.ureport.font.songti;

import org.sbybfai.ureport.export.pdf.font.FontRegister;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
public class SongTiFontRegister implements FontRegister {

	public String getFontName() {
		return "宋体";
	}

	public String getFontPath() {
		return "org/sbybfai/ureport/font/songti/SIMSUN.TTC";
	}
}
