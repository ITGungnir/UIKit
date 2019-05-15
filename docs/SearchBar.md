# UIKit - SearchBar

`SearchBar`是一个搜索栏控件。

## 1、自定义属性
|名称|含义|默认值|
|---|---|---|
|app:sb_hint|文本框的hint文本|空字符串|
|app:sb_hintColor|hint文本的颜色|Color.LTGRAY|
|app:sb_textColor|文本框中文本的颜色|Color.DKGRAY|
|app:sb_iconFont|文本框左侧图标|🔍|
|app:sb_btnColor|搜索按钮的颜色|Color.BLACK|
|app:sb_dividerColor|分隔线颜色|Color.LTGRAY|

## 2、布局
```xml
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
```

## 3、使用
```kotlin
searchBar.back("\ue720") { dismiss() }
    .doOnSearch { toast(it) }
```
也可以通过`getInput()`方法获取到`SearchBar`中的输入框，从而进行动态的内容修改。