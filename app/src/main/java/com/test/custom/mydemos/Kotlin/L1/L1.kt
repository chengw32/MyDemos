package com.test.custom.mydemos.Kotlin.L1

import com.test.custom.mydemos.R
import com.test.custom.mydemos.base.BaseActivity
import com.test.custom.mydemos.utils.LogUtil


class L1 : BaseActivity() {
    override fun setContentView() {
        setContentView(R.layout.activity_l2)
        getKotText()
        getPerson()
        getEnum()
        cycle()
        test("")
        test2(p=20)
        test3(1,2,5)

    }
}

//传参
fun test(param:String = "wtf"){
    LogUtil.e(param)
}
fun test2(p:Int=10,param:String = "wtf"){
    LogUtil.e(p)
    LogUtil.e(param)
}

//可变长度参数
fun test3(vararg param:Int){

}

fun getKotText():String{
    var i:Int = 0
    var str = "getKT 的值;${getKT()}  getKoT 的值:${getKoT(5,6)}"
    LogUtil.e(str)
    var sstt =i
    LogUtil.e(i)
    i=5
    LogUtil.e(i)
    getKT2(null)
    return "mykot"
}

fun getKT() = 2 ;
fun getKT2(a:Int?) = 2 ;
fun getKoT(x:Int,y:Int) = if (x > y){x} else{y}


fun getPerson(){
    var p = Person("huang")
    LogUtil.e(p.age)
    p.age = 25
    LogUtil.e(p.age)
    LogUtil.e(p.a)

}
class Person(var a:String?){
     var age:Int = 10
         get() =  field
         set(value) {field = value}
}

fun getEnum(){
    when(Test.Z){
        Test.X ->{LogUtil.e( "符合 X 条件") }
        //穿透
        Test.Z,
        Test.Y ->{ LogUtil.e("符合 Y、Z 条件") }

        else -> { LogUtil.e("都不符合" ) }
    }
}
enum class Test(name:String,index:Int){
    X("x",1),
    Y("y",2),
    Z("z",3);
    fun getIndex() = ordinal

}

fun cycle(){
    for (i in 0..10){

    }
    var range:IntRange = 0..10
    for (i in range){
        if (i == 2)break
        LogUtil.e(i)
    }
}


