package cn.featherfly.easyapi.web.user.api.v1.secondmodule;

import cn.featherfly.common.structure.page.Page;
import cn.featherfly.easyapi.web.user.api.v1.secondmodule.dto.User;
import org.springframework.stereotype.Component;

/**
 * The type UserApiImpl.
 *
 * @author zhongj
 */
@Component
public class UserApiImpl implements UserApi{
    @Override
    public User getUserMySelf(Long id, Page page) {
        User u = new User();
        u.setId(id);
        u.setNickname("nickname_" + id);
        return u;
    }
}
