package uz.pdp.appjwttoken.payload;

import lombok.Data;

import java.util.Date;

@Data
public class OutputDto {
    private int fromCardId;

    private int toCardId;

    private long amount;

    private Date date;

    private long commissionAmount;
}
