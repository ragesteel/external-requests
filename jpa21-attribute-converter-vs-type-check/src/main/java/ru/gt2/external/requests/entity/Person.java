package ru.gt2.external.requests.entity;

import lombok.Getter;
import lombok.Setter;
import ru.gt2.external.requests.type.CustomConverted;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private CustomConverted customConverted;
}
