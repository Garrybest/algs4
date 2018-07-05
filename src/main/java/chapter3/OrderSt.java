package chapter3;

/**
 * 符号表接口
 *
 * @Author: Fang Rui
 * @Date: 2018/7/5
 * @Time: 10:26
 */
public interface OrderSt<Key, Value> {
    void put(Key key, Value value);

    Value get(Key key);

    void delete(Key key);

    void deleteMin();

    void deleteMax();

    default boolean containsKey(Key key) {
        return get(key) != null;
    }

    default boolean isEmpty(Key key) {
        return size() == 0;
    }

    int size();

    Iterable<Key> keys();

    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    Key select(int k);

    int rank(Key key);
}
