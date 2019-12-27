package pub.avalonframework.cloud.gar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import pub.avalon.holygrail.response.utils.ResultUtil;
import pub.avalon.holygrail.response.views.ExceptionView;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.entity.AutResource;
import pub.avalonframework.cloud.gar.model.AutResourceModel;
import pub.avalonframework.cloud.gar.utils.GarUtils;
import pub.avalonframework.cloud.gar.utils.TableUtils;
import pub.avalonframework.core.beans.MessageConstant;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.config.filter.FilterConfiguration;
import pub.avalonframework.security.core.api.service.ResourceAuthenticationService;
import pub.avalonframework.security.core.api.service.WebService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author 白超
 * @date 2018/6/8
 */
public class GarResourceAuthenticationServiceImpl implements ResourceAuthenticationService {

    private final static String SERVICE_NAME = "GAR";

    private final static HttpMessageConverter<Object> HTTP_MESSAGE_CONVERTER = new MappingJackson2HttpMessageConverter();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    public Set<String> getReleaseResources(Map<String, Object> parameters, SecurityConfiguration securityConfiguration) {
        try {
            // 缓存权限表表名信息
            List<String> tables = jdbcTemplate.queryForList("show tables", String.class);
            for (String table : tables) {
                if (table.matches(TableUtils.RESOURCE_TABLE_NAME_REGEX)) {
                    GarUtils.RESOURCE_TABLE_NAMES.add(table);
                } else if (table.matches(TableUtils.ROLE_TABLE_NAME_REGEX)) {
                    GarUtils.ROLE_TABLE_NAMES.add(table);
                } else if (table.matches(TableUtils.ROLE_RESOURCE_TABLE_NAME_REGEX)) {
                    GarUtils.ROLE_RESOURCE_TABLE_NAMES.add(table);
                } else if (table.matches(TableUtils.OBJECT_ROLE_TABLE_NAME_REGEX)) {
                    GarUtils.OBJECT_ROLE_TABLE_NAMES.add(table);
                } else if (table.matches(TableUtils.ROLE_MENU_TABLE_NAME_REGEX)) {
                    GarUtils.ROLE_MENU_TABLE_NAMES.add(table);
                } else if (table.matches(TableUtils.ROLE_ROUTE_VIEW_TEMPLATE_TABLE_NAME_REGEX)) {
                    GarUtils.ROLE_ROUTE_VIEW_TABLE_NAMES.add(table);
                }
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        try {
            List<AutResource> autResources = this.jdbcEngine.queryForList(AutResource.class, MySqlDynamicEngine.query(TableUtils.GAR_RESOURCE_TABLE_NAME, AutResourceModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.parentId().equalTo("1"))));
            if (autResources != null) {
                Set<String> releaseResources = new LinkedHashSet<>(autResources.size());
                autResources.forEach(autResource -> releaseResources.add(autResource.getUrl()));
                return releaseResources;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    @Override
    public void onAccessDenied(WebService webService, HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameters, SecurityConfiguration securityConfiguration) {
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            if (webService.isAjaxRequest(request, response, securityConfiguration)) {
                HTTP_MESSAGE_CONVERTER.write(new ExceptionView(0, ResultUtil.createNoAuthority(MessageConstant.EXCEPTION_NO_AUTHENTICATION_MESSAGE)), MediaType.APPLICATION_JSON, outputMessage);
            } else {
                FilterConfiguration filterConfiguration = securityConfiguration.getFilterConfiguration();
                request.getRequestDispatcher(filterConfiguration.getUnauthorizedUrl()).forward(request, response);
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}