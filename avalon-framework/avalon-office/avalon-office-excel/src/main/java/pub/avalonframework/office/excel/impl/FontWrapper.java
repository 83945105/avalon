package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.Font;

/**
 * @author baichao
 */
public class FontWrapper implements Font {

    private org.apache.poi.ss.usermodel.Font font;

    public FontWrapper(org.apache.poi.ss.usermodel.Font font) {
        this.font = font;
    }

    @Override
    public Font setColor(FontColor fontColor) {
        this.font.setColor(fontColor.value);
        return this;
    }

    @Override
    public FontColor getColor() {
        return FontColor.getFontColorByValue(this.font.getColor());
    }

    @Override
    public Font setStrikeout(boolean strikeout) {
        this.font.setStrikeout(strikeout);
        return this;
    }

    @Override
    public boolean isStrikeout() {
        return this.font.getStrikeout();
    }

    @Override
    public Font setItalic(boolean italic) {
        this.font.setItalic(italic);
        return this;
    }

    @Override
    public boolean isItalic() {
        return this.font.getItalic();
    }

    @Override
    public Font setFontHeightInPoints(short size) {
        this.font.setFontHeightInPoints(size);
        return this;
    }

    @Override
    public short getFontHeightInPoints() {
        return this.font.getFontHeightInPoints();
    }

    @Override
    public Font setFontName(String fontName) {
        this.font.setFontName(fontName);
        return this;
    }

    @Override
    public String getFontName() {
        return this.font.getFontName();
    }

    @Override
    public Font setBoldWeight(boolean boldWeight) {
        this.font.setBoldweight(boldWeight ? org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD : org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_NORMAL);
        return this;
    }

    @Override
    public boolean isBoldWeight() {
        return this.font.getBoldweight() == org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD;
    }

    @Override
    public Font setUnderLine(UnderLine underLine) {
        this.font.setUnderline((byte) underLine.value);
        return this;
    }

    @Override
    public UnderLine getUnderLine() {
        switch (this.font.getUnderline()) {
            case 1:
                return UnderLine.SINGLE;
            case 2:
                return UnderLine.DOUBLE;
            case 3:
                return UnderLine.SINGLE_ACCOUNTING;
            case 4:
                return UnderLine.DOUBLE_ACCOUNTING;
            case 5:
                return UnderLine.NONE;
            default:
                return null;
        }
    }

    public org.apache.poi.ss.usermodel.Font getFont() {
        return font;
    }

    public void setFont(org.apache.poi.ss.usermodel.Font font) {
        this.font = font;
    }
}