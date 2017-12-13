package service.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
    private String typeOfInfo;
    private String value;

    public Data(String typeOfInfo, String value) {
        this.typeOfInfo = typeOfInfo;
        this.value = value;
    }
}
