package com.netcracker.model;

import com.arangodb.springframework.annotation.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor
@Document("devices")
@Data
public class Devices {

    @Id
    @JsonIgnore
    String id;
    @NonNull
    Nic[] nics;
    @NonNull
    Switch[] switches;
    @NonNull
    Router[] routers;
}
