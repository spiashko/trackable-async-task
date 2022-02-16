package com.spiashko.trackabletask.person;


import com.fasterxml.jackson.annotation.JsonView;
import com.spiashko.trackabletask.crudbase.View;
import com.spiashko.trackabletask.crudbase.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person extends BaseEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @JsonView({View.Retrieve.class, View.Update.class, View.Create.class})
    @NotEmpty
    @Column(name = "name")
    private String name;

    @JsonView({View.Retrieve.class, View.Update.class, View.Create.class})
    @NotEmpty
    @Column(name = "email")
    private String email;

}
