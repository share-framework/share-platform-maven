package org.andot.share.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Andot
 */
@AllArgsConstructor
@Getter
public class XUserDetail implements UserDetails {

    private UserDTO user;
    private List<RoleDTO> roleList;
    private List<MenuPermissionDTO> menuDTOList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleList.stream().map(item -> new SimpleGrantedAuthority(item.getRoleCode())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getXNumber().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getDisabled() != 0;
    }
}
