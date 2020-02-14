package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.store.DataStore;

/**
 * @author baichao
 */
public interface DataInjector<R> {

    DataStore<R> getDataStore();
}