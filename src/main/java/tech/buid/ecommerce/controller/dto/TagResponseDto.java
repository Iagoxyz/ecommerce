package tech.buid.ecommerce.controller.dto;

import tech.buid.ecommerce.entities.TagEntity;

public record TagResponseDto(Long tagId,
                             String name) {
    public static TagResponseDto fromEntity(TagEntity entity) {
        return new TagResponseDto(entity.getTagId(), entity.getName());
    }
}
