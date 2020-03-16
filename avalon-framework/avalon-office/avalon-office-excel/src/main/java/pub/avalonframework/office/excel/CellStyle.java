package pub.avalonframework.office.excel;

/**
 * 单元格样式
 *
 * @author baichao
 */
public interface CellStyle {

    /**
     * 水平对齐方式
     */
    enum HorizontalAlignType {
        /**
         * 常规对齐
         */
        GENERAL((short) 0x0),
        /**
         * 靠左(缩进)
         */
        LEFT((short) 0x1),
        /**
         * 居中
         */
        CENTER((short) 0x2),
        /**
         * 靠右(缩进)
         */
        RIGHT((short) 0x3),
        /**
         * 填充
         */
        FILL((short) 0x4),
        /**
         * 两端对齐
         */
        JUSTIFY((short) 0x5),
        /**
         * 跨列居中
         */
        CENTER_SELECTION((short) 0x6),
        /**
         * 分散对齐
         */
        DISTRIBUTED((short) 0x7);

        public short value;

        HorizontalAlignType(short value) {
            this.value = value;
        }

        /**
         * 根据name获取水平枚举类型
         */
        public static HorizontalAlignType getHAlignByName(String name) {
            for (HorizontalAlignType alignType : HorizontalAlignType.values()) {
                if (alignType.name().equalsIgnoreCase(name)) {
                    return alignType;
                }
            }
            throw new RuntimeException("hAlign值不正确:" + name);
        }

        /**
         * 根据value获取水平枚举类型
         */
        public static HorizontalAlignType getHAlignByValue(short value) {
            for (HorizontalAlignType alignType : HorizontalAlignType.values()) {
                if (alignType.value == value) {
                    return alignType;
                }
            }
            throw new RuntimeException("hAlign值不正确:" + value);
        }
    }

    /**
     * 垂直对齐方式
     */
    enum VerticalAlignType {
        /**
         * 靠上
         */
        TOP((short) 0x0),
        /**
         * 居中
         */
        CENTER((short) 0x1),
        /**
         * 靠下
         */
        BOTTOM((short) 0x2),
        /**
         * 两端对齐
         */
        JUSTIFY((short) 0x3),
        /**
         * 分散对齐
         */
        DISTRIBUTED((short) 0x4);

        public short value;

        VerticalAlignType(short value) {
            this.value = value;
        }

        /**
         * 根据name获取垂直枚举类型
         */
        public static VerticalAlignType getVAlignByName(String name) {
            for (VerticalAlignType alignType : VerticalAlignType.values()) {
                if (alignType.name().equalsIgnoreCase(name)) {
                    return alignType;
                }
            }
            throw new RuntimeException("vAlign值不正确:" + name);
        }

        /**
         * 根据value获取垂直枚举类型
         */
        public static VerticalAlignType getVAlignByValue(short value) {
            for (VerticalAlignType alignType : VerticalAlignType.values()) {
                if (alignType.value == value) {
                    return alignType;
                }
            }
            throw new RuntimeException("vAlign值不正确:" + value);
        }
    }

    /**
     * 获取单元格水平对齐方式
     *
     * @return HorizontalAlignType
     */
    HorizontalAlignType getHorizontalAlignType();

    /**
     * 设置单元格水平对齐方式
     *
     * @param hAlignType HorizontalAlignType
     * @return CellStyle
     */
    CellStyle setHorizontalAlignType(HorizontalAlignType hAlignType);

    /**
     * 设置单元格水平对齐方式
     *
     * @param hAlignTypeString HorizontalAlignType
     * @return CellStyle
     */
    default CellStyle setHorizontalAlignType(String hAlignTypeString) {
        return this.setHorizontalAlignType(HorizontalAlignType.getHAlignByName(hAlignTypeString));
    }

    /**
     * 设置单元格水平对齐方式
     *
     * @param hAlignTypeShort HorizontalAlignType
     * @return CellStyle
     */
    default CellStyle setHorizontalAlignType(short hAlignTypeShort) {
        return this.setHorizontalAlignType(HorizontalAlignType.getHAlignByValue(hAlignTypeShort));
    }

    /**
     * 获取单元格垂直对齐方式
     *
     * @return VerticalAlignType
     */
    VerticalAlignType getVerticalAlignType();

    /**
     * 设置单元格垂直对齐方式
     *
     * @param vAlignType VerticalAlignType
     * @return CellStyle
     */
    CellStyle setVerticalAlignType(VerticalAlignType vAlignType);

    /**
     * 设置单元格垂直对齐方式
     *
     * @param vAlignTypeString VerticalAlignType
     * @return CellStyle
     */
    default CellStyle setVerticalAlignType(String vAlignTypeString) {
        return this.setVerticalAlignType(VerticalAlignType.getVAlignByName(vAlignTypeString));
    }

