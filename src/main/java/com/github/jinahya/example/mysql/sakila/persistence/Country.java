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


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@Table(name = "country")
@XmlRootElement
@XmlType(propOrder = {"name"})
public class Country implements Serializable {


    public int getCountryId() {

        return countryId;
    }


    @Column(name = "country_id")
    @Id
    @XmlAttribute
    private int countryId;


    @Column(name = "name")
    @NotNull
    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    private String name;


}

