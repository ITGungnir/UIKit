# UIKit - Dialog

`UIKit#Dialog`中目前提供了两种对话框：`FullScreenDialog`和`SimpleDialog`。

## 1、FullScreenDialog
`FullScreenDialog`是一种可以铺满屏幕的对话框控件，使用时需注意为布局设置`layout_margin`属性以显示半透明的边缘：
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <!-- 为此LinearLayout设置margin属性以显示半透明的边缘 -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="10dp"
        android:background="@color/colorPure"
        android:orientation="vertical"
        tools:ignore="UselessParent">

        <my.itgungnir.ui.search_bar.SearchBar
            android:id="@+id/searchBar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            app:sb_btnColor="@color/colorAccent"
            app:sb_dividerColor="@color/clr_divider"
            app:sb_hint="请输入搜索关键词"
            app:sb_hintColor="@color/clr_divider"
            app:sb_iconFont="\ue833"
            app:sb_textColor="@color/clr_text" />

        <my.itgungnir.ui.flex.ScrollableFlexView
            android:id="@+id/flexView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginStart="10dp"
            android:layout_marginTop="10dp"
            android:layout_marginEnd="10dp"
            android:clipToPadding="false"
            android:paddingBottom="10dp" />

    </LinearLayout>

</LinearLayout>
```
使用时，创建一个类继承`FullScreenDialog`类，并重写三个方法：
```kotlin
class SearchDialog : FullScreenDialog() {

    // 此Dialog的布局
    override fun layoutId(): Int = R.layout.dialog_search

    // 初始化控件
    override fun initComponent() {}

    // 此方法在 initComponent 之后被执行，常用来进行数据的监听
    override fun observeVM() {}
}
```
弹出对话框：
```kotlin
SearchDialog().show(supportFragmentManager, SearchDialog::class.java.name)
```

# 2、SimpleDialog
`SimpleDialog`是一种类似`iOS`系统中默认对话框的效果，仅有一个消息文本、一个`取消`按钮和一个`确定`按钮：
```kotlin
SimpleDialog.newInstance(
    // bgColor：对话框的背景颜色
    bgColor = Color.parseColor(COLOR_DIALOG_BG),
    // msgColor：对话框中消息文本的颜色
    msgColor = Color.parseColor(COLOR_DIALOG_MSG),
    // dividerColor：对话框中分隔线的颜色
    dividerColor = Color.parseColor(COLOR_DIVIDER),
    // btnColor：对话框中取消和确定按钮的颜色（确定按钮默认会加粗）
    btnColor = Color.parseColor(COLOR_DIALOG_BTN),
    // msg：对话框的消息文本
    msg = "Simple Dialog Test for UIKit.",
    // onConfirm：确定按钮的点击回调
    onConfirm = { toast("Confirm") },
    // onCancel：取消按钮的点击回调
    onCancel = { toast("Cancel") }
).show(supportFragmentManager, SimpleDialog::class.java.name)
```