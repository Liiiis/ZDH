package Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class test {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-1, -3, 0, 7, 9, -1);
        Stream<Integer>  stream1 = numbers.stream();
        stream1.forEach(v -> System.out.println(v));

        System.out.println(numbers.parallelStream().filter((x) -> x>0).count());

        numbers.stream().map(Math::abs).forEach(System.out::println);

        numbers.stream().limit(2).forEach(System.out::println);



    }
}
