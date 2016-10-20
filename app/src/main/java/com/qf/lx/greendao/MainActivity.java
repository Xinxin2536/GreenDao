package com.qf.lx.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.qf.lxsdsdsa.DaoMaster;
import com.qf.lxsdsdsa.UserEntity;
import com.qf.lxsdsdsa.UserEntityDao;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    DaoMaster mDaoMaster;//数据库的管理类
    DaoMaster.DevOpenHelper mOpenHelper;
    UserEntityDao mUserEntityDao;
    SimpleCursorAdapter mAdapter;
    Cursor mCursor;
    long curItemId = -1;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv);
        initDao();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("xin", id + "");
                curItemId = id;
                flag=true;
            }
        });
    }

    private void initDao() {
        mOpenHelper = new DaoMaster.DevOpenHelper(this, "user.db", null);
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        //获取对象数据库的操作类
        mUserEntityDao = mDaoMaster.newSession().getUserEntityDao();
        mCursor = db.query(mUserEntityDao.getTablename(), mUserEntityDao.getAllColumns(), null, null, null, null, null);
        mAdapter = new SimpleCursorAdapter(this,
                R.layout.item_listview,
                mCursor,
                new String[]{UserEntityDao.Properties.Name.columnName,
                        UserEntityDao.Properties.Age.columnName,
                        UserEntityDao.Properties.Tel.columnName},
                new int[]{R.id.name, R.id.age, R.id.tel},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//检测数据库变化
        mListView.setAdapter(mAdapter);

    }

    public void add(View v) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("鑫哥哥" + System.currentTimeMillis());
        userEntity.setAge((int) (Math.random() * 10 + 20));
        userEntity.setTel("135" + System.currentTimeMillis());
        mUserEntityDao.insert(userEntity);
        mCursor.requery();//对数据库进行重查


    }

    public void update(View v) {
        if (curItemId!=-1&&flag) {
            UserEntity userEntity = mUserEntityDao.load(curItemId);
            if (userEntity == null) {
                return;
            }
            userEntity.setName("嘻嘻" + System.currentTimeMillis());
            userEntity.setAge((int) (Math.random() * 10 + 30));
            userEntity.setTel("15454" + System.currentTimeMillis());
            mUserEntityDao.update(userEntity);
            mCursor.requery();
            flag=false;
        }

    }

    public void del(View v) {
        if (curItemId != -1&&flag) {
            UserEntity userEntity = mUserEntityDao.load(curItemId);
            mUserEntityDao.delete(userEntity);
            mCursor.requery();
            flag=false;
        }
    }

    public void delAll(View v) {
        mUserEntityDao.deleteAll();
        mCursor.requery();
    }
}
