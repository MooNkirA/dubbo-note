package com.moon.dubbo.service;

import com.moon.dubbo.entity.ValidationParameter;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 参数校验测试接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-17 16:47
 * @description
 */
// 缺省可按服务接口区分验证场景，如：@NotNull(groups = ValidationService.class)
public interface ValidationService {

    @GroupSequence(Update.class) // 同时验证Update组规则
    // 与方法同名接口，首字母大写，用于区分验证场景，如：@NotNull(groups = ValidationService.Save.class)，可选
    @interface Save {}
    void save(@NotNull ValidationParameter parameter); // 验证参数不为空

    @interface Update {}
    void update(ValidationParameter parameter);

    void delete(@Min(1) int id); // 直接对基本类型参数验证

}
