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


import java.util.Arrays;
import static java.util.Optional.ofNullable;
import java.util.Set;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;
import javax.persistence.AttributeConverter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@Table(name = "film")
@XmlRootElement
@XmlType(propOrder = {"title", "releaseYear", "language", "originalLanguage",
                      "rentalDuration", "rentalRate", "length",
                      "replacementCost", "rating", "specialFeatures"})
public class Film extends BaseEntity {


    @XmlEnum
    public static enum Rating {


        G("G"),
        PG("PG"),
        PG_13("PG-13"),
        R("R"),
        NC_17("NC-17");


        private Rating(final String dbData) {

            this.dbData = dbData;
        }


        private String dbData;


    }


    @Converter
    public static class RatingConverter
        implements AttributeConverter<Rating, String> {


        @Override
        public String convertToDatabaseColumn(final Rating entityAttribute) {

            return entityAttribute.dbData;
        }


        @Override
        public Rating convertToEntityAttribute(final String databaseColumn) {

            for (final Rating value : Rating.values()) {
                if (value.dbData.equals(databaseColumn)) {
                    return value;
                }
            }

            throw new IllegalArgumentException("no matches: " + databaseColumn);
        }


    }


    @XmlEnum
    public static enum SpecialFeature {


        TRAILERS("Trailers"),
        COMMENTARIES("Commentaries"),
        DELETED_SCENES("Deleted Scenes"),
        BEHIND_THE_SCENES("Behind the Scenes");


        private SpecialFeature(final String dbData) {
            this.dbData = dbData;
        }


        private final String dbData;


    }


    @Converter
    public static class SpecialFeaturesConverter
        implements AttributeConverter<Set<SpecialFeature>, String> {


        @Override
        public String convertToDatabaseColumn(
            final Set<SpecialFeature> attribute) {

            return ofNullable(attribute)
                .map(c -> c.stream().map(e -> e.dbData).collect(joining(",")))
                .orElse(null);
        }


        @Override
        public Set<SpecialFeature> convertToEntityAttribute(
            final String dbData) {

            return ofNullable(dbData)
                .map(v -> Arrays.stream(v.split(","))
                    .map(SpecialFeature::valueOf)
                    .collect(toSet()))
                .orElse(null);
        }


    }


    @Column(name = "film_id")
    @Id
    @XmlAttribute
    private short filmId;


    @Column(name = "title")
    @NotNull
    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    private String title;


    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description")
    @Lob
    //@XmlElement(nillable = true, required = true)
    @XmlTransient
    private String description;


    @Column(name = "release_year")
    @XmlElement(nillable = true, required = true)
    private int releaseYear;


    @JoinColumn(name = "language_id")
    @ManyToOne
    @NotNull
    @XmlElement(required = true)
    //@XmlTransient
    private Language language;


    @JoinColumn(name = "original_language_id")
    @ManyToOne
    @XmlElement(nillable = true, required = true)
    //@XmlTransient
    private Language originalLanguage;


    @JoinColumn(name = "rental_duration")
    @Min(0)
    @XmlElement(required = true)
    private short rentalDuration;


    @JoinColumn(name = "rental_rate")
    @XmlElement(required = true)
    private float rentalRate;


    @JoinColumn(name = "length")
    @Min(0)
    @XmlElement(required = true)
    private int length;


    @JoinColumn(name = "rental_rate")
    @XmlElement(required = true)
    private float replacementCost;


    @Column(name = "rating")
    @Convert(converter = RatingConverter.class)
    @XmlElement(nillable = true, required = true)
    private Rating rating;


    @Column(name = "special_features")
    @Convert(converter = SpecialFeaturesConverter.class)
    @XmlElement(name = "specialFeature")
    private Set<SpecialFeature> specialFeatures;

//    @JoinTable(
//        inverseJoinColumns = {
//            @JoinColumn(name = "film_id", referencedColumnName = "film_id")},
//        joinColumns = {
//            @JoinColumn(name = "category_id",
//                        referencedColumnName = "category_id")},
//        name = "film_category")
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Category> categories;

}

