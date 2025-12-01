package com.projet.j_and_d.api.response;

import com.projet.j_and_d.model.Item;

public class ItemResponse {
    private Integer id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ItemResponse convert(Item item) {
        ItemResponse response = new ItemResponse();

        response.setId(item.getId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());

        return response;
    }
}
