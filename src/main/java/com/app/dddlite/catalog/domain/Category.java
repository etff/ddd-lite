package com.app.dddlite.catalog.domain;

import com.app.dddlite.config.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "category")
public class Category extends BaseEntity {

    @EmbeddedId
    private CategoryId id;

    private String name;
}
