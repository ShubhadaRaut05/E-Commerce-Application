package com.shubhada.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class setUserRolesRequestDto {
    private List<Long> roleIds;
}
