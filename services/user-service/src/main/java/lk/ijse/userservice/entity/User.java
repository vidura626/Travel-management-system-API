package lk.ijse.userservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lk.ijse.userservice.entity.embedded.NICorPassport;
import lk.ijse.userservice.util.constants.Gender;
import lk.ijse.userservice.util.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class User implements UserDetails {
    @Id
    @Min(value = 1, message = "UserId cannot be less than 1")
    @Max(value = 9999999999L, message = "UserId cannot be greater than 9999999999")
    private long userId;
    @NotBlank(message = "Name cannot be blank")
    @Pattern(message = "Name should contain only letters",
            regexp = "^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$")
    private String name;
    private Gender gender;
    @NotNull(message = "Date of Birth cannot be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @NotNull(message = "Profile Picture cannot be null or blank")
    private String proPic;
    @NotNull(message = "Remarks cannot be null")
    private String remarks;
    private NICorPassport nicOrPassport;
    private Date regDate;
    private Date modifiedDate;
    private String username;
    @NotNull
    private String password;
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Address cannot be null or blank")
    private String address;
    @NotBlank(message = "Contact cannot be blank")
    @Pattern(message = "Contact should contain only numbers",
            regexp = "^(?:7|0|(?:\\+94)|(?:94))[0-9]{9,10}$")
    private String contact;
    @Pattern(message = "Role should be role_user or role_admin",
            regexp = "^(role_user|role_admin)$",
            flags = {Pattern.Flag.CASE_INSENSITIVE})
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
