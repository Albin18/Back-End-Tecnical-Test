package com.test.backendapirest.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
@ToString
public class Phones {
        private Integer number;

        private Integer citycode;

        private Integer contrycode;
}

