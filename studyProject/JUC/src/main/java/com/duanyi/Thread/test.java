package com.duanyi.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("d");
        list.add("d");
        list.add("d");
        list.add("d");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("aa");
        list.add("faaa");
        list.stream().forEach(System.out::print);
        System.out.println();

        System.out.println("----对集合数据的过滤，根据给到的条件筛选过滤----");
        list.stream().filter(param -> !param.contains("a")).forEach(System.out::print);
        System.out.println();

        System.out.println("----对集合数据进行排序,默认的是升序排列----");
        list.stream().sorted().forEach(System.out::print);
        System.out.println();

        System.out.println("----自定义排序方式----");//注:这里是通过 Stream<T> sorted(Comparator<? super T> comparator); 这个方法
        list.stream().sorted((p1,p2) -> p1.hashCode() <p2.hashCode() ? 1 : -1).forEach(System.out::print);
        System.out.println();

        System.out.println("----对于集合的每个元素可以按照规则变成相应的元素,这里是把包含a的元素替换成了1,其他的不变----");
        list.stream().map(param -> param.contains("a") ? 1 : param).forEach(System.out ::print);
        System.out.println();

        System.out.println("----对于集合中重复的数据进行去重,把相同的数据去除,这里去除了d的重复元素----");
        list.stream().distinct().forEach(System.out ::print);
        System.out.println();

        System.out.println("----reduce是把集合里的数据组合起来,这里是把所有的元素组合成一个新的字符串----");
        System.out.println(list.stream().reduce((p1,p2) -> p1+p2).get());
        System.out.println();

        System.out.println("----collect是将原先的集合数据根据给定的条件筛选重新生成一个新的list集合返回----");
        list.stream().filter(p -> !p.contains("a")).collect(Collectors.toList()).forEach(System.out::print);
    }


}
