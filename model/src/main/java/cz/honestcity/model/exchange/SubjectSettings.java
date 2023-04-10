package cz.honestcity.model.exchange;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SubjectSettings {

    private List<String> names;

}
