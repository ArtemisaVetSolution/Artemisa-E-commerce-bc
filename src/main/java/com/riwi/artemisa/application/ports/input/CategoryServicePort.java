package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.CategoryModel;

import java.util.List;

public interface CategoryServicePort extends
            Save<CategoryModel>
        , Update<CategoryModel,String>
        , UpdateStatusProduct<String>
        , ReadAll<CategoryModel>
        , ReadByName<CategoryModel,String>
         {
         }
