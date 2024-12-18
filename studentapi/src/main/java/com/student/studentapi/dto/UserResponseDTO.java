package com.student.studentapi.dto;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String adgroup;

    public UserResponseDTO(Long id, String username, String adgroup) {
        this.id=id;
        this.username=username;
        this.adgroup=adgroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdgroup() {
        return adgroup;
    }

    public void setAdgroup(String adgroup) {
        this.adgroup = adgroup;
    }
}
