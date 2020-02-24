package pub.avalonframework.cloud.gar.mapper;

import pub.avalonframework.cloud.gar.entity.GarRoute;

public interface GarRouteMapper {
    int deleteByPrimaryKey(String id);

    int insert(GarRoute record);

    int insertSelective(GarRoute record);

    GarRoute selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GarRoute record);

    int updateByPrimaryKeyWithBLOBs(GarRoute record);

    int updateByPrimaryKey(GarRoute record);
}