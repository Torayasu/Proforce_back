package com.proforce.proforcecore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Part {

    public static final String TYPE_PROCESS = "Process";
    public static final String TYPE_ELECTRICAL = "Electrical";
    public static final String TYPE_COMPLEX = "Assembly of Parts";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    private String model;

    private String manufacturer;

    private String type;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Document> docs;

    public static class PartBuilder {

        private String model;
        private String manufacturer;
        private String type;
        private List<Document> docs = new ArrayList<>();

        public PartBuilder model(String model) {
            this.model = model;
            return this;
        }

        public PartBuilder manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public PartBuilder type(String type) {
            this.type = type;
            return this;
        }

        public PartBuilder doc(Document doc) {
            docs.add(doc);
            return this;
        }

        public Part build() {
            return new Part(model, manufacturer, type, docs);
        }

    }

    private Part(String model, String manufacturer, String type, List<Document> docs) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.docs = docs;
    }

    public void addDoc(Document document) {
        this.docs.add(document);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }
}
