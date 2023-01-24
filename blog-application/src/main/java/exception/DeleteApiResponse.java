package exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class DeleteApiResponse extends RuntimeException{
    String message;
}
