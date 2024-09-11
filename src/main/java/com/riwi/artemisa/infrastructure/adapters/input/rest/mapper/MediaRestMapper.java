package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MediaCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MediaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MediaRestMapper {

    MediaModel toMediaModel(MediaCreateRequest mediaCreateRequest);
    MediaResponse toMediaResponse(MediaModel mediaModel);
    List<MediaResponse> toMediaResponseList(List<MediaModel> mediaModel);

}
