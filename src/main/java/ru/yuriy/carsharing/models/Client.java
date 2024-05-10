package ru.yuriy.carsharing.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.yuriy.carsharing.enums.ClientRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "clients")
public class Client implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @OneToMany(mappedBy = "owner")
//    private List<Car> cars;

    @Column(name = "name")
    @NotEmpty(message = "Имя не может быть пустым!")
    @Size(min = 3, max = 20, message = "Длина имени от 3 до 20 символов!")
    private String name;

    @Column(name = "age")
    @Min(value = 16, message = "Некорректный возраст!")
    private int age;

    @Column(name = "password")
    @NotEmpty(message = "Пароль обязан быть!")
    @Size(min = 5, max = 100, message = "Пароль слишком короткий, минимум 5 символов!")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "Почта не может быть пустой!")
    @Email(message = "Введена некорректная почта!")
    private String email;

    @Column(name = "drivingexperience")
    @Min(value = 0, message = "Некорректный стаж вождения!")
    private int drivingExperience;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ClientRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getUsername()
    {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}