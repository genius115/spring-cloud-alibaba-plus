package com.xiaomai.cloud.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 * @author Madison
 * @date 2021/2/19
 * 自定义负载均衡算法
 */
public class CustomRule implements IRule {
    private ILoadBalancer iLoadBalancer;


    public CustomRule() {
    }

    @Override
    public Server choose(Object key) {
        // 获取所有可用的提供者
        List<Server> servers = iLoadBalancer.getReachableServers();

        // 从提供者中随机获取可用的提供者
        return this.getRandomServers(servers);
    }

    //
    private Server getRandomServers(List<Server> servers) {
        // 获取一个[0，servers .size())的随机整数
        int index = new Random().nextInt(servers.size());
        return servers.get(index);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return iLoadBalancer;
    }

}
