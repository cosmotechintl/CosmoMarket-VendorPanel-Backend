package com.cosmo.authentication.user.entity;

import com.cosmo.authentication.accessGroup.entity.AccessGroup;
import com.cosmo.authentication.accessGroup.entity.AccessGroupRoleMap;
import com.cosmo.authentication.vendor.entity.Vendor;
import com.cosmo.common.abstractEntity.AbstractEntity;
import com.cosmo.common.entity.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "vendor_user")
public class VendorUser extends AbstractEntity implements UserDetails {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "address")
    private String address;

    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;

    @JoinColumn(name = "access_group", nullable = false, referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AccessGroup accessGroup;

    @Column(name = "password_changed_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordChangeDate;

    @Column(name = "last_logged_in_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoggedInTime;

    @Column(name = "wrong_password_attempt_count")
    private Integer wrongPasswordAttemptCount;

    @Column(name = "profile_picture_name")
    private String profilePictureName;

    @Column(name = "otp_auth_secret")
    private String otpAuthSecret;

    @Column(name = "two_factor_enabled", nullable = false)
    private boolean twoFactorEnabled;

    @Column(name = "wrong_oto_auth_attempt_count")
    private Integer wrongOtpAuthAttemptCount;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @ManyToOne
    @JoinColumn(name = "vendor")
    private Vendor vendor;

    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Hibernate.initialize(this.accessGroup.getAccessGroupRoleMaps());
        return this.accessGroup
                .getAccessGroupRoleMaps()
                .stream()
                .map(AccessGroupRoleMap::getRoles)
                .map(roles -> new SimpleGrantedAuthority(roles.getPermission()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
