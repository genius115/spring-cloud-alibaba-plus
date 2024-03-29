package com.xiaomai.cloud.jdk8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
 *
 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 *
 * Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
 *
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 *
 * 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
 *
 * +--------------------+       +------+   +------+   +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+       +------+   +------+   +---+   +-------+
 *
 * 以上的流程转换为 Java 代码为：
 * List<Integer> transactionsIds =
 * widgets.stream()
 *              .filter(b -> b.getColor() == RED)
 *              .sorted((x,y) -> x.getWeight() - y.getWeight())
 *              .mapToInt(Widget::getWeight)
 *              .sum();
 *
 * 1、什么是 Stream？
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作
 *     元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
 *     数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 *     聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 *
 * 和以前的Collection操作不同， Stream操作还有两个基础的特征：
 *     Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
 *     内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。
 *
 * 2、生成流
 * 在 Java 8 中, 集合接口有两个方法来生成流：
 *     stream() − 为集合创建串行流。
 *     parallelStream() − 为集合创建并行流。
 *
 *
 *  forEach  map  filter  limit  sorted  Collectors  IntSummaryStatistics
 *
 *
 *
 * @author Madison
 * @date 2021/1/17
 */
public class Java8Test3Stream { public static void main(String args[]){
    System.out.println("使用 Java 7: ");

    // 计算空字符串
    List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
    System.out.println("列表: " +strings);
    long count = getCountEmptyStringUsingJava7(strings);

    System.out.println("空字符数量为: " + count);
    count = getCountLength3UsingJava7(strings);

    System.out.println("字符串长度为 3 的数量为: " + count);

    // 删除空字符串
    List<String> filtered = deleteEmptyStringsUsingJava7(strings);
    System.out.println("筛选后的列表: " + filtered);

    // 删除空字符串，并使用逗号把它们合并起来
    String mergedString = getMergedStringUsingJava7(strings,", ");
    System.out.println("合并字符串: " + mergedString);
    List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

    // 获取列表元素平方数
    List<Integer> squaresList = getSquares(numbers);
    System.out.println("平方数列表: " + squaresList);
    List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);

    System.out.println("列表: " +integers);
    System.out.println("列表中最大的数 : " + getMax(integers));
    System.out.println("列表中最小的数 : " + getMin(integers));
    System.out.println("所有数之和 : " + getSum(integers));
    System.out.println("平均数 : " + getAverage(integers));
    System.out.println("随机数: ");

    // 输出10个随机数
    Random random = new Random();

    for(int i=0; i < 10; i++){
        System.out.println(random.nextInt());
    }

    System.out.println("使用 Java 8: ");
    System.out.println("列表: " +strings);

    count = strings.stream().filter(string->string.isEmpty()).count();
    System.out.println("空字符串数量为: " + count);

    count = strings.stream().filter(string -> string.length() == 3).count();
    System.out.println("字符串长度为 3 的数量为: " + count);

    filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
    System.out.println("筛选后的列表: " + filtered);

    mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
    System.out.println("合并字符串: " + mergedString);

    squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
    System.out.println("Squares List: " + squaresList);
    System.out.println("列表: " +integers);

    IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();

    System.out.println("列表中最大的数 : " + stats.getMax());
    System.out.println("列表中最小的数 : " + stats.getMin());
    System.out.println("所有数之和 : " + stats.getSum());
    System.out.println("平均数 : " + stats.getAverage());
    System.out.println("随机数: ");

    random.ints().limit(10).sorted().forEach(System.out::println);

    // 并行处理
    count = strings.parallelStream().filter(string -> string.isEmpty()).count();
    System.out.println("空字符串的数量为: " + count);
}

    private static int getCountEmptyStringUsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.isEmpty()){
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.length() == 3){
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
        List<String> filteredList = new ArrayList<String>();

        for(String string: strings){

            if(!string.isEmpty()){
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator){
        StringBuilder stringBuilder = new StringBuilder();

        for(String string: strings){

            if(!string.isEmpty()){
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length()-2);
    }

    private static List<Integer> getSquares(List<Integer> numbers){
        List<Integer> squaresList = new ArrayList<Integer>();

        for(Integer number: numbers){
            Integer square = new Integer(number.intValue() * number.intValue());

            if(!squaresList.contains(square)){
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers){
        int max = numbers.get(0);

        for(int i=1;i < numbers.size();i++){

            Integer number = numbers.get(i);

            if(number.intValue() > max){
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers){
        int min = numbers.get(0);

        for(int i=1;i < numbers.size();i++){
            Integer number = numbers.get(i);

            if(number.intValue() < min){
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers){
        int sum = (int)(numbers.get(0));

        for(int i=1;i < numbers.size();i++){
            sum += (int)numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers){
        return getSum(numbers) / numbers.size();
    }
}
