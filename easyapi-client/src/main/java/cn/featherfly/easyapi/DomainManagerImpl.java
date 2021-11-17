package cn.featherfly.easyapi;

import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.lang.Lang;

/**
 * The Class DomainManagerImpl.
 *
 * @author zhongj
 */
public class DomainManagerImpl implements DomainManager {

    private String domain;

    private Map<String, String> groupDomains = new HashMap<>(0);

    /**
     * Instantiates a new domain manager impl.
     *
     * @param domain the domain
     */
    public DomainManagerImpl(String domain) {
        this.domain = domain;
    }

    /**
     * Adds the group.
     *
     * @param group  the group
     * @param domain the domain
     * @return the domain manager impl
     */
    public DomainManagerImpl addGroup(String group, String domain) {
        groupDomains.put(group, domain);
        return this;
    }

    /**
     * Contains group.
     *
     * @param group the group
     * @return true, if successful
     */
    public boolean containsGroup(String group) {
        return groupDomains.containsKey(group);
    }

    /**
     * Gets the domain by group.
     *
     * @param group the group
     * @return the domain by group
     */
    public String getDomainByGroup(String group) {
        return groupDomains.get(group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDomain(String group) {
        String d = getDomainByGroup(group);
        if (Lang.isNotEmpty(d)) {
            return d;
        } else {
            return domain;
        }
    }
}
