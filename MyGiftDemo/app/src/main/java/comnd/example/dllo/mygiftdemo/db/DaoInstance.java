package comnd.example.dllo.mygiftdemo.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import comnd.example.dllo.mygiftdemo.tools.MyApp;
import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by dllo on 16/7/26.
 *  数据库的单例类
 * 每一个APP只有一个数据库,所以要封装单例类.
 * 继承我封装的接口.接口里的泛型是我的数据类bean
 */
public class DaoInstance implements DBHelper<PresentBean>{
    /**
     * 不可修改的静态常量 --> 数据库的名字
     */
    private static final String DB_NAME = "present.db";

    /**
     * 定义单例对象
     */
    private static DaoInstance sInstance;

    /**
     * 以下数据库辅助类
     */
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;

    /**
     * 两个操作数据库辅助类类
     */
    private DaoSession mDaoSession;
    private PresentBeanDao presentBeanDao;

    /**
     * 查询数据库条件类
     */
    private QueryBuilder queryBuilder;

    /**
     * 对外提供获取单例对象的方法
     */
    public static DaoInstance getsInstance(){
        if (sInstance==null){
            synchronized (DaoInstance.class){
                if (sInstance == null){
                    sInstance = new DaoInstance();
                }
            }
        }
        return sInstance;
    }

    /**
     * 私有化构造方法
     *
     */

    private DaoInstance(){
        // 初始化我的数据类
        presentBeanDao = getPresentBeanDao();
        // 初始化我的游标查询
        queryBuilder = presentBeanDao.queryBuilder();

    }

    /**
     * 内部方法 获得数据库帮助类
     */
    private DaoMaster.DevOpenHelper getHelper(){
        if (helper == null){
            // 三个参数 content, 数据库名, 游标(这里我们让它为空)
            helper = new DaoMaster.DevOpenHelper(MyApp.getContext(),DB_NAME,null);
        }
        return helper;
    }

    /**
     * 内部方法 获得database
     */

    private SQLiteDatabase getDatabase(){
        if (database== null){
            database =getHelper().getWritableDatabase();
        }

        return database;
    }
    /**
     * 内部方法 获得daomaster
     */
    private DaoMaster getDaoMaster(){
        if (daoMaster == null){
            daoMaster = new DaoMaster(getDatabase());
        }
        return daoMaster;
    }

    /**
     * 内部方法 获得daoSession
     */

    private DaoSession getmDaoSession(){
        if (mDaoSession == null){
            mDaoSession = getDaoMaster().newSession();
        }
        return mDaoSession;
    }

    /**
     * 上方几个private 的get方法都是为本方法服务的
     * 通过上方4个方法的嵌套,来获得一个数据库操作对象
     * 获得中国操作对象的过程中,创建了数据库
     * 内部方法 获取数据的方法
     * [articleDao(数据类)]
     */

    private PresentBeanDao _getPresentBeanDao(){
        if (presentBeanDao == null){
            presentBeanDao = getmDaoSession().getPresentBeanDao();
        }

        return presentBeanDao;
    }


    /**
     * 对外提供 ,获取数据的方法
     */

    public PresentBeanDao getPresentBeanDao(){

        return _getPresentBeanDao();

    }



    //*********************对外提供增删改查(接口)**************//

    /**
     * 对外添加单条数据的方法
     * @param presentBean
     */
    @Override
    public void insert(PresentBean presentBean) {
        getsInstance()._insert(presentBean);
    }

    /**
     * 封装的 - 对内添加内容的方法
     * @param presentBean
     */
    private void _insert(PresentBean presentBean){
        presentBeanDao.insertOrReplace(presentBean);
    }

    /**
     * 对外提供的添加集合内容的方法
     * @param presentBeen
     */
    @Override
    public void insert(List<PresentBean> presentBeen) {
       getsInstance()._insert(presentBeen);
    }

    /**
     * 封装的 - 对内添加集合内容的方法
     * @param presentBeen
     */
    private void _insert(List<PresentBean> presentBeen) {
        presentBeanDao.insertOrReplaceInTx(presentBeen);
    }

    /**
     * 对外提供的条件查找的方法
     * @param userName
     * @return
     */
    @Override
    public List<PresentBean> queryBy(String userName) {
        return getsInstance()._queryBy(userName);
    }

    /**
     * 封装的 - 对内查找单条
     * @param userName
     * @return
     */
    private List<PresentBean> _queryBy(String userName) {
        queryBuilder.where(PresentBeanDao.Properties.UserName.eq(userName));
        queryBuilder.limit(20);
        return queryBuilder.list();
    }

    /**
     * 对外提供查找
     * @param title
     * @return
     */
    @Override
    public List<PresentBean> queryBy(CharSequence title) {
        return getsInstance()._queryBy(title);
    }


    /**
     * 封装的查找
     * @param title
     * @return
     */
    private List<PresentBean> _queryBy(CharSequence title) {
        queryBuilder.where(PresentBeanDao.Properties.Title.eq(title));
        queryBuilder.limit(20);
        return queryBuilder.list();
    }

    private void _deleteBy(PresentBean presentBean){
        //  queryBuilder.where(PresentBeanDao.Properties.Title.eq(presentBean.getTitle())).buildDelete();
        // 直接删
        presentBeanDao.delete(presentBean);
    }
    @Override
    public void deleteBy(PresentBean presentBean) {


           DaoInstance.getsInstance()._deleteBy(presentBean);
    }



    public void deleteBy(List<PresentBean> presentBeen ){


    }


}
