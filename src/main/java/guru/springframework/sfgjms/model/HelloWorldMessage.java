package guru.springframework.sfgjms.model;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Olivier Cappelle
 * @version x.x.x
 * @see
 * @since x.x.x 19/12/2020
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class HelloWorldMessage implements Serializable {
    private static final long serialVersionUID = -2892279614815403841L;

    private UUID id;
    private String message;
}
