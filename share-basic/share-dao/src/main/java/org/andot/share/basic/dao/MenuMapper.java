package org.andot.share.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.andot.share.basic.entity.AnMenu;
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
}
