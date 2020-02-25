package pub.avalonframework.cloud.gar.mapper;

import pub.avalonframework.cloud.gar.entity.GarMenu;

public interface GarMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(GarMenu record);

    int insertSelective(GarMenu record);

    GarMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GarMenu record);

    int updateByPrimaryKeyWithBLOBs(GarMenu record);

    int updateByPrimaryKey(GarMenu record);
}