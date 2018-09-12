package cz.exchange.model.subject;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Activity {
    private LocalDateTime watchedSince;
}
