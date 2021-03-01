package com.xiaomai.cloud.config.rocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Madison
 * @date 2021/2/25
 */
@Service
public class ParamConfigService {

    @Value("${plat.plat-group}")
    public String platGroup ;
    @Value("${plat.plat-topic}")
    public String platTopic ;
    @Value("${plat.plat-tag}")
    public String accountTag ;

    public String getPlatGroup() {
        return platGroup;
    }

    public void setPlatGroup(String platGroup) {
        this.platGroup = platGroup;
    }

    public String getPlatTopic() {
        return platTopic;
    }

    public void setPlatTopic(String platTopic) {
        this.platTopic = platTopic;
    }

    public String getAccountTag() {
        return accountTag;
    }

    public void setAccountTag(String accountTag) {
        this.accountTag = accountTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParamConfigService that = (ParamConfigService) o;

        if (platGroup != null ? !platGroup.equals(that.platGroup) : that.platGroup != null) return false;
        if (platTopic != null ? !platTopic.equals(that.platTopic) : that.platTopic != null) return false;
        return accountTag != null ? accountTag.equals(that.accountTag) : that.accountTag == null;
    }

    @Override
    public int hashCode() {
        int result = platGroup != null ? platGroup.hashCode() : 0;
        result = 31 * result + (platTopic != null ? platTopic.hashCode() : 0);
        result = 31 * result + (accountTag != null ? accountTag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ParamConfigService{" +
                "platGroup='" + platGroup + '\'' +
                ", platTopic='" + platTopic + '\'' +
                ", accountTag='" + accountTag + '\'' +
                '}';
    }
}