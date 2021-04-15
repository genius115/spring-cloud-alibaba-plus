package com.xiaomai.cloud.demo.services;

import cn.hutool.core.lang.Console;
import com.xiaomai.cloud.demo.po.Demo;
import com.xiaomai.cloud.demo.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author wangfeng
 * @date 2020/12/1
 */
@Service
@Slf4j
public class DemoService {
    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private  DemoRepositoryByName demoRepositoryByName;

    @Autowired
    private  DemoRepositoryCrudRepository demoRepositoryCrudRepository;

    @Autowired
    private  DemoRepositoryPagingAndSorting demoRepositoryPagingAndSorting;

    @Autowired
    private  DemoRepositoryQueryAnnotation demoRepositoryQueryAnnotation;

    @Autowired
    private  DemoRepositorySpecification demoRepositorySpecification;

    public String base(){
        Demo demo = new Demo();
        demo.setName("base:" + new Random().nextInt(1000));
        demo.setAge(new Random().nextInt(100));
        demo.setAddress("address-" + Math.random());
        this.demoRepository.save(demo);

        //Order	定义了排序规则
        Sort.Order order=new Sort.Order(Sort.Direction.DESC,"name");
        //Sort对象封装了排序规则
        Sort sort=Sort.by(order);
        List<Demo> list= this.demoRepository.findAll(sort);
        for (Demo users:list){
            System.out.println(users);
        }

        // demoRepositoryByName
        System.out.println("*******demoRepositoryByName***********");
        List<Demo> list0 = demoRepositoryByName.findByName("lisi");
        Console.log(list0);
        List<Demo> list1=this.demoRepositoryByName.findByName("张三");
        for (Demo users:list1){
            System.out.println(users);
        }
        List<Demo> list2=this.demoRepositoryByName.findByNameAndAge("张三",20);
        for (Demo users:list2){
            System.out.println(users);
        }
        List<Demo> list3 = this.demoRepositoryByName.findByNameLike("张%");
        for (Demo users : list3) {
            System.out.println(users);
        }

        // demoRepositoryQueryAnnotation
        System.out.println("*******demoRepositoryQueryAnnotation***********");
        List<Demo> list4 = this.demoRepositoryQueryAnnotation.queryByNameDemoHQL("张三");
        for (Demo users : list4) {
            System.out.println(users);
        }

        this.demoRepositoryQueryAnnotation.updateDemoNameById("1111111",Integer.valueOf(10));

        Demo demoOne = this.demoRepositoryQueryAnnotation.findOne(Integer.valueOf(10));
        System.out.println(demoOne);

        return "OK";
    }

    public String crud(){
        Demo demo = new Demo();
        demo.setName("crud:" +new Random().nextInt(1000));
        demo.setAge(new Random().nextInt(100));
        demo.setAddress("address-" + Math.random());
        this.demoRepositoryCrudRepository.save(demo);




        Optional<Demo> optdemo = this.demoRepositoryCrudRepository.findById(10);
        Demo demo1 = optdemo.get();
        System.out.println(demo1);

        List<Demo> list = (List<Demo>) this.demoRepositoryCrudRepository.findAll();
        for (Demo user:list){
            System.out.println(user);
        }
        return "OK";
    }

    public String crudPage(){

        //Order	定义了排序规则
        Sort.Order order=new Sort.Order(Sort.Direction.DESC,"id");
        //Sort对象封装了排序规则
        Sort sort = Sort.by(order);
        List<Demo> list= (List<Demo>) this.demoRepositoryPagingAndSorting.findAll(sort);
        for (Demo users:list){
            System.out.println(users);
        }


        //Pageable:封装了分页的参数，当前页，煤业显示的条数。注意：它的当前页是从0开始
        //PageRequest(page,size):page表示当前页，size表示每页显示多少条
        Pageable pageable= PageRequest.of(0,2,sort);
        Page<Demo> page=this.demoRepositoryPagingAndSorting.findAll(pageable);
        System.out.println("数据的总条数："+page.getTotalElements());
        System.out.println("总页数："+page.getTotalPages());
        List<Demo> list2=page.getContent();
        for (Demo users:list2){
            System.out.println(users);
        }

        return "OK";
    }

    public String crudSpecification(){
        log.info("*****crudSpecification*****");
        /**
         * Specification:用于封装查查询条件
         */
        Specification<Demo> spec=new Specification<Demo>() {
            //Predicate：封装了单个查询条件
            /**
             * @param root		对查询对象属性的封装
             * @param criteriaQuery	封装了我们要执行的查询中的各个部分的信息，select from order
             * @param criteriaBuilder	查询条件的构造器
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Demo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //where name="张三"
                /**
                 * 参数一：查询的属性
                 * 参数二：条件的值
                 */
                Predicate predicate=criteriaBuilder.equal(root.get("name"),"张三");
                return predicate;
            }
        };

        List<Demo> list=this.demoRepositorySpecification.findAll(spec);
        for (Demo users:list){
            System.out.println(users);
        }
        return "OK";
    }

    public String crudSpecification1(){
        log.info("*****crudSpecification1*****");
        /**
         * Specification:用于封装查查询条件
         */
        Specification<Demo> spec=new Specification<Demo>() {
            //Predicate：封装了单个查询条件

            /**
             * @param root		对查询对象属性的封装
             * @param criteriaQuery	封装了我们要执行的查询中的各个部分的信息，select from order
             * @param criteriaBuilder	查询条件的构造器
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Demo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //where name="张三" and age=20
                /**
                 * 参数一：查询的属性
                 * 参数二：条件的值
                 */
                List<Predicate> list=new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("name"),"张三"));
                list.add(criteriaBuilder.equal(root.get("age"),20));
                Predicate[] arr=new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };
        List<Demo> list=this.demoRepositorySpecification.findAll(spec);
        for (Demo users:list){
            System.out.println(users);
        }
        return "OK";
    }

    public String crudSpecification2(){
        log.info("*****crudSpecification2*****");
        /**
         * Specification:用于封装查查询条件
         */
        Specification<Demo> spec=new Specification<Demo>() {
            //Predicate：封装了单个查询条件

            /**
             * @param root		对查询对象属性的封装
             * @param criteriaQuery	封装了我们要执行的查询中的各个部分的信息，select from order
             * @param criteriaBuilder	查询条件的构造器
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Demo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //where name="张三" and age=20
                /**
                 * 参数一：查询的属性
                 * 参数二：条件的值
                 */
				/*List<Predicate> list=new ArrayList<>();
				list.add(criteriaBuilder.equal(root.get("name"),"张三"));
				list.add(criteriaBuilder.equal(root.get("age"),20));
				Predicate[] arr=new Predicate[list.size()];*/
                //(name='张三' and age=20) or id=2
                return criteriaBuilder.or(criteriaBuilder.and(criteriaBuilder.equal(root.get("name"),"张三"),criteriaBuilder.equal(root.get("age"),20)),criteriaBuilder.equal(root.get("id"),1));
            }
        };
        Sort sort=Sort.by(new Sort.Order(Sort.Direction.DESC,"id"));
        List<Demo> list=this.demoRepositorySpecification.findAll(spec,sort);
        for (Demo users:list){
            System.out.println(users);
        }
        return "OK";
    }

}