    /**
     * 设置单元格垂直对齐方式
     *
     * @param vAlignTypeShort VerticalAlignType
     * @return CellStyle
     */
    default CellStyle setVerticalAlignType(short vAlignTypeShort) {
        return this.setVerticalAlignType(VerticalAlignType.getVAlignByValue(vAlignTypeShort));
    }

    /**
     * 边框样式
     */
    enum BorderStyle {
        /**
         *
         */
        NONE((short) 0),
        /**
         *
         */
        THIN((short) 1),
        /**
         *
         */
        MEDIUM((short) 2),
        /**
         *
         */
        DASHED((short) 3),
        /**
         *
         */
        DOTTED((short) 4),
        /**
         *
         */
        THICK((short) 5),
        /**
         *
         */
        DOUBLE((short) 6),
        /**
         *
         */
        HAIR((short) 7),
        /**
         *
         */
        MEDIUM_DASHED((short) 8),
        /**
         *
         */
        DASH_DOT((short) 9),
        /**
         *
         */
        MEDIUM_DASH_DOT((short) 10),
        /**
         *
         */
        DASH_DOT_DOT((short) 11),
        /**
         *
         */
        MEDIUM_DASH_DOT_DOTC((short) 12),
        /**
         *
         */
        SLANTED_DASH_DOT((short) 13);

        public short value;

        BorderStyle(short value) {
            this.value = value;
        }

        /**
         * 根据name获取边框样式
         */
        public static BorderStyle getBorderStyleByName(String name) {
            for (BorderStyle borderStyle : BorderStyle.values()) {
                if (borderStyle.name().equalsIgnoreCase(name)) {
                    return borderStyle;
                }
            }
            throw new RuntimeException("borderStyle值不正确:" + name);
        }

        /**
         * 根据value获取边框样式
         */
        public static BorderStyle getBorderStyleByValue(short value) {
            for (BorderStyle borderStyle : BorderStyle.values()) {
                if (borderStyle.value == value) {
                    return borderStyle;
                }
            }
            throw new RuntimeException("borderStyle值不正确:" + value);
        }
    }

    /**
     * 获取左边框样式
     *
     * @return BorderStyle
     */
    BorderStyle getBorderLeftStyle();

    /**
     * 设置左边框样式
     *
     * @param borderLeftStyle BorderStyle
     * @return CellStyle
     */
    CellStyle setBorderLeftStyle(BorderStyle borderLeftStyle);

    /**
     * 设置左边框样式
     *
     * @param borderLeftStyleString BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderLeftStyle(String borderLeftStyleString) {
        return this.setBorderLeftStyle(BorderStyle.getBorderStyleByName(borderLeftStyleString));
    }

    /**
     * 设置左边框样式
     *
     * @param borderLeftStyleShort BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderLeftStyle(short borderLeftStyleShort) {
        return this.setBorderLeftStyle(BorderStyle.getBorderStyleByValue(borderLeftStyleShort));
    }

    /**
     * 获取上边框样式
     *
     * @return BorderStyle
     */
    BorderStyle getBorderTopStyle();

    /**
     * 设置上边框样式
     *
     * @param borderTopStyle BorderStyle
     * @return CellStyle
     */
    CellStyle setBorderTopStyle(BorderStyle borderTopStyle);

    /**
     * 设置上边框样式
     *
     * @param borderTopStyleString BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderTopStyle(String borderTopStyleString) {
        return this.setBorderTopStyle(BorderStyle.getBorderStyleByName(borderTopStyleString));
    }

    /**
     * 设置上边框样式
     *
     * @param value BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderTopStyle(short value) {
        return this.setBorderTopStyle(BorderStyle.getBorderStyleByValue(value));
    }

    /**
     * 获取右边框样式
     *
     * @return BorderStyle
     */
    BorderStyle getBorderRightStyle();

    /**
     * 设置右边框样式
     *
     * @param borderRightStyle BorderStyle
     * @return CellStyle
     */
    CellStyle setBorderRightStyle(BorderStyle borderRightStyle);

    /**
     * 设置右边框样式
     *
     * @param borderRightStyleString BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderRightStyle(String borderRightStyleString) {
        return this.setBorderRightStyle(BorderStyle.getBorderStyleByName(borderRightStyleString));
    }

    /**
     * 设置右边框样式
     *
     * @param borderRightStyleShort BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderRightStyle(short borderRightStyleShort) {
        return this.setBorderRightStyle(BorderStyle.getBorderStyleByValue(borderRightStyleShort));
    }

    /**
     * 获取下边框样式
     *
     * @return BorderStyle
     */
    BorderStyle getBorderBottomStyle();

    /**
     * 设置下边框样式
     *
     * @param borderBottomStyle BorderStyle
     * @return CellStyle
     */
    CellStyle setBorderBottomStyle(BorderStyle borderBottomStyle);

