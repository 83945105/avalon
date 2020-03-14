package pub.avalonframework.office.excel.impl;

import org.apache.poi.ss.usermodel.CellStyle;
import pub.avalonframework.office.excel.Font;

/**
 * @author baichao
 */
public class CellStyleWrapper implements pub.avalonframework.office.excel.CellStyle {

    private CellStyle cellStyle;

    private Font font;

    public CellStyleWrapper(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public CellStyleWrapper(CellStyle cellStyle, Font font) {
        this.cellStyle = cellStyle;
        this.font = font;
    }

    @Override
    public HorizontalAlignType getHorizontalAlignType() {
        return HorizontalAlignType.getHAlignByValue(this.cellStyle.getAlignment());
    }

    @Override
    public void setHorizontalAlignType(HorizontalAlignType hAlignType) {
        this.cellStyle.setAlignment(hAlignType.value);
    }

    @Override
    public VerticalAlignType getVerticalAlignType() {
        return VerticalAlignType.getVAlignByValue(this.cellStyle.getVerticalAlignment());
    }

    @Override
    public void setVerticalAlignType(VerticalAlignType vAlignType) {
        this.cellStyle.setVerticalAlignment(vAlignType.value);
    }

    @Override
    public BorderStyle getBorderLeftStyle() {
        return BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderLeft());
    }

    @Override
    public void setBorderLeftStyle(BorderStyle borderLeftStyle) {
        this.cellStyle.setBorderLeft(borderLeftStyle.value);
    }

    @Override
    public BorderStyle getBorderTopStyle() {
        return BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderTop());
    }

    @Override
    public void setBorderTopStyle(BorderStyle borderTopStyle) {
        this.cellStyle.setBorderTop(borderTopStyle.value);
    }

    @Override
    public BorderStyle getBorderRightStyle() {
        return BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderRight());
    }

    @Override
    public void setBorderRightStyle(BorderStyle borderRightStyle) {
        this.cellStyle.setBorderRight(borderRightStyle.value);
    }

    @Override
    public BorderStyle getBorderBottomStyle() {
        return BorderStyle.getBorderStyleByValue(this.cellStyle.getBorderBottom());
    }

    @Override
    public void setBorderBottomStyle(BorderStyle borderBottomStyle) {
        this.cellStyle.setBorderBottom(borderBottomStyle.value);
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
    public void setFont(Font font) {
        this.font = font;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
}