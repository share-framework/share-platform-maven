package org.andot.share.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.andot.share.basic.entity.AnMenu;
import org.andot.share.core.dto.MenuPermissionDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<AnMenu> {
    /**
     *
     * @return
     */
    List<AnMenu> getMenuListByUserId(@Param("appSystemId") Long appSystemId,
                                     @Param("xNumber") Long xNumber);

    /**
     *
     * @param appSystemId
     * @param roleCode
     * @return
     */
    List<MenuPermissionDTO> getMenuListByRoleCode(@Param("appSystemId") Long appSystemId,
                                     @Param("roleCode") String roleCode);

    /**
     *
     * @param appSystemId
     * @param roleCodes
     * @return
     */
    List<MenuPermissionDTO> getMenuListByRoleCodes(@Param("appSystemId") Long appSystemId,
                                     @Param("roleCodes") List<String> roleCodes);
}
