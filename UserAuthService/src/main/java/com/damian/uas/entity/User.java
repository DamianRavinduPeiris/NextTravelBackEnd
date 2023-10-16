package com.damian.uas.entity;


import com.damian.uas.entity.superentity.SuperEntity;
import com.damian.uas.enums.GENDER;
import com.damian.uas.enums.ROLES;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@RequiredArgsConstructor

public class User implements UserDetails, SuperEntity {
    @NonNull
    private ROLES userRole;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @NonNull
    private String userId;
    @NonNull
    private String name;
    @NonNull
    private String userName;
    @NonNull
    private String userPassword;
    @NonNull
    private String userNIC;
    @NonNull
    private String userNICImageLocation;
    @NonNull
    private int userAge;
    @Enumerated(EnumType.STRING)
    @NonNull
    private GENDER gender;
    @NonNull
    private String userEmail;
    @NonNull
    private String userPhone;
    @NonNull
    private String userAddress;
    @NonNull
    private String remarks;
    @NonNull
    private String userImageLocation;
    @NonNull
    private boolean isAuthenticated;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> packageDetailsIDList;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> paymentsIDList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
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
