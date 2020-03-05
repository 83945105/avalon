package pub.avalonframework.sqlhelper.core.data.block.builder;

import pub.avalonframework.sqlhelper.core.data.block.DataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.helper.TableAliasNullException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractDataBlockBuilder<T extends Helper, S extends DataBlock> implements DataBlockBuilder<T, S> {

    protected String tableAlias;

    private T helper;

    public AbstractDataBlockBuilder(String tableAlias, T helper) {
        if (tableAlias == null) {
            throw new TableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.helper = helper;
    }

    private List<S> dataBlocks = null;

    @Override
    public String getTableAlias() {
        return this.tableAlias;
    }

    @Override
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public T getHelper() {
        return this.helper;
    }

    @Override
    public void setHelper(T helper) {
        this.helper = helper;
    }

    @Override
    public void addDataBlock(S dataBlock) {
        if (dataBlock == null) {
            return;
        }
        if (this.dataBlocks == null) {
            this.dataBlocks = new ArrayList<>();
        }
        this.dataBlocks.add(dataBlock);
    }

    @Override
    public List<S> takeoutDataBlocks() {
        List<S> dataBlocks = this.dataBlocks;
        this.dataBlocks = null;
        return dataBlocks;
    }
}