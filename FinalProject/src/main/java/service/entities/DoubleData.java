package service.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleData {
    private String typeOfInfo;
    private String value1;
    private String value2;

    public DoubleData(String typeOfInfo, String value1, String value2) {
        this.typeOfInfo = typeOfInfo;
        this.value1 = value1;
        this.value2 = value2;
    }
}
