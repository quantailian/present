package comnd.example.dllo.mygiftdemo.db;

import java.util.List;

/**
 * Created by dllo on 16/7/26.
 */
public interface DBHelper<T> {

    // 增加一条
    void insert(T t);

    // 增加一个集合
    void insert(List<T> ts);

    // 条件查找 两种都是string 类型.不过CharSequence 是String的一个接口
    List<T> queryBy(String userName);

    List<T> queryBy(CharSequence title);

    // 删除

    void deleteBy(T t);
    void deleteBy(List<T> ts);
    //List<T> queryBy(List<T> ts);
}
