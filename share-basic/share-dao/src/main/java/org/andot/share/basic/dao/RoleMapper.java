package org.andot.share.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.andot.share.basic.entity.BaseUser;
import org.andot.share.basic.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends BaseMapper<Role> {
}
