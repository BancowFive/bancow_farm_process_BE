package com.bancow.process.global.security.auth;

import com.bancow.process.domain.farm.domain.Farm;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
@Data
public class PrincipalDetails implements UserDetails {


    private Farm farm; // userName과 password 정보를 가지고 있는 farm 객체를 만들어 컴포지션

    public PrincipalDetails(Farm farm){
        this.farm = farm;
    }

    @Override
    public String getPassword() {
        return farm.getPassword();
    }

    @Override
    public String getUsername() {
        return farm.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정이 만료되었는지
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정이 잠겨있는지
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 계정 password가 너무 오래되지 않았는지
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정이 활성화되어 있는지
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 해당 이용자의 권한을 리턴하는 부분인데 Role을 나누지 않아서 빈 list로 return
    }

}
