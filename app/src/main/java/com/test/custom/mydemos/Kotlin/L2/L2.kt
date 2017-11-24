package com.test.custom.mydemos.Kotlin.L2

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.test.custom.mydemos.R
import com.test.custom.mydemos.utils.LogUtil

class L2 : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l2)

        collectionOfList()


        mapTest()


        stringTest()


        yyTest()
    }
}


// -----------------------------容器-------------------------

//类可以不用大括号
class TestData(name:String)
fun collectionOfList(){
    //创建一个 list 容器可以放多种类型数据
    var list = listOf(1,"2",TestData("wtf"))

    // 获取 容器里的某个值
    LogUtil.e(list[2])

    //for循环遍历 容器
    for (item in list){
        LogUtil.e(item)
    }

    //用自带api forEach遍历
    list.forEach {
        //可以使用 关键字 it 表示 容器里数据
//        LogUtil.e(it)

        //也可以自己指定任意数据名字
        wtf ->
        LogUtil.e(wtf)
    }

    // 用自带api forEachIndexed 遍历 index下标 any 数据
    list.forEachIndexed { index, any ->
        LogUtil.e(index)
        LogUtil.e(any)
    }

}






//map
fun mapTest(){
    // map  初始化map 传入的参数 Pair（一组，一对） map 只读的
    var map_test = mapOf(1 to "1")
    //  1 to "2" 术语角：中缀调用 如果一直 to 则最后一个会被界定为 value 而前面的自动 两两成一对 pair_x  的 key 输出为：((1, 2), 3)
    var  pair_x = 1 to "2" to "3" to "4"
    var  pair_test = 1 to "2"
    //或者
    var pair_test2 = 1.to("2")

//    自己创建 xxxx 中缀调用 关键字 infix  用到泛型 返回的是 Pair 也可以自定义 Pair
    1 xxxx "1"





//    析构  因为 Pair 是键值对类型的 所以可以直接用下面的方式解析出 key value
    var (key,value) = pair_test
    LogUtil.e(key)

//    字符串切割 split返回的是 list 集合
    var (first,second) = "1:2:3:45".split(":")
    LogUtil.e(first)
}

infix fun <A, B> A.xxxx(that: B): Pair<A, B> = Pair(this, that)






//扩展方法 关键字 inline 。 在 String 上扩展一个点show 方法 在任意 String 类型上面都可与调用 如 "xxx".show()
fun Context.toast(message:CharSequence)= Toast.makeText(this,message, Toast.LENGTH_LONG).show()
inline fun String.show(){}






//扩展属性  扩展并不会真正给类添加了成员属性 要提供明确的get set 方法
var TextView.leftMargin:Int
    get():Int {
        return (layoutParams as ViewGroup.MarginLayoutParams).leftMargin
    }
    set(value) {
        (layoutParams as ViewGroup.MarginLayoutParams).leftMargin=value
    }



//字符串
fun stringTest(){

    //kotlin 为String 扩展了很多方法

    var str = "www.baidu.com"
    var array = str.split(".");//输出 [www,baidu,com]
    str.substringBeforeLast(".") //输出 www.biadu
    str.forEach {  } //每个字符





//    正则表达式（补充）





//    三引号的字符串

    var str3 = """
        selkg
        gwegw
        gwegw
    """.trimIndent() //三引号的字符串key随意的字符串 没有转义符 除了美元符号$

    var srt4 = """$str3""" //要是美元符号变成普通字符串 加单引号即可


}





//null安全  如果调用的对象有可能为空 就加个问号
fun nullTest(str:String?){
    str?.substring(1)
}





//本地函数  即 在方法内在声明方法或者类
fun funTest(str:String?){
    fun funnullTest(str:String?){
        if (str == null || str.isEmpty()){

        }
    }

    funnullTest(str)
    class FunTest(str:String)

}






//接口
interface IFTest{
    var name:String
    fun show(){}

}
interface IFTest2{
    var name2:String
    fun show(){}

}

//实现接口 需要重写成员变量 override 是关键字 在 java 里是注解
//如果实现的两个接口有方法同名 则可以指定 调的是哪个
open class ImplentIF(override var name: String, override var name2: String) :IFTest,IFTest2 {
    override fun show() {
        super<IFTest2>.show()
    }

    open fun iii(){}
}

//继承一个类也是用冒号 与 接口不同的是还需要括号 普通类默认是final 所以要加open关键字
//普通类里面的方法也是 默认final 要重写的话也要加上open 重新的方法需要加上关键字 override
class extendTest: ImplentIF("","") {
    override fun iii(){}

    override fun show() {
        super.show()
    }

}





//内部类
class innerClass{
    var ttt = "xxx"
    class Y{
        fun show(){
//            ttt.sub  //无法调用    没有 inner修饰 这个类就是个普通类 拿不到 ttt
        }
    }
    inner class InnerClass{
        fun show(){
//            ttt.substring(0) //这个才是内部类 能拿到 ttt
        }
    }
}


//类似java 的static 类
object StaticClass{
    fun show(){}
    var str = "tt"
}
//类里的 static 类    伴生类 一个类里面只允许一个伴生类 类似一个静态模块
// 伴生类可以不用命名 调用的时候 .Companion 调用
class YYY{
    companion object {
        fun x(){}
    }
}

fun staticClassTest() {
    StaticClass.show()//可以直接用
    StaticClass.str
    YYY.Companion.x()
}





//数据类 用关键字 data 修饰   效果就是这种类重写 tostring 我们不需要去手动重写 但是这个值针对主构造器 次构造器的成员变量不会被输出
data class XXXX(var a:String){}
fun showXXXX() {
    XXXX("1")
}

//构造器 除了类默认的构造器 还可以定义次级构造器 用关键字 constructor
//如果有主构造器 则次构造器要指向主构造器（在后面加：this）
// 类似java 里多参数的构造方法要super向少参数的
data class YY(var a:String){
    var x = ""
    var y = ""
    constructor(b:String,c:String):this(b){

        LogUtil.e("constructor---2")
    }
    constructor(d:String,e:String,f:String):this(d,e){
        LogUtil.e("constructor---3")
        this.y = e
    }


    //拿到主构造器的值
    init {
        LogUtil.e("init")
//        this.x = a
    }
}
fun yyTest(){
    LogUtil.e(YY("4","2","3"))
    LogUtil.e(YY("4","2","3").y)
}




//委托 替代继承





















