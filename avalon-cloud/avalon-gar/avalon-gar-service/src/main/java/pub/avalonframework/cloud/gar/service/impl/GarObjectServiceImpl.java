package pub.avalonframework.cloud.gar.service.impl;

import org.springframework.stereotype.Service;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.Limit;
import pub.avalon.beans.Pagination;
import pub.avalonframework.cloud.gar.beans.PageListResult;
import pub.avalonframework.cloud.gar.beans.Table;
import pub.avalonframework.cloud.gar.beans.TableColumn;
import pub.avalonframework.cloud.gar.dc.AutObjectGet;
import pub.avalonframework.cloud.gar.entity.AutObject;
import pub.avalonframework.cloud.gar.service.GarObjectService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 白超
 * @date 2019/4/3
 */
@Service
public class GarObjectServiceImpl implements GarObjectService {

    @Override
    public Table getTableOptions() throws Exception {
        List<TableColumn> tableColumns = new ArrayList<>();
        TableColumn id = new TableColumn();
        id.setLabel("id");
        id.setProp("id");
        id.setWidth("200");
        tableColumns.add(id);

        TableColumn username = new TableColumn();
        username.setLabel("名称");
        username.setProp("username");
        username.setWidth("300");
        tableColumns.add(username);

        TableColumn nickname = new TableColumn();
        nickname.setLabel("昵称");
        nickname.setProp("nickname");
        nickname.setWidth("200");
        tableColumns.add(nickname);

        TableColumn age = new TableColumn();
        age.setLabel("年龄");
        age.setProp("age");
        age.setWidth("150");
        tableColumns.add(age);

        Table table = new Table();
        table.setTableColumns(tableColumns);
        return table;
    }

    @Override
    public AutObject getObjectByObjectId(String objectId) throws Exception {
        return null;
    }

    @Override
    public PageListResult<Limit, AutObject> getPageListObject(AutObjectGet record, Integer currentPage, Integer pageSize) throws Exception {
        return new PageListResult<>(new Pagination(DataBaseType.MYSQL, 1, currentPage, pageSize), Collections.singletonList(new AutObject() {
            @Override
            public String getId() {
                return "1";
            }

            @Override
            public boolean disabled() {
                return false;
            }

            public String getUsername() {
                return "张三";
            }

            public String getNickname() {
                return record.getLikeText();
            }

            public Integer getAge() {
                return 18;
            }

        }));
    }

    @Override
    public void putObjectEnabledByObjectId(String objectId) throws Exception {

    }

    @Override
    public void putObjectDisabledByObjectId(String objectId) throws Exception {

    }
}