package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.application.ports.CRUD.Save;
import com.riwi.artemisa.domain.models.MediaModel;

public interface MediaServicePort extends
        Save<MediaModel> {
}
