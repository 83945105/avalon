package pub.avalonframework.office.excel.impl;

import pub.avalonframework.office.excel.CellStyle;
import pub.avalonframework.office.excel.Font;

/**
 * @author baichao
 */
public class CellStyleWrapper implements CellStyle {

    private org.apache.poi.ss.usermodel.CellStyle cellStyle;

    private Font font;

    public CellStyleWrapper(org.apache.poi.ss.usermodel.CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public CellStyleWrapper(org.apache.poi.ss.usermodel.CellStyle cellStyle, Font font) {
        this.cellStyle = cellStyle;
        this.font = font;
    }

    @Override
    public HorizontalAlignType getHorizontalAlignType() {
        return HorizontalAlignType.getHAlignByValue(this.cellStyle.getAlignment());
    }

    @Override
    public CellStyle setHorizontalAlignType(HorizontalAlignType hAlignType) {
        this.cellStyle.setAlignment(hAlignType.value);
        return this;
    }

    @Override
    public VerticalAlignType getVerticalAlignType() {
        return VerticalAlignType.getVAlignByValue(this.cellStyle.getVerticalAlignment());
    }

    @Override
    public CellStyle setVerticalAlignType(VerticalAlignType vAlignType) {
        this.cellStyle.setVerticalAlignment(vAlignType.value);
        return this;
    }

    @Override
    public BorderStyle getBorderLeftStyle() {
        return BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderLeft());
    }

    @Override
    public pub.avalonframework.office.excel.CellStyle setBorderLeftStyle(BorderStyle borderLeftStyle) {
        this.cellStyle.setBorderLeft(borderLeftStyle.value);
        return this;
    }

    @Override
    public BorderStyle getBorderTopStyle() {
        return BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderTop());
    }

    @Override
    public CellStyle setBorderTopStyle(BorderStyle borderTopStyle) {
        this.cellStyle.setBorderTop(borderTopStyle.value);
        return this;
    }

    @Override
    public BorderStyle getBorderRightStyle() {
        return BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderRight());
    }

    @Override
    public CellStyle setBorderRightStyle(BorderStyle borderRightStyle) {
        this.cellStyle.setBorderRight(borderRightStyle.value);
        return this;
    }

    @Override
    public BorderStyle getBorderBottomStyle() {
        return BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderBottom());
    }

    @Override
    public CellStyle setBorderBottomStyle(BorderStyle borderBottomStyle) {
        this.cellStyle.setBorderBottom(borderBottomStyle.value);
        return this;
    }

    @Override
    public BorderStyle[] getBorderStyle() {
        return new BorderStyle[]{
                BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderLeft()),
                BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderTop()),
                BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderRight()),
                BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderBottom())
        };
    }

    @Override
    public Font getFont() {
        return this.font;
    }

    @Override
    public CellStyle setFont(Font font) {
        this.font = font;
        return this;
    }

    public org.apache.poi.ss.usermodel.CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(org.apache.poi.ss.usermodel.CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
}