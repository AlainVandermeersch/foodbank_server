package foodbank.it.persistence.model;

import java.io.Serializable;
import java.time.LocalDate;

public class MovementDailyKey implements Serializable {
    private LocalDate day;
    private String bankShortName;
    private int idOrg;
}

