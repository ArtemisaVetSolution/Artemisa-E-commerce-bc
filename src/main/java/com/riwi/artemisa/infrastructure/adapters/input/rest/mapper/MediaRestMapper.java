package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MediaCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MediaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MediaRestMapper {

    //Request

//    MediaModel toMediaModel(MediaCreateRequest mediaCreateRequest);
    MediaCreateRequest toMediaCreateRequest(MediaModel mediaModel);
//    List<MediaCreateRequest> toMediaCreateRequestList(List<MediaModel> mediaModel);

    //Response

//    MediaModel toMediaModel(MediaResponse mediaResponse);

    MediaResponse toMediaResponse(MediaModel mediaModel);

//    List<MediaResponse> toMediaResponseList(List<MediaModel> mediaModel);


}
