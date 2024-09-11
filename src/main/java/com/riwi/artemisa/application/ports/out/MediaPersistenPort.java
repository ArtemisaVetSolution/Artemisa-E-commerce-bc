package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.Save;
import com.riwi.artemisa.domain.models.MediaModel;

public interface MediaPersistenPort extends
        Save<MediaModel> {}
