package org.sbybfai.ureport.font.fangsong;

import org.sbybfai.ureport.export.pdf.font.FontRegister;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
public class FangSongFontRegister implements FontRegister {

	public String getFontName() {
		return "仿宋";
	}

	public String getFontPath() {
		return "org/sbybfai/ureport/font/fangsong/SIMFANG.TTF";
	}
}
