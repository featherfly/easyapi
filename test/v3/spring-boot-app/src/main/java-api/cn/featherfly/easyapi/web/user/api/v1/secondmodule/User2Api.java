package cn.featherfly.easyapi.web.user.api.v1.secondmodule;

import cn.featherfly.easyapi.web.user.api.v1.secondmodule.dto.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link User2ApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-02-06T16:12:02.900418+08:00[Asia/Shanghai]")
public interface User2Api {

    /**
     * 更新用户基础资料
     * @param body survey object that needs to be added to the store

     */
    void updateUserBasicData( User  body
    );

}