    /**
     * 设置下边框样式
     *
     * @param borderBottomStyleString BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderBottomStyle(String borderBottomStyleString) {
        return this.setBorderBottomStyle(BorderStyle.getBorderStyleByName(borderBottomStyleString));
    }

    /**
     * 设置下边框样式
     *
     * @param borderBottomStyleShort BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderBottomStyle(short borderBottomStyleShort) {
        return this.setBorderBottomStyle(BorderStyle.getBorderStyleByValue(borderBottomStyleShort));
    }

    /**
     * 获取边框样式
     *
     * @return 左边框, 上边框, 右边框, 下边框
     */
    BorderStyle[] getBorderStyle();

    /**
     * 设置四个边框样式,以","分隔,顺序为左上右下
     *
     * @param borderStyleStrings BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderStyle(String borderStyleStrings) {
        String[] borders = borderStyleStrings.split(",");
        this.setBorderLeftStyle(borders[0]);
        this.setBorderTopStyle(borders[1]);
        this.setBorderRightStyle(borders[2]);
        this.setBorderBottomStyle(borders[3]);
        return this;
    }

    /**
     * 设置四个边框样式,顺序为左上右下
     *
     * @param borderStyles BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderStyle(BorderStyle[] borderStyles) {
        StringBuilder sb = new StringBuilder();
        for (BorderStyle borderStyle : borderStyles) {
            sb.append(",").append(borderStyle.name());
        }
        this.setBorderStyle(sb.substring(1));
        return this;
    }

    /**
     * 设置四个边框样式,顺序为左上右下
     *
     * @param borderStyleShorts BorderStyle
     * @return CellStyle
     */
    default CellStyle setBorderStyle(short[] borderStyleShorts) {
        this.setBorderLeftStyle(borderStyleShorts[0]);
        this.setBorderTopStyle(borderStyleShorts[1]);
        this.setBorderRightStyle(borderStyleShorts[2]);
        this.setBorderBottomStyle(borderStyleShorts[3]);
        return this;
    }

    /**
     * 获取字体
     *
     * @return 字体
     */
    Font getFont();

    /**
     * 设置字体
     *
     * @param font Font
     * @return CellStyle
     */
    CellStyle setFont(Font font);

    /**
     * 默认的单元格样式实现
     */
    final class DefaultCellStyle implements CellStyle {

        private CellStyle.HorizontalAlignType hAlignType = CellStyle.HorizontalAlignType.CENTER;
        private CellStyle.VerticalAlignType vAlignType = CellStyle.VerticalAlignType.CENTER;
        private CellStyle.BorderStyle borderLeftStyle = CellStyle.BorderStyle.NONE;
        private CellStyle.BorderStyle borderTopStyle = CellStyle.BorderStyle.NONE;
        private CellStyle.BorderStyle borderRightStyle = CellStyle.BorderStyle.NONE;
        private CellStyle.BorderStyle borderBottomStyle = CellStyle.BorderStyle.NONE;
        private Font font = new Font.DefaultFont();

        @Override
        public HorizontalAlignType getHorizontalAlignType() {
            return this.hAlignType;
        }

        @Override
        public CellStyle setHorizontalAlignType(HorizontalAlignType hAlignType) {
            this.hAlignType = hAlignType;
            return this;
        }

        @Override
        public VerticalAlignType getVerticalAlignType() {
            return this.vAlignType;
        }

        @Override
        public CellStyle setVerticalAlignType(VerticalAlignType vAlignType) {
            this.vAlignType = vAlignType;
            return this;
        }

        @Override
        public BorderStyle getBorderLeftStyle() {
            return this.borderLeftStyle;
        }

        @Override
        public CellStyle setBorderLeftStyle(BorderStyle borderLeftStyle) {
            this.borderLeftStyle = borderLeftStyle;
            return this;
        }

        @Override
        public BorderStyle getBorderTopStyle() {
            return this.borderTopStyle;
        }

        @Override
        public CellStyle setBorderTopStyle(BorderStyle borderTopStyle) {
            this.borderTopStyle = borderTopStyle;
            return this;
        }

        @Override
        public BorderStyle getBorderRightStyle() {
            return this.borderRightStyle;
        }

        @Override
        public CellStyle setBorderRightStyle(BorderStyle borderRightStyle) {
            this.borderRightStyle = borderRightStyle;
            return this;
        }

        @Override
        public BorderStyle getBorderBottomStyle() {
            return this.borderBottomStyle;
        }

        @Override
        public CellStyle setBorderBottomStyle(BorderStyle borderBottomStyle) {
            this.borderBottomStyle = borderBottomStyle;
            return this;
        }

        @Override
        public BorderStyle[] getBorderStyle() {
            return new BorderStyle[]{
                    this.borderLeftStyle,
                    this.borderTopStyle,
                    this.borderRightStyle,
                    this.borderBottomStyle
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
    }
}