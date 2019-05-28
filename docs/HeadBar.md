# UIKit - HeadBar

`HeadBar`是一个自定义封装的标题栏控件，其特点包括以下几点：
* 可以动态指定标题；
* 可以动态设置返回按钮的图标以及是否显示；
* 可以动态的添加工具按钮，并指定工具按钮的点击回调；
* 可以动态的改变工具按钮的图标，并提供了获取工具按钮数量的API；
* 可以动态的添加菜单项，并指定菜单项的点击回调。

## 1、自定义属性
|名称|含义|默认值|
|---|---|---|
|app:hb_textColor|控件中的文本颜色，包括返回按钮、标题和工具按钮|Color.WHITE|
|app:hb_showDivider|是否显示最底部的分隔线|false|
|app:hb_dividerColor|分隔线颜色|Color.LTGRAY|
|app:hb_menuBackground|菜单的背景色|Color.WHITE|
|app:hb_menuIconColor|菜单中图标的颜色|Color.BLACK|
|app:hb_menuTitleColor|菜单中文本的颜色|Color.BLACK|

## 2、布局
```xml
<my.itgungnir.ui.head_bar.HeadBar
    android:id="@+id/headBar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="@color/colorPrimary"
    app:hb_dividerColor="@color/clr_divider"
    app:hb_menuBackground="#115588"
    app:hb_menuIconColor="#00FF00"
    app:hb_menuTitleColor="@android:color/white"
    app:hb_showDivider="true"
    app:hb_textColor="@android:color/black" />
```

## 3、使用
为`HeadBar`设置标题：
```kotlin
headBar.title("Test UIKit")
```
为`HeadBar`设置返回按钮的图标以及点击回调：
```kotlin
headBar.back("\ue720") { finish() }
```
为`HeadBar`添加工具按钮：
```kotlin
headBar.addToolButton("\ue834") { /* Your codes */ }
```
其他管理`HeadBar`中工具按钮的方法：
```kotlin
// 修改HeadBar的第0个工具按钮的图标为"\ue720"
headBar.updateToolButton(0, "\ue720")
// 获取HeadBar中工具按钮的数量
headBar.toolButtonCount()
```
为`HeadBar`添加菜单项：
```kotlin
// 注：HeadBar只有在菜单项列表不为空的时候才会显示菜单按钮
headBar.addMenuItem("\ue601", "关于我们") { /* Your codes */ }
```
以下是HeadBar的完整应用代码：
```kotlin
headBar.title("Test UIKit")
    .back("\ue720") { finish() }
    .addToolButton("\ue834") {
        SimpleDialog.newInstance(
            bgColor = Color.parseColor(COLOR_DIALOG_BG),
            msgColor = Color.parseColor(COLOR_DIALOG_MSG),
            dividerColor = Color.parseColor(COLOR_DIVIDER),
            btnColor = Color.parseColor(COLOR_DIALOG_BTN),
            msg = "Simple Dialog Test for UIKit.",
            onConfirm = { toast("Confirm") },
            onCancel = { toast("Cancel") }
        ).show(supportFragmentManager, SimpleDialog::class.java.name)
    }
    .addToolButton("\ue833") {
        SearchDialog().show(supportFragmentManager, SearchDialog::class.java.name)
    }
    .addMenuItem("\ue601", "关于我们") {
        AboutUsDialog().show(supportFragmentManager, AboutUsDialog::class.java.name)
    }
    .addMenuItem("\ue6f2", "更换工具按钮图标") {
        headBar.updateToolButton(0, "\uE6F2")
    }
    .addMenuItem("\ue617", "获取工具按钮数量") {
        toast("共有${headBar.toolButtonCount()}个工具按钮")
    }
```