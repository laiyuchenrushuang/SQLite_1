package com.ly.myapplication

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.view.View
import com.ly.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.ly.myapplication.database.SqliteOpenHelper
import java.io.File


/**
 * 1. 跑马灯效果
 * 2. 沉浸title
 */
class MainActivity : BaseActivity(), View.OnClickListener {

    var TABLE_NAME = "CODE_TABLE"
    var name = "name"
    var age = "age"
    var sex = "sex"
    var zplj = "zplj"
    var db: SQLiteDatabase? = null
    override fun initView() {
        setPageTitle("我的SQLite")
        text.isSelected = true
        bindEvent()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private fun bindEvent() {
        bt_create.setOnClickListener(this)
        bt_add.setOnClickListener(this)
        bt_delete.setOnClickListener(this)
        bt_search.setOnClickListener(this)
        bt_modify.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.bt_create -> {
                db = SqliteOpenHelper.getInstance(this)!!.writableDatabase
                Log.d("lylog", "path = " + db!!.path)
            }
            R.id.bt_add -> {

                val values = ContentValues()
                values.put(name, "laiyu")
                values.put(age, "29")
                values.put(sex, "boy")
                values.put(zplj, "/ss/ss/ss/s//")
                db!!.insert(TABLE_NAME, "id", values)
            }
            R.id.bt_delete -> {
                Log.d("lylog", "delete db!!.path = " + db!!.path)
                var file = File(db!!.path)
                file.delete()
                db!!.close()
            }
            R.id.bt_search -> {
                var list = ArrayList<UserBean.UserInfoBean>()
                var nm = edit_search!!.text.toString()
                var cursor = db!!.query(TABLE_NAME, null, null, null, null, null, null)
                if (cursor.count > 0) {
                    cursor.moveToFirst()
                    for (index in 0 until cursor.count) {
                        if (!"".equals(nm) && nm.equals(cursor.getString(cursor.getColumnIndex(name)))) {

                            var namec = cursor.getString(cursor.getColumnIndex(name))
                            var sexc = cursor.getString(cursor.getColumnIndex(sex))
                            var agec = cursor.getString(cursor.getColumnIndex(age))
                            var zpc = cursor.getString(cursor.getColumnIndex(zplj))
                            var ub = UserBean.UserInfoBean(namec, sexc, agec, zpc)
                            list.add(ub)
                        }
                    }
                }
                var string = StringBuffer()
                for(index in 0 until list.size){
                    string!!.append("naem = "+list[index].name+"  age = "+list[index].age+"  sex = "+list[index].sex+" zp = "+list[index].zplj+"\n")
                }
                tv_result.setText(string.toString())
                cursor.close()
            }
            R.id.bt_modify -> {
                val values = ContentValues()
                values.put(name, "laiyichen")
                values.put(age, "20")
                values.put(sex, "girl")
                values.put(zplj, "lalallalalallawsmbxhj/")
                db!!.update(TABLE_NAME,values,null,null)
            }
        }
    }

}
