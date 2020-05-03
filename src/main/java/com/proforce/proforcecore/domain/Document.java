package com.proforce.proforcecore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Document {

    public static final String TYPE_COC = "Certificate of conformity";
    static final String TYPE_DOC = "Declaration of conformity";
    static final String TYPE_COC_RUS = "Certificate of Conformity CUTR";
    static final String TYPE_DOC_RUS = "Declaration of conformity CUTR";
    static final String TYPE_LOE_RUS = "Letter of Exemption CUTR";
    static final String TYPE_CERT_OTHER = "General Certificate";
    static final String TYPE_TEST_REP = "Test Report";
    static final String TYPE_DS = "Datasheet";
    static final String TYPE_OM = "Operation and Maintenance Manual";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    private String name;

    private String manufacturer;

    private String type;

    @OneToOne(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    private Pdf pdf;

    public Document(String name, String manufacturer, String type, Pdf pdf) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.pdf = pdf;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }
}
