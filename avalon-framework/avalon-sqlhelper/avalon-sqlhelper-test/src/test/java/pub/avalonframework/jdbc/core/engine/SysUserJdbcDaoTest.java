package pub.avalonframework.jdbc.core.engine;

import org.springframework.beans.factory.annotation.Autowired;
import pub.avalonframework.sqlhelper.readme.entity.SysUser;

/**
 * @author baichao
 */
public class SysUserJdbcDaoTest {

    @Autowired
    private SysUserJdbcDao sysUserJdbcDao;

    void test() {
        SysUser sysUser = sysUserJdbcDao.queryByPrimaryKey("");
        sysUserJdbcDao.queryOne(jdbcSelectEngine -> jdbcSelectEngine.where((condition, mainTable) -> condition));
    }
}
