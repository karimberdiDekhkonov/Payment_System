package uz.pdp.appjwttoken.payload;

import lombok.Data;
import uz.pdp.appjwttoken.entity.Card;

import javax.persistence.OneToOne;
import java.util.Date;

@Data
public class InputDto {
    private int fromCardId;

    private int toCardId;

    private long amount;
}
