/*
 * Copyright (c) Bia
 */

package models;

public class HypeTier {
    private static Integer idHypeTier = 0;
    final private Integer id;
    private String name;
    private String type;

    public HypeTier(String name, String type) {
        idHypeTier++;
        this.id = idHypeTier;
        this.name = name;
        this.type = type;
    }

    public static Integer getIdHypeTier() {
        return idHypeTier;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HypeTier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
