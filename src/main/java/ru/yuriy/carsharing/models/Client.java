package ru.yuriy.carsharing.models;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client
{
    @Id
    private int id;
    @NotEmpty(message = "Имя не может быть пустым!")
    @Size(min = 3, max = 20, message = "Длина имени от 3 до 20 символов!")
    private String name;

    @Min(value = 16, message = "Некорректный возраст!")
    private int age;

    @NotEmpty(message = "Пароль обязан быть!")
    @Size(min = 5, max = 100, message = "Пароль слишком короткий, минимум 5 символов!")
    private String password;

    @NotEmpty(message = "Почта не может быть пустой!")
    @Email(message = "Введена некорректная почта!")
    private String email;

    @Min(value = 0, message = "Некорректный стаж вождения!")
    private int drivingExperience;

    private String role;
}