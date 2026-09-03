package cn.featherfly.jbox.rbac.domain;


/**
 * The role entity.
 *
 * @author zhongj
 */
public class Role {

    public static enum BindType {
        /**
         * 手动绑定
         */
        NONE,
        /**
         * 自动绑定用户和权限
         */
        AUTO_BIND_ALL,
        /**
         * 自动绑定用户
         */
        AUTO_BIND_USER,
        /**
         * 自动绑定权限
         */
        AUTO_BIND_PRIVILEGE
    }

    private Long   id;
    private String name;
    private BindType bindType;

    /**
     * Instantiates a new Role.
     */
    public Role() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BindType getBindType() {
        return bindType;
    }

    public void setBindType(BindType bindType) {
        this.bindType = bindType;
    }
}