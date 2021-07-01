package cz.honestcity.endpoints;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author michal.keder
 */
public abstract class BaseController {

    protected  <DTO> Map<String, ? extends List<? extends DTO>> getDtosByClass(List<? extends DTO> suggestions) {
        return suggestions.stream()
                .collect(Collectors.groupingBy(it -> it.getClass().getSimpleName()));
    }

}
