package com.sudip.fusecanteen.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@Document("role")
public class Role {
    @Id
    private String  id;
    private String name;
}
