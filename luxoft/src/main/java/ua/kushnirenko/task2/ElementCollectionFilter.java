package ua.kushnirenko.task2;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;


public class ElementCollectionFilter {

    /**
     * @param collection - collection of Elements
     * @return collection with Elements with age > 20 and unique 'nums'.
     */
    public static Collection<Element> filter(Collection<Element> collection) {
        HashSet<Integer> pool = new HashSet<>();

        return collection.stream().filter(element -> {
            return element.getAge() > 20 && pool.add(element.getNum());
        }).collect(Collectors.toList());
    }
}