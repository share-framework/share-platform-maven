package org.andot.share.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.andot.share.basic.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * role、menu映射关系管理
 * @author andot
 */
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    /**
     * 参考<code>menu_code</code>、<code>role_id</code>唯一索引，如果存在则更新，不存在则新增
     * @param roleMenus
     */
    void replace(@Param("roleMenus") List<RoleMenu> roleMenus);
}
