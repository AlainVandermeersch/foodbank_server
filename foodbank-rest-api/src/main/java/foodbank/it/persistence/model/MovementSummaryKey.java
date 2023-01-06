package foodbank.it.persistence.model;

import java.io.Serializable;

public class MovementSummaryKey implements Serializable {
    private int month;
    private String bankShortName;
    private int idOrg;
}
