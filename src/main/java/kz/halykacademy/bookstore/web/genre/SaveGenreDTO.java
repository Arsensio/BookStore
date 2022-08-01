package kz.halykacademy.bookstore.web.genre;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveGenreDTO {
    Long id;
    String name;

   public SaveGenreDTO(String name){
        this.name = name;
    }
}
