package kz.halykacademy.bookstore.web.user;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserDTO {
    private Long id;
    private String userName;
    private String password;
    private String role;
    private boolean isBlocked;
}
