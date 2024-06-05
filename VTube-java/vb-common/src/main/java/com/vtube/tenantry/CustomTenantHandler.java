package com.vtube.tenantry;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomTenantHandler implements TenantLineHandler {

    /**
     * 租户字段名
     */
    private static final String SYSTEM_TENANT_ID = "tenant_id";

    /**
     * 默认的租户ID
     */
    public static final Long DEFAULT_TENANT_ID = 1L;

    /**
     * 需要过滤的表
     */
    private static final List<String> IGNORE_TENANT_TABLES = new ArrayList<>();

    /**
     * 获取租户ID值
     * @return
     */
    @Override
    public Expression getTenantId() {
        //获取登录用户的租户ID
        Long loginUserTenantId = StpUtil.getLoginIdAsLong();;
        if (loginUserTenantId == null){
            loginUserTenantId = DEFAULT_TENANT_ID;
        }
        return new LongValue(loginUserTenantId);
    }

    /**
     * 租户字段名，默认是tenant_id，如果想改成其他字段，在这里返回即可
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return SYSTEM_TENANT_ID;
    }

    /**
     * 有不需要进行租户隔离的表，在这里返回true
     * @param tableName
     * @return
     */
    @Override
    public boolean ignoreTable(String tableName) {
//        return IGNORE_TENANT_TABLES.contains(tableName);
        return  true;
    }

}