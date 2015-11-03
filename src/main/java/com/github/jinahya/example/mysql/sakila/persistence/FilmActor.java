/*
 * Copyright 2015 Jin Kwon &lt;onacit at gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.example.mysql.sakila.persistence;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@Table(name = "film_actor")
@XmlRootElement
@XmlType(propOrder = {})
public class FilmActor extends BaseEntity {


    static final String TABLE_NAME = "film_category";


    @Id
    @JoinColumn(name = "actor_id")
    @OneToMany
    @NotNull
    @XmlTransient
    private Actor actor;


    @Id
    @JoinColumn(name = "film_id")
    @OneToMany
    @NotNull
    @XmlTransient
    private Film film;


}

