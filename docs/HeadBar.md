# UIKit - HeadBar

`HeadBar`是一个自定义封装的标题栏控件，其特点包括以下几点：
* 可以动态指定标题；
* 可以动态设置返回按钮的图标以及是否显示；
* 可以动态的添加工具按钮，并置顶工具按钮的点击回调。

## 1、自定义属性
|名称|含义|默认值|
|---|---|---|
|app:hb_textColor|控件中的文本颜色，包括返回按钮、标题和工具按钮|Color.WHITE|
|app:hb_showDivider|是否显示最底部的分隔线|false|
|app:hb_dividerColor|分隔线颜色|Color.LTGRAY|

## 2、布局
```xml
<my.itgungnir.ui.head_bar.HeadBar
    android:id="@+id/headBar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="@color/colorPrimary"
    app:hb_showDivider="false"
    app:hb_textColor="@color/clr_text" />
```

## 3、使用
```kotlin
headBar.title("Test UIKit")
    .back("\ue720") { finish() }
    .addToolButton("\ue834") {
        // ... Your codes
    }
    .addToolButton("\ue833") {
        // ... Your codes
    }
```
`HeadBar`中还提供了一些其他方法，如`updateToolButton(position, iconFont)`方法，可以更新某个位置上的工具按钮图标；`toolButtonCount()`方法，可以获取到工具按钮的总数等。