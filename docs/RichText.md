# UIKit - RichText

`RichText`是一个用于方便快捷的展示富文本的控件，其支持的富文本效果包括：
* 改变背景色；
* 改变前景色；
* 添加删除线；
* 添加下划线；
* 设置绝对字体大小；
* 设置相对字体大小；
* 加粗；
* 倾斜；
* 添加点击事件；
* 插入图片。

## 使用
使用`RichText`的示例代码如下：
```kotlin
textView.apply {
    // 如果富文本中包含了点击效果，则必须为TextView设置movementMethod
    movementMethod = LinkMovementMethod.getInstance()
    text = RichText()
        .append("正常文本")
        .append("改变背景色")
        .backColor(Color.YELLOW)
        .append("改变前景色")
        .foreColor(Color.BLUE)
        .append("删除线")
        .middleLine()
        .append("下划线")
        .underLine()
        .append("绝对字体")
        .absoluteSize(25)
        .append("相")
        // 设置相对于正常字体大小的变化程度
        .relativeSize(1.5F)
        .append("对")
        .relativeSize(2.0F)
        .append("字")
        .relativeSize(2.5F)
        .append("体")
        .relativeSize(3.0F)
        .append("加粗")
        .bold()
        .append("倾斜")
        .italic()
        .append("可以点击")
        // 第二个参数：设置文本是否有下划线；第三个参数：设置文本点击后是否保留背景色
        .onClick(Color.RED, true, false) { toast("点击了可点击的文本") }
        .append("图片")
        .image(context!!, R.mipmap.ic_launcher)
        .create()
}
```
**注意：** 如果富文本中包含了点击效果，则必须为`TextView`设置`movementMethod`，否则点击无效！