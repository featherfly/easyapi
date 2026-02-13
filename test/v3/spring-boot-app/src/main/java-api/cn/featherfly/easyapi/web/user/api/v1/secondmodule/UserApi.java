package cn.featherfly.easyapi.web.user.api.v1.secondmodule;

import cn.featherfly.easyapi.web.user.api.v1.secondmodule.dto.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link UserApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-02-06T16:12:02.900418+08:00[Asia/Shanghai]")
public interface UserApi {

    /**
     * 获取当前用户的用户信息
     * @param id id
     * @param page auto generate page with request parameters
     * @return User 
     */
    User getUserMySelf( Long  id
    , cn.featherfly.common.structure.page.Page  page
    );

}
