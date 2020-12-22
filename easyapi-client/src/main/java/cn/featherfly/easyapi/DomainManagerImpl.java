package cn.featherfly.easyapi;

public class DomainManagerImpl implements DomainManager{

    public String domain;

    public DomainManagerImpl(String domain) {
        this.domain = domain;
    }

    @Override
    public String getDomain(String group) {
        return domain;
    }
}
