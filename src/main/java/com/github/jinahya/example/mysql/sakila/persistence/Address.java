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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@Table(name = "address")
@XmlRootElement
@XmlType(propOrder = {"address", "address2", "district", "postalCode", "phone"})
public class Address implements Serializable {


    @XmlAttribute
    public int getCityId() {

        return city.getCityId();
    }


    @Column(name = "address_id")
    @Id
    @XmlAttribute
    private int addressId;


    @Column(name = "address")
    @NotNull
    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    private String address;


    @Column(name = "address2")
    @XmlElement(nillable = true, required = true)
    private String address2;


    @Column(name = "district")
    @XmlElement(nillable = true, required = true)
    private String district;


    @JoinColumn(name = "city_id")
    @ManyToOne
    @NotNull
    @XmlTransient
    private City city;


    @Column(name = "postal_code")
    @NotNull
    @XmlElement(required = true)
    private String postalCode;


    @Column(name = "phone")
    @XmlElement(nillable = true, required = true)
    private String phone;


}

