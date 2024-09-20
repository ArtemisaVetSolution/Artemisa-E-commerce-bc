package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.OrderModel;
import com.riwi.artemisa.utils.enums.StatesOrder;

import java.time.LocalDate;

public interface OrderPersistencePort extends
        Save<OrderModel>,
        ReadAll<OrderModel>,
        ReadByIdUserAndOrderDate<OrderModel, Long, LocalDate>,
        ReadById<OrderModel, Long>,
        UpdateStatesOrder<Long, StatesOrder>,
        DeleteOrderDetails<Long> {}
