package com.ssaffeine.ssaffeine.user.dto;

import com.ssaffeine.ssaffeine.user.domain.Region;
import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.domain.UserRole;
import java.util.ArrayList;
import java.util.Collection;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@ToString
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> user.getRole().toString());

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getLoginId() {

        return user.getLoginId();
    }
    public String getUserId() {
        return user.getUserId() + "";
    }
    public UserRole getUserRole() {
        return user.getRole();
    }
    public String getStudentNumber() {
        return user.getStudentNumber();
    }

    public Region getRegion() {
        return user.getRegion();
    }

    public Integer getGroup() {
        return user.getGroup();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
