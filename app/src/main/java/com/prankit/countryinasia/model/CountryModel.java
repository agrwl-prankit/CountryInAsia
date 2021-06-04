package com.prankit.countryinasia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CountryModel implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subregion;
    @SerializedName("population")
    @Expose
    private Long population;
    @SerializedName("borders")
    @Expose
    private Object borders;
    @SerializedName("languages")
    @Expose
    private ArrayList<Datum> languages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Object getBorders() {
        return borders;
    }

    public void setBorders(Object borders) {
        this.borders = borders;
    }

    public ArrayList<Datum> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Datum> languages) {
        this.languages = languages;
    }

    public class Datum{
        @SerializedName("name")
        @Expose
        private String language_name;
        @SerializedName("iso639_1")
        @Expose
        private String iso1;
        @SerializedName("iso639_2")
        @Expose
        private String iso2;
        @SerializedName("nativeName")
        @Expose
        private String native_name;

        public String getLanguage_name() {
            return language_name;
        }

        public void setLanguage_name(String language_name) {
            this.language_name = language_name;
        }

        public String getIso1() {
            return iso1;
        }

        public void setIso1(String iso1) {
            this.iso1 = iso1;
        }

        public String getIso2() {
            return iso2;
        }

        public void setIso2(String iso2) {
            this.iso2 = iso2;
        }

        public String getNative_name() {
            return native_name;
        }

        public void setNative_name(String native_name) {
            this.native_name = native_name;
        }
    }

}
